package com.springboot.auth.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum Gender {
    MALE("M"), FEMALE("F"), OTHER("O");
    private String code;

    Gender(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public static Map<String, Gender> getGradeList(){
        Map<String, Gender> genderMap = new HashMap<>();
        for (Gender gender : Gender.values()) {
            genderMap.put(gender.getCode(), gender);
        }
        return genderMap;
    }
}
