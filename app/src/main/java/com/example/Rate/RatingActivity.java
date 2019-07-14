package com.example.Rate;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Rate.Retrofit.Client;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatingActivity extends AppCompatActivity {
    TextView textView;
    RatingBar ratingBar;
    float rate = -1;
    AlertDialog.Builder builder;
    EditText edit1, edit2, edit3;
    Button save;
    AlertDialog alertDialog;
    Integer year, month, days;
    TextView content, experience, rateText;
    ImageView help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        textView = findViewById(R.id.txt_rating);
        ratingBar = findViewById(R.id.ratingBar);

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        edit3 = findViewById(R.id.edit3);

        content = findViewById(R.id.your_day_content);
        experience = findViewById(R.id.your_experience_content);

        help = findViewById(R.id.btn_help);


        final Intent intent = getIntent();
        year = intent.getIntExtra("year", 0);
        month = intent.getIntExtra("month", 0);
        days = intent.getIntExtra("days", 0);

        save = findViewById(R.id.save);
        rateText = findViewById(R.id.txt_rating);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                rate = rating;
                rateText.setText(Float.toString(rating));
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String s1 = edit1.getText().toString();
                final String s2 = edit2.getText().toString();
                final String s3 = edit3.getText().toString();
                if (s1.equals("") || s2.equals("") || s3.equals("") || rate == -1) {
                    Toast.makeText(getApplicationContext(), "모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent resultIntent = new Intent();
                resultIntent.putExtra("s1", s1);
                resultIntent.putExtra("s2", s2 + "\n" + s3);
                resultIntent.putExtra("rating", rate);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        builder = new AlertDialog.Builder(this);


        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Help");
                builder.setMessage("도움이 필요하신가요?");
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        builder.setTitle("Help2");
                        builder.setMessage("친구에 대해서 써보시는 건 어떨까요??");
                        builder.setPositiveButton("좋아요!", null);
                        builder.setNegativeButton("다른걸로 해볼래요!", null);
                        alertDialog = builder.create();
                        alertDialog.show();


                    }
                });


                builder.setNegativeButton("아니요", null);


                alertDialog = builder.create();
                alertDialog.show();
            }
        });

//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String s2 = edit2.getText().toString();
//                final String s3 = edit3.getText().toString();
//                final String s1 = edit1.getText().toString();
//                if (s1.equals("") || s2.equals("") || s3.equals("") || rate == -1)
//                    Toast.makeText(getApplicationContext(), "모두 입력해주세요.", Toast.LENGTH_SHORT).show();
//
//                Client.api.setRating(Client.id, s1, s2, s3, year, month, days).enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        switch (response.code()) {
//                            case 200:
//                                finish();
//                                Intent intent2 = new Intent();
//                                intent2.putExtra("s1", s1);
//                                intent2.putExtra("s2", s2 + s3);
//                                setResult(RESULT_OK, intent2);
//                                break;
//                            case 400:
//                                Toast.makeText(getApplicationContext(), "레이팅 실패", Toast.LENGTH_SHORT).show();
//                                break;
//                            case 403:
//                                Toast.makeText(getApplicationContext(), "레이팅 실패 : 없는 아이디입니다.", Toast.LENGTH_SHORT).show();
//                                break;
//                            case 500:
//                                Toast.makeText(getApplicationContext(), "레이팅 실패 : 서버가 터졌어요.", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                    }
//                });
//            }
//        });


    }
}
