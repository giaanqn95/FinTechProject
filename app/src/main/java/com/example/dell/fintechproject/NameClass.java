package com.example.dell.fintechproject;


public enum NameClass {

    SECOND(1, SecondActivity.class),
    THIRD(2, ThirdActivity.class),
    FOUR(3, FourActivity.class);


    private int key;
    private Class name;

    NameClass(int key, Class name) {
        this.key = key;
        this.name = name;
    }

    public static NameClass filter(int key) {
        for (NameClass item : values()) {
            if (item.key == key) {
                return item;
            }
        }
        return SECOND;
    }

    public int getKey() {
        return key;
    }

    public Class getName() {
        return name;
    }
}
