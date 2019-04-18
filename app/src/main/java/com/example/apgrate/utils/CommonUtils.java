package com.example.apgrate.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apgrate.R;

import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AlertDialog;

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

    public static void showChooseLanguageDialog(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.choose_lang_dialog_layout);
        TextView title = dialog.findViewById(R.id.tv_title);
        title.setText(activity.getResources().getString(R.string.dialog_choose_lang_title));

        RadioGroup group = dialog.findViewById(R.id.rg_languages);

        RadioButton rbtKg = new RadioButton(activity);
        rbtKg.setId((int) 1);
        rbtKg.setText(activity.getResources().getString(R.string.dialog_choose_lang_btn_kg));
        group.addView(rbtKg);

        RadioButton rbtRu = new RadioButton(activity);
        rbtKg.setId((int) 2);
        rbtRu.setText(activity.getResources().getString(R.string.dialog_choose_lang_btn_ru));
        group.addView(rbtRu);

        final String[] lang = {"ky"};
        RadioGroup.OnCheckedChangeListener listener = (group1, checkedId) -> {
            lang[0] = checkedId == 1 ? "ru" : "ky";
            Log.d("MylogChosen", lang[0]);
        };

        group.setOnCheckedChangeListener(listener);
        rbtKg.toggle();

        TextView button = dialog.findViewById(R.id.tv_choose);
        button.setOnClickListener(v -> {
            dialog.dismiss();
            SharedPreferences shP = activity.getSharedPreferences(BaseActivity.CONFIG_LANGUAGE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = shP.edit();
            editor.putString(BaseActivity.CURRENT_LANGUAGE, lang[0]);
            editor.apply();
            editor.commit();
            activity.recreate();
        });

        dialog.show();
    }

    public static void showAlertDialog(Context context, String title, String msg, String btn) {
        android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(context).create();
        dialog.setTitle(title);
        dialog.setMessage(msg);

        dialog.setButton(android.app.AlertDialog.BUTTON_NEUTRAL,
                context.getResources().getString(R.string.dialog_ok_btn),
                ((dialog1, which) -> dialog.dismiss()));
        dialog.show();
    }
}
