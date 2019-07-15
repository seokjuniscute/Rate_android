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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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

//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String s1 = edit1.getText().toString();
//                final String s2 = edit2.getText().toString();
//                final String s3 = edit3.getText().toString();
//                if (s1.equals("") || s2.equals("") || s3.equals("") || rate == -1) {
//                    Toast.makeText(getApplicationContext(), "모두 입력해주세요.", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                Intent resultIntent = new Intent();
//                resultIntent.putExtra("s1", s1);
//                resultIntent.putExtra("s2", s2 + "\n" + s3);
//                resultIntent.putExtra("rating", rate);
//                setResult(RESULT_OK, resultIntent);
//                finish();
//            }
//        });

        builder = new AlertDialog.Builder(this);
        final ArrayList<String> messages = new ArrayList<String>(Arrays.asList("오늘은 친구에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 학교에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 가족에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 구름에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 먹었던 음식에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 좋아하는 연예인에 대해서 생각해 보시는 것은 어떨까요?",
                "오늘은 들었던 노래에 대해 생각해 보시는건 어떨까요?",
                "오늘은 좋아하시는 스포츠에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 보았던 영상에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 지하철 반대편에 대해서 생각해 보시는건 어떨까요?",
                "오늘은 자연에 대해서 생각해 보시는것은 어떨까요?",
                "오늘은 우리 주위의 고마운 분들에 대해서 생각해 보시는 것은 어떨까요?",
                "오늘은 여러분이 사랑하는 분들에 대해서 생각해 보시는 것은 어떨까요?",
                "오늘은 소중한 사람에 대해서 생각해 보시는건 어떨까요?",
                "오늘은 소중하지만 작은 행복에 대해서 생각해 보시는 것은 어떨까요?",
                "오늘은 꾸었던 꿈에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 애완동물에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 여행에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 게임에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은  해에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 당신의 패션에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 당신의 소비에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 사회에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 역사에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 난민에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 철학에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 지구에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 달에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 전래동화에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 산에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 나라를 지키는 국군에 대해서 생각해  보시는 건 어떨까요?",
                "오늘은 자신의 직업을 생각해 보시는 건 어떨까요?",
                "오늘은 별에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 일기에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 당신의 언행에 대해서생각해 보시는 건 어떨까요?",
                "오늘은 당신을 생각해 보시는 건 어떨까요?",
                "오늘은 영화에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 예술에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 어린 시절에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 인생의 파트너에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 읽었던 책에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 어린 시절 좋아하던 것에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 자신의 외모에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 자신의 특기에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 연인에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 선배에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 후배에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 우주에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 한글에 대해서생각해 보시는 건 어떨까요?",
                "오늘은 당신의 생활 습관에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 꽃들에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 당신의 사진에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 당신의 계획에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 당신의 취미에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 나만의 멘토에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 자신의 동아리에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 자신의 심리적 안정에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 자신의 묘비명에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 자신의 헤어스타일에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 미세먼지에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 외교에 생각해 보시는 건 어떨까요?",
                "오늘은 하늘에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 정치에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 시에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 문학에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 대학에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 전자기기에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 당신의 자동차에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 인테리어에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 당신의 집에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 당신의 SNS에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 당신의 경제적 여건에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 보안에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 자신의 건강에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 자신의 시간 생각해 보시는 건 어떨까요?",
                "오늘은 당신의 새벽 활동에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 당신의 낮 활동에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 생각해 밤 활동에 대해 보시는 건 어떨까요?",
                "오늘은 이웃에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 자신이 평상시에 하는 운동에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 운명적인 인연에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 추억에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 거짓말에 생각해 보시는 건 어떨까요?",
                "오늘은 자신만의 신념에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 중고에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 종교에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 환생에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 자신의 단점에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 자신의 장점에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 자신만의 색에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 날씨 생각해 보시는 건 어떨까요?",
                "오늘은 후회에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 이성과 본성에 대해서 생각해 보시는 건 어떨까요?",
                "오늘은 약속에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 나와 타인과의 거리에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 자신의 흔적에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 커피에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 소설에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 만화에 대해 생각해 보시는 건 어떨까요?",
                "오늘은 외국어에 대해 생각해 보시는 건 어떨까요?"));
        final Random generator = new Random();


        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Help");
                builder.setMessage("도움이 필요하신가요?");
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int num = generator.nextInt(100);

                        builder.setTitle("Help2");
                        builder.setMessage(messages.get(num));
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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String s2 = edit2.getText().toString();
                final String s3 = edit3.getText().toString();
                final String s1 = edit1.getText().toString();
                if (s1.equals("") || s2.equals("") || s3.equals("") || rate == -1)
                    Toast.makeText(getApplicationContext(), "모두 입력해주세요.", Toast.LENGTH_SHORT).show();

                Client.api.setRating(Client.id, s1, s2, s3, year, month, days, rate).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        switch (response.code()) {
                            case 200:
                                Intent intent2 = new Intent();
                                intent2.putExtra("s1", s1);
                                intent2.putExtra("s2", s2);
                                intent2.putExtra("s3", s3);
                                intent2.putExtra("rating", rate);
                                setResult(RESULT_OK, intent2);
                                finish();
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
}
