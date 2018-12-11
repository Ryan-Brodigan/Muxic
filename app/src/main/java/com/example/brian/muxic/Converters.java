package com.example.brian.muxic;

import android.arch.persistence.room.TypeConverter;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class Converters {

    @TypeConverter
    public static String arrayListToString(ArrayList<String> strings){
        return TextUtils.join(",",strings);
    }

    @TypeConverter
    public static ArrayList<String> stringToArrayList(String string){
        return new ArrayList<>(Arrays.asList(string.split(",")));
    }
}
