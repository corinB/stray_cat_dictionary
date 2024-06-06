package com.example.butler.entity.util;

public interface IEntityAdapter<T> {
    void setCrateAt(T o);
    void setUpdateAt(T o);
}
