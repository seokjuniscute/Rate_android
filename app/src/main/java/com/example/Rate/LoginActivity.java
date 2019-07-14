package com.example.Rate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Rate.Retrofit.Client;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button login, register;
    EditText ID, PW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final SharedPreferences spf = getApplicationContext().getSharedPreferences("Rate", 0);

        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        ID = findViewById(R.id.login_id);
        PW = findViewById(R.id.login_pw);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = ID.getText().toString();
                String password = PW.getText().toString();
                if (id.equals("") || password.equals("")) {
                    Toast.makeText(getApplicationContext(), "정보가 모두 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
                Client.api.Login(id, password).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        switch (response.code()) {
                            case 200:
                                finish();
                                try {
                                    String responseBody = response.body().string();
                                    JSONObject json = new JSONObject(responseBody);
                                    String token = json.getString("token");

                                    spf.edit().putString("token", token);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    break;
                                }

                                spf.edit().putBoolean("login", true);

                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                Client.id = id;
//                                alarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//                                Intent intent = new Intent(LoginActivity.this, RateReceiver.class);
//
//                                PendingIntent pendingIntent = PendingIntent.getBroadcast(LoginActivity.this, 201, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//                                Calendar calendar = Calendar.getInstance();
//                                calendar.setTimeInMillis(System.currentTimeMillis());
//                                calendar.set(Calendar.HOUR_OF_DAY, 15);
//                                calendar.set(Calendar.MINUTE, 11);
//                                alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                                        SystemClock.elapsedRealtime() + 60 * 1000, pendingIntent);
                                break;
                            case 403:
                                Toast.makeText(getApplicationContext(), "로그인 실패 : 아이디나 비밀번호가 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
                                break;
                            case 500:
                                Toast.makeText(getApplicationContext(), "로그인 실패 : 서버가 터졌어요.", Toast.LENGTH_SHORT).show();
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
