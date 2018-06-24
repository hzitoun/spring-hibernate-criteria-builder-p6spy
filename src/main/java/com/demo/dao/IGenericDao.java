package com.demo.dao;

public interface IGenericDao<ENTITY, PK> {
    void create(ENTITY newInstance);

    ENTITY read(PK id);
}
