package com.kyh.amyApp.util;

import com.kyh.amyApp.model.UserData;

import java.util.Random;

public class RandData {
    static final String name[] = {
            "홍길동", "김길동", "박길동", "이길동", "최길동"
    };

    static final String tel[] = {
            "010-1111-1234", "010-1234-1234", "010-1111-4444", "010-1111-3333", "010-3333-1234",
    };

    static final String addr[] = {
            "대구 동구 신천 2동", "서구 동구 신천 2동", "부산 동구 신천 2동", "광주 동구 신천 2동", "울산 동구 신천 2동",
    };

    Random r = new Random();

    public String getRandName() {
        return name[r.nextInt(name.length)];
    }

    public String getRandTel() {
        return tel[r.nextInt(tel.length)];
    }

    public String getRandAddr() {
        return addr[r.nextInt(addr.length)];
    }

    public UserData getRandUserData(){
        return new UserData(getRandName(), getRandTel(), getRandAddr());
    }
}
