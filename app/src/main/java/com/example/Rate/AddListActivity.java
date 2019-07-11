package com.example.Rate;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddListActivity extends AppCompatActivity {
    TextView remindDay;
    TextView remindTime;
    ImageView up, down;
    EditText remind_edit;
    TextView remind_importance;
    Integer integer;
    Button btn_save;
    int pos;
    ArrayList<Data> arrayList = new ArrayList<>();
    Button btn_not_save;
    Button btn_clear;

    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener myDatepicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        btn_save = findViewById(R.id.save_remind);
        btn_not_save = findViewById(R.id.dont_save_remind);
        btn_clear = findViewById(R.id.clear_remind);
        up  = findViewById(R.id.up_importance);
        down = findViewById(R.id.down_importance);


        remindDay = findViewById(R.id.remind_day);
        remindTime = findViewById(R.id.remind_time);
        remind_importance = findViewById(R.id.remind_importance);
        remind_edit = findViewById(R.id.remind_content);


        integer = 0;
        Intent i = getIntent();
        pos = i.getExtras().getInt("position");


        if (pos != -1){
            loadData();

            remindDay.setText(arrayList.get(pos).getDay());
            remindTime.setText(arrayList.get(pos).getTime());
            remind_edit.setText(arrayList.get(pos).getContnet());
            remind_importance.setText(arrayList.get(pos).getImportance());
        }

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pos != -1){
                    arrayList.remove(pos);
                    saveData();
                }
                Intent intent = new Intent(AddListActivity.this,RemindActivity.class);
                startActivity(intent);

                finish();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pos == -1){
                    loadData();

                    Data data = new Data(remindDay.getText().toString(),remindTime.getText().toString(),remind_edit.getText().toString(),remind_importance.getText().toString());

                    arrayList.add(data);
                    saveData();
                }

                else {
                    Data data = new Data(remindDay.getText().toString(),remindTime.getText().toString(),remind_edit.getText().toString(),remind_importance.getText().toString());
                    arrayList.set(pos, data);
                    saveData();
                }

                Intent intent = new Intent(AddListActivity.this,RemindActivity.class);
                startActivity(intent);

                finish();

            }
        });


        btn_not_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddListActivity.this, RemindActivity.class);
                startActivity(intent);

                finish();
            }
        });

        final ImageView selectDay = findViewById(R.id.select_day);
        final ImageView selectTime = findViewById(R.id.select_time);




        selectDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddListActivity.this, myDatepicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        selectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentTime = Calendar.getInstance();
                int hour = currentTime.get(Calendar.HOUR_OF_DAY);
                int minute = currentTime.get(Calendar.MINUTE);
                TimePickerDialog TimePicker;
                TimePicker = new TimePickerDialog(AddListActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String state = "오전";
                        if (selectedHour > 12) {
                            selectedHour -= 12;
                            state = "오후";
                        }
                        remindTime.setText(state + " " + selectedHour + "시 " + selectedMinute + "분");
                    }
                }, hour, minute, false);
                TimePicker.setTitle("Select Time");
                TimePicker.show();
            }
        });




        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (integer==5){
                    Toast.makeText(AddListActivity.this, "중요도의 최고 수치는 5 입니다.", Toast.LENGTH_SHORT).show();
                }else{
                    integer++;
                }
                remind_importance.setText(""+integer);
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(integer==0){
                    Toast.makeText(AddListActivity.this, "중요도의 최하 수치는 0 입니다.", Toast.LENGTH_SHORT).show();
                }else{
                    integer--;
                }
                remind_importance.setText(""+integer);
            }
        });
    }

    private void updateLabel() {
        String myFormat = "yyyy년 MM월 dd일";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        remindDay.setText(sdf.format(myCalendar.getTime()));
    }

    public void loadData(){
        Gson gson = new Gson();
        SharedPreferences prefs = getSharedPreferences("obj",MODE_PRIVATE);
        String json = prefs.getString("obj","");
        ArrayList<Data> alt;
        alt = gson.fromJson(json,new TypeToken<ArrayList<Data>>(){}.getType());
        if (alt != null) arrayList.addAll(alt);
    }

    public void saveData(){
        SharedPreferences prefs = getSharedPreferences("obj",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString("obj",json);
        editor.apply();

    }


}