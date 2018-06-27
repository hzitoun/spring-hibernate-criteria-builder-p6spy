package com.demo.business.impl;

import com.demo.business.IGenericManager;
import com.demo.dao.IGenericDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Locale;

public abstract class AbstractGenericManagerImpl<ENTITY, PK> implements IGenericManager<ENTITY, PK> {

    @Resource(name = "messagesBusiness")
    private MessageSource businessMessages;

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGenericManagerImpl.class);

    @Override
    @Transactional(readOnly = true)
    public ENTITY read(PK id) throws IllegalArgumentException {
        LOGGER.info("read by id" + id);
        return this.getDao().read(id);
    }

    @Override
    @Transactional
    public void create(ENTITY entity) {
        LOGGER.info("creating entity" + entity);
        this.getDao().create(entity);
    }

    protected final String getBusinessMessage(final String key, final Object... args) {
        return this.businessMessages.getMessage(key, args, Locale.ROOT);
    }

    protected abstract IGenericDao<ENTITY, PK> getDao();
}
