package com.luffy.generalview.ui.bottombar;

public class DemoBean {
    public String name;
    public Class<?> clazz;

    public DemoBean(String name, Class<?> clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return name;
    }
}