package com.example.Rate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.Rate.Retrofit.Client;

import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

//        Button overlap;
    ImageView setTime, save;
    TextView Time;
    EditText ID, PW;
    int hours, minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setTime = findViewById(R.id.btn_time);
        Time = findViewById(R.id.time);
        save = findViewById(R.id.save);
//        overlap = findViewById(R.id.overlap);
        ID = findViewById(R.id.register_id);
        PW = findViewById(R.id.register_pw);
        hours = -1;
        minutes = -1;

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(RegisterActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Time.setText(selectedHour + "시 " + selectedMinute + "분");
                        hours = selectedHour;
                        minutes = selectedMinute;
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select time");
                mTimePicker.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = ID.getText().toString();
                String password = PW.getText().toString();
                if (id.equals("") || password.equals("") || hours == -1 || minutes == -1) {
                    Toast.makeText(getApplicationContext(), "정보가 모두 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
                if (id.length() < 7 || id.length() > 10) {
                    Toast.makeText(getApplicationContext(), "아이디는 7자 이상, 10자 미만으로 작성해야 합니다.", Toast.LENGTH_SHORT).show();

                }
                else if(password.length() < 7 || password.length() > 11){
                    Toast.makeText(getApplicationContext(), "비밀번호는 7자이상, 11자 미만으로 작성해야합니다.",Toast.LENGTH_SHORT).show();
                }
                else {


                    Client.api.Register(id, password, hours, minutes).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            switch (response.code()) {
                                case 200:
                                    finish();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    Client.id = id;
                                    break;
                                case 403:
                                    Toast.makeText(getApplicationContext(), "회원가입 실패 : 이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 500:
                                    Toast.makeText(getApplicationContext(), "회원가입 실패 : 서버가 터졌어요.", Toast.LENGTH_SHORT).show();
                            }
                        }


                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                        }
                    });


                }
            }
        });
    }
}

