package com.company.bean;

public class Type {

    private Integer id;

    private Integer type;

    private String typeName;

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + typeName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return typeName;
    }

    public void setName(String name) {
        this.typeName = name;
    }

    public Type(Integer id, Integer type, String typeName) {
        this.id = id;
        this.type = type;
        this.typeName = typeName;
    }

    public Type() {
    }
}
