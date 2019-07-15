package com.example.Rate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CalendarView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Rate.Retrofit.Client;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton fab_main, fab_edit, fab_tran;
    CalendarView calendarView;
    TextView tv_title_1, tv_title_2, tv_content_1, tv_content_2;
    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    Integer year, month, days, now_year, now_month, now_days;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_title_1 = findViewById(R.id.your_experience_title);
        tv_content_1 = findViewById(R.id.your_experience_content);
        tv_title_2 = findViewById(R.id.your_day_title);
        tv_content_2 = findViewById(R.id.your_day_content);
        calendarView = findViewById(R.id.calendar);
        fab_main = findViewById(R.id.fab_main);
        fab_edit = findViewById(R.id.fab_edit);
        fab_tran = findViewById(R.id.fab_tran);
        fab_main.setOnClickListener(this);
        fab_edit.setOnClickListener(this);
        fab_tran.setOnClickListener(this);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        ratingBar = findViewById(R.id.ratingBar);


        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = sdf.format(date);
        String[] separated = getTime.split("-");
        year = Integer.parseInt(separated[0]);
        month = Integer.parseInt(separated[1]);
        days = Integer.parseInt(separated[2]);
        now_year = year;
        now_month = month;
        now_days = days;

        Client.api.getRating(Client.id, now_year, now_month, now_days).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                switch (response.code()) {
                    case 200:
                        tv_content_1.setText("error");
                        tv_content_2.setText("error");
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.body().string());
                            Log.d("!!!", jsonObject.getString("whatyougood") + '+' + jsonObject.getString("whatyoubad"));
                            tv_content_1.setText(jsonObject.getString("whatyoudo"));
                            tv_content_2.setText(jsonObject.getString("whatyougood") + '\n' + jsonObject.getString("whatyoubad"));
                            ratingBar.setRating((float) jsonObject.getDouble("importance"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        break;
                    case 400:
                        Toast.makeText(getApplicationContext(), "레이팅 실패", Toast.LENGTH_SHORT).show();
                        break;
                    case 403:
                        Toast.makeText(getApplicationContext(), "레이팅 실패 : 없는 아이디입니다.", Toast.LENGTH_SHORT).show();
                        break;
                    case 500:
                        Toast.makeText(getApplicationContext(), "레이팅 실패 : 서버가 터졌어요.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


        tv_title_1.setText(month + "월 " + days + "일 의 경험");
        tv_title_2.setText(month + "월 " + days + "일 의 하루");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int _year, int _month, int _dayOfMonth) {
                year = _year;
                month = _month + 1;
                days = _dayOfMonth;
                tv_title_1.setText(month + "월 " + days + "일 의 경험");
                tv_title_2.setText(month + "월 " + days + "일 의 하루");
                Client.api.getRating(Client.id, year, month, days).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                        switch (response.code()) {
                            case 200:
                                tv_content_1.setText("error");
                                tv_content_2.setText("error");
                                JSONObject jsonObject = null;
                                try {
                                    jsonObject = new JSONObject(response.body().string());
                                    Log.d("!!!", jsonObject.getString("whatyougood") + '+' + jsonObject.getString("whatyoubad"));
                                    tv_content_1.setText(jsonObject.getString("whatyoudo"));
                                    tv_content_2.setText(jsonObject.getString("whatyougood") + '\n' + jsonObject.getString("whatyoubad"));
                                    ratingBar.setRating((float) jsonObject.getDouble("importance"));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                break;
                            case 400:
                                Toast.makeText(getApplicationContext(), "레이팅 실패", Toast.LENGTH_SHORT).show();
                                break;
                            case 403:
                                Toast.makeText(getApplicationContext(), "레이팅 실패 : 없는 아이디입니다.", Toast.LENGTH_SHORT).show();
                                break;
                            case 500:
                                Toast.makeText(getApplicationContext(), "레이팅 실패 : 서버가 터졌어요.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab_main:
                anim();
                break;

            case R.id.fab_tran:
                anim();
                Intent intent1 = new Intent(MainActivity.this, RemindActivity.class);
                startActivity(intent1);
                break;

            case R.id.fab_edit:
                if (now_year < year || now_month < month || now_days < days) {
                    Toast.makeText(this, "미래 날짜에는 작성할 수 없습니다.", Toast.LENGTH_SHORT).show();
                } else if (now_year > year || now_month > month || now_days > days) {
                    Toast.makeText(this, "과거 날짜는 기록만 확인이 가능합니다.", Toast.LENGTH_SHORT).show();
                } else {
                    anim();
                    Intent intent2 = new Intent(MainActivity.this, RatingActivity.class);
                    intent2.putExtra("year", year);
                    intent2.putExtra("month", month);
                    intent2.putExtra("days", days);
                    startActivityForResult(intent2, 100);
                }

                break;
        }
    }

    public void anim() {

        if (isFabOpen) {
            fab_tran.startAnimation(fab_close);
            fab_edit.startAnimation(fab_close);
            fab_tran.setClickable(false);
            fab_edit.setClickable(false);
            isFabOpen = false;
        } else {
            fab_tran.startAnimation(fab_open);
            fab_edit.startAnimation(fab_open);
            fab_tran.setClickable(true);
            fab_edit.setClickable(true);
            isFabOpen = true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == RESULT_OK) {
            String s1 = data.getStringExtra("s1");
            String s2 = data.getStringExtra("s2");
            String s3 = data.getStringExtra("s3");
            float rating = data.getFloatExtra("rating", 0);
            tv_content_1.setText(s1);
            tv_content_2.setText(s2 + '\n' + s3);
            ratingBar.setRating(rating);

        }
    }
}
