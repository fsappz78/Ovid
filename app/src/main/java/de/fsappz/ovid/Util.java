package de.fsappz.ovid;

import android.content.Context;
import android.util.Base64;

import static android.content.Context.MODE_PRIVATE;

public class Util {
    public static boolean useLatinTheme(Context context){
        return context.getSharedPreferences("pref",MODE_PRIVATE).getBoolean("normalFontTheme",false);
    }
    public static String s18573(){
        return new String(Base64.decode("wqkgMjAyMCBGcmllZHJpY2ggU2VpZGVsLg==", Base64.DEFAULT));
    }
}