package com.demo.business;

public interface IGenericManager<ENTITY, PK> {

    ENTITY read(PK id);

    void create(ENTITY entity);

}
