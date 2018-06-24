package com.demo.conf;

import org.springframework.context.annotation.*;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan("com.demo")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class SpringJPAConfig {

    @Bean
    @Profile("dev")
    public LocalContainerEntityManagerFactoryBean hsqldbEntityManagerFactory() {
        return getLocalContainerEntityManagerFactoryBean("persistence-unit");
    }

    @Bean
    @Profile("debug")
    public LocalContainerEntityManagerFactoryBean p6spyEntityManagerFactory() {
        return getLocalContainerEntityManagerFactoryBean("persistence-unit-p6spy");
    }

    //if there was only one entity manager factory we could have inject it by param
    @Bean
    @Profile("dev")
    public PlatformTransactionManager hsqldbTransactionManager() {
        return getPlatformTransactionManager(hsqldbEntityManagerFactory());
    }

    @Bean
    @Profile("debug")
    public PlatformTransactionManager p6spyTransactionManager() {
        return getPlatformTransactionManager(p6spyEntityManagerFactory());
    }

    private LocalContainerEntityManagerFactoryBean getLocalContainerEntityManagerFactoryBean(String persistenceUnit) {
        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setPersistenceUnitName(persistenceUnit);
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factory.setJpaVendorAdapter(vendorAdapter);
        return factory;
    }


    private PlatformTransactionManager getPlatformTransactionManager(LocalContainerEntityManagerFactoryBean
                                                                             localContainerEntityManagerFactoryBean) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(localContainerEntityManagerFactoryBean.getObject());
        return transactionManager;
    }


}
