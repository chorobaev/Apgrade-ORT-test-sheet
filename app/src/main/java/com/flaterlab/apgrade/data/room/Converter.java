package com.flaterlab.apgrade.data.room;

public class Converter {

    public static int[] fromString(String value) {
        String[] nums = value.trim().split(" ");
        int[] a = new int[nums.length];
        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.valueOf(nums[i]);
        }

        return a;
    }

    public static String fromIntArray(int[] answers) {
        StringBuilder sb = new StringBuilder();
        for (int i : answers) {
            sb.append(i);
            sb.append(" ");
        }

        return sb.toString();
    }


}
