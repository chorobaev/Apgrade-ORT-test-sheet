package com.example.apgrate.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apgrate.BuildConfig;

import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AlertDialog;

import static android.content.Context.MODE_PRIVATE;

public class CommonUtils {

    public static void makeLongToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void makeShortToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void closeApp(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static class TestTimer {

        private boolean isTimerRunning = false;
        private Timer timer;
        private OnTimerListener mListener;

        public void setOnTimerListener(OnTimerListener listener) {
            mListener = listener;
        }

        public void setNewTimer(int initialTime) {
            checkListenerExists();
            timer = new Timer();
            if (isTimerRunning) {
                throw new IllegalMonitorStateException("Timer is already running");
            } else {
                isTimerRunning = true;
                startTimer(initialTime);
            }
        }

        public void terminateTimer() {
            if (timer != null) {
                isTimerRunning = false;
                timer.cancel();
                timer = null;
            }
        }

        private void startTimer(int initialTime) {
            final int[] s = {initialTime};
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    mListener.run(s[0]--);
                    if (s[0] == -1) {
                        timer.cancel();
                        isTimerRunning = false;
                        mListener.timeOver();
                    }
                }
            }, 1000, 1000);
        }

        private void checkListenerExists() {
            if (mListener == null) {
                throw  new NullPointerException("Set OnTimerListener first");
            }
        }
    }

    public interface OnTimerListener {
        void run(int time);
        void timeOver();
    }

    public static void showYesNoDialog(Context context, String title, String msg, String positiveBtn, String negativeBtn, DialogInterface.OnClickListener listener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(msg)
                .setPositiveButton(positiveBtn, listener)
                .setNegativeButton(negativeBtn, listener).show();
    }
}
