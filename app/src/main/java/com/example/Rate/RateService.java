package com.example.Rate;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class RateService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startActivity(new Intent(this, RatingActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
