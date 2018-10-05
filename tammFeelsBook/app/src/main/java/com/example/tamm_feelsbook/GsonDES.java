package com.example.tamm_feelsbook;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import java.text.SimpleDateFormat;

/* Using GsonDES class to solve Gson issues with SimpleDateFormat
https://stackoverflow.com/questions/22831578/gson-is-crashing-with-failed-to-invoke-protected-java-text-numberformat-with-n
Users: https://stackoverflow.com/users/2523667/nicolas
Date: 2018-10-04
 */

public class GsonDES implements ExclusionStrategy{

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getDeclaredClass() == SimpleDateFormat.class;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}
