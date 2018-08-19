package com.qchery.ltdco.entity;

/**
 * @author Chery
 * @date 2018/8/19 14:25
 */
public class LtdCo {

    private String name;

    private String code;

    public LtdCo() {
    }

    public LtdCo(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "LtdCo{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
