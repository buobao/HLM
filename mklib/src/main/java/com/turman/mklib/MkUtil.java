package com.turman.mklib;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;

public class MkUtil {
    private volatile static MkUtil instance;

    private MkUtil() { }

    public static MkUtil getInstance() {
        if (instance == null) {
            synchronized (MkUtil.class) {
                if (instance == null) {
                    instance = new MkUtil();
                }
            }
        }
        return instance;
    }

    private SpannableString convertStringToSpannable(String source) {



        SpannableStringBuilder spb = new SpannableStringBuilder(source);

        SpannableString sp = new SpannableString(source);


        return sp;
    }

}
