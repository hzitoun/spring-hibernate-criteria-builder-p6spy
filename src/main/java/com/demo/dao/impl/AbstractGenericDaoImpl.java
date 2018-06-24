package com.demo.dao.impl;

import com.demo.dao.IGenericDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;


public class AbstractGenericDaoImpl<ENTITY, PK extends Serializable> implements IGenericDao<ENTITY, PK> {


    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGenericDaoImpl.class.getName());

    @PersistenceContext
    protected EntityManager em;


    private Class<ENTITY> entityClass;


    public AbstractGenericDaoImpl(final Class<ENTITY> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void create(final ENTITY newInstance) {
        try {
            this.em.persist(newInstance);
        } catch (final Exception ex) {
            LOGGER.error(ex.getMessage(), ex.fillInStackTrace());
            throw ex;
        }
    }


    @Override
    public ENTITY read(final PK id) {
        if (id == null) {
            throw new IllegalArgumentException("L'identifiant de PK est NULL pour le read");
        }
        try {
            return this.em.find(this.entityClass, id);
        } catch (final Exception ex) {
            LOGGER.error(ex.getMessage(), ex.fillInStackTrace());
            throw ex;
        }
    }

}
