package com.example.butler.entity;

public interface IEntityAdapter<T> {
    void setCrateAt(T o);
    void setUpdateAt(T o);
}
