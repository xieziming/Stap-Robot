package com.xieziming.stap.robot;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Suny on 8/18/16.
 */
public class UidCreator {
    public static String getUid(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        return simpleDateFormat.format(Calendar.getInstance().getTime());
    }
}
