package com.cms.backend.util;

import java.util.Random;

public class Color {
    
    public static String randomHex(){
        Random obj = new Random();
        int rand_num = obj.nextInt(0xffffff + 1);
        String colorCode = String.format("#%06x", rand_num);
        return colorCode;
    }

}
