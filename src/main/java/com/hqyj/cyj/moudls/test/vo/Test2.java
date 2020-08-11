package com.hqyj.cyj.moudls.test.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix ="com")
@PropertySource("classpath:config/applicationTest.properties")
public class Test2 {
    @Value("${com.name}")
    private String name;

    @Value("${com.age}")
    private int age;

    @Value("${com.more}")
    private String more;

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }
}
