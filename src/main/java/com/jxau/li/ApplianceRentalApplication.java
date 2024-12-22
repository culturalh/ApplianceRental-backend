package com.jxau.li;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jxau.li.mapper")
public class ApplianceRentalApplication {


    private static final Logger logger = LoggerFactory.getLogger(ApplianceRentalApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ApplianceRentalApplication.class, args);
        logger.info("ApplianceRentalApplication 启动成功...");
    }


}
