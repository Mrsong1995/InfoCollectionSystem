package com.key;

import com.key.common.base.service.AccountManager;
import org.hibernate.SessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;


@ImportResource(locations = {"classpath:/conf/applicationContext.xml","classpath:/conf/applicationContext-dwsurvey.xml"})
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.key.dwsurvey.mapper")
@Configuration
public class IcsApplication {

    public static void main(String[] args) {
        SpringApplication.run(IcsApplication.class, args);
    }

}
