package com.example.core.common.enums;

import java.util.Arrays;

public enum Inspection implements EnumModel {

    ACCEPT("수락"),
    STANDBY("대기"),
    REJECT("거절");

    private String value;

    Inspection(String value) {
        this.value = value;
    }


    public static Inspection fromCode(String dbData) {
        return Arrays.stream(Inspection.values())
                .filter(v -> v.getValue().equals(dbData))
                .findAny()
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
