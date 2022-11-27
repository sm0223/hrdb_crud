package com.shux.hms.Util;

public class Result {
    int resCode;
    String res;
    String data;

    public Result() {
    }

    public Result(int resCode, String res) {
        this.resCode = resCode;
        this.res = res;
    }

    public Result(int resCode, String res, String data) {
        this.resCode = resCode;
        this.res = res;
        this.data = data;
    }

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    @Override
    public String toString() {
        return "{" +
                "\"resCode\" :" + resCode +
                ",\"result\" :" + res +
                ",\"data\" :" + data +
                "}";
    }
}
