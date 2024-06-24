package com.example.butler.entity.util;

import com.example.butler.entity.util.IEntityAdapter;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class DefaultListener {
    @PrePersist
    public void prePersist(Object o) {
        if (o instanceof IEntityAdapter) {
            ((IEntityAdapter) o).setCrateAt(LocalDateTime.now());
            ((IEntityAdapter) o).setUpdateAt(LocalDateTime.now());
        }
    }


    @PreUpdate
    public void preUpdate(Object o) {
        if (o instanceof IEntityAdapter) {
            ((IEntityAdapter) o).setUpdateAt(LocalDateTime.now());
        }
    }
}
