package com.demo.dao.conf;

import org.springframework.context.annotation.*;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;


@Configuration
@ComponentScan("com.demo.dao")
@PropertySource("classpath:application.properties")
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


    private LocalContainerEntityManagerFactoryBean getLocalContainerEntityManagerFactoryBean(String persistenceUnit) {
        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setPersistenceUnitName(persistenceUnit);
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factory.setJpaVendorAdapter(vendorAdapter);
        return factory;
    }


}
