package com.example.patternforge.service.pattern;

public enum ProgrammingLanguage {

    JAVA("java"),
    CPP("cpp");

    private final String value;

    ProgrammingLanguage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
