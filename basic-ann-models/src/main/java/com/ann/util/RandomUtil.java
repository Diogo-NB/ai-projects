package com.ann.util;

public class RandomUtil {

    public static float randomFloat(float min, float max) {
        return (float) (Math.random() * (max - min) + min);
    }
    
}
