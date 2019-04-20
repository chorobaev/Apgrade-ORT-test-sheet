package com.example.apgrate.helper;

public class Common {

    public static String getTimeFormat(int seconds) {
        StringBuilder s = new StringBuilder();
        int min = seconds / 60;
        int sec = seconds % 60;
        if (min > 9) {
            s.append(min);
        } else  {
            s.append('0');
            s.append(min);
        }
        s.append(':');

        if (sec > 9) {
            s.append(sec);
        } else {
            s.append('0');
            s.append(sec);
        }

        return s.toString();
    }

    public static String getUidFromEmail(String email) {
        return email.substring(0, email.indexOf('@'));
    }
}
