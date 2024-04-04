package com.example.helper;

public class PrivacyHelper {

    public static String idcard(String idcard) {
        if (idcard == null) {
            return null;
        }
        if (idcard.length() == 18) {
            return idcard.substring(0, 4) + "**********" + idcard.substring(14);
        } else if (idcard.length() == 15) {
            return idcard.substring(0, 4) + "********" + idcard.substring(11);
        } else {
            return idcard;
        }
    }
}
