package com.vortexbird.vorteam.audit;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class EntityAuditEventConfig implements BeanFactoryAware {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        EntityAuditEventListener.setBeanFactory(beanFactory);
    }
}
