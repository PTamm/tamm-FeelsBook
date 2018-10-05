package com.example.tamm_feelsbook;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import java.text.SimpleDateFormat;

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
