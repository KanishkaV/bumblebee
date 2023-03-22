package com.bumble.bee.app.models.dao;

public interface Entity<KEY> {

    String getEntityName();

    KEY getId();

    void setId(KEY id);
}
