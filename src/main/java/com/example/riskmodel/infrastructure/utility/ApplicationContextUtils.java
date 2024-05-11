package com.example.riskmodel.infrastructure.utility;

import jakarta.annotation.PostConstruct;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtils {

    private static ApplicationContextUtils contextUtils;

    @PostConstruct
    private void getInstance() {
        contextUtils = this;
    }

    public static ApplicationContext getApplicationContext() {
        return ApplicationContextProvider.getApplicationContext();
    }

}
