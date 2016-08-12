package com.trimph.toprand.trimphrxandroid.trimph.ui.main.person.excption;

/**
 * Created by tao on 2016/8/11.
 */

public class HttpResutlException extends RuntimeException {


    public HttpResutlException(int code) {
        this(ResultErrorException(code));
    }

    public HttpResutlException(String detailMessage) {
        super(detailMessage);
    }


    public static String ResultErrorException(int code) {
        switch (code) {
            case 1:
                return "返回数据错误";
            default:
                return "返回数据错误";
        }
    }
}
