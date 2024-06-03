package com.example.butler.entity.lisen;

import com.example.butler.entity.IEntityAdapter;
import jakarta.persistence.PrePersist;

import java.time.LocalDateTime;

public class DefaultListener {
    @PrePersist
    public void prePersist(Object o) {
        if (o instanceof IEntityAdapter) {
            ((IEntityAdapter) o).setCrateAt(LocalDateTime.now());
            ((IEntityAdapter) o).setUpdateAt(LocalDateTime.now());
        }
    }

    public void postUpdate(Object o) {
        if (o instanceof IEntityAdapter) {
            ((IEntityAdapter) o).setUpdateAt(LocalDateTime.now());
        }
    }
}
