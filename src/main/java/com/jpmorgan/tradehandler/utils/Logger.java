package com.jpmorgan.tradehandler.utils;

import com.jpmorgan.tradehandler.common.Constant;

public class Logger {
    public static void log(String title, Object data) {
        System.out.println(String.format(Constant.STRING_FORMAT_FOR_LOG, title, data != null ? data.toString() : "NONE"));
    }
    
    public static void log(String message) {
    	System.out.println(message);
    }
}
