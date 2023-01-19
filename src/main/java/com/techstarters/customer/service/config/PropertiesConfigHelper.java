package com.techstarters.customer.service.config;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("ngen")
public class PropertiesConfigHelper {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Setter
    @Getter
    private String customerAddressUrl;

}
