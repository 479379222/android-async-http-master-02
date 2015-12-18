package com.example.myas.myasyntest.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015-12-18.
 */
public class CommonUtils {
    public static boolean isMobileNum(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches() + "---");
        return m.matches();

    }

    public static String getDay() {
        Calendar c=Calendar.getInstance();

        String strDay=c.get(Calendar.YEAR)+""+
                formatTime(c.get(Calendar.MONTH)+1) +
                formatTime(c.get(Calendar.DAY_OF_MONTH));
        return strDay;
    }

    public static String getDay(String SperateChar) {
        Calendar c=Calendar.getInstance();

        String strDay=c.get(Calendar.YEAR)+SperateChar+
                formatTime(c.get(Calendar.MONTH)+1) + SperateChar	+
                formatTime(c.get(Calendar.DAY_OF_MONTH));
        return strDay;
    }
    private static String formatTime(int t){
        return t>=10? ""+t:"0"+t;
    }

    //32
    public static String getMd5Value(String sSecret) {
        try {
            MessageDigest bmd5 = MessageDigest.getInstance("MD5");
            bmd5.update(sSecret.getBytes());
            int i;
            StringBuffer buf = new StringBuffer();
            byte[] b = bmd5.digest();
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null)
        {
        } else {

            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public static String calcdate(String OriginalDate,int para)
    {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try{
            date = new SimpleDateFormat("yy-MM-dd").parse(OriginalDate);
        }catch (Exception e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + para);
        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;

    }


    public static Boolean IsLogin() {
		 /*String userID=PreferencesUtils.getString(content,"UserID","");
		 String pwd=PreferencesUtils.getString(content,"PWD","");
		 if("".equals(userID)&&"".equals(pwd))
			 return false;
		 else
			 return true;*/

        return true;
    }
}
