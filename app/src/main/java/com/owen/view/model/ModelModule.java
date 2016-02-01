package com.owen.view.model;

/**
 * 模块
 * <p/>
 * Created by Owen on 2015/12/28.
 */
public class ModelModule {

    private String name;
    private Class<?> clazz;

    public ModelModule(String name, Class<?> clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }
}
