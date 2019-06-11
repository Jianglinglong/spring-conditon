package com.jiang.condition;

import com.jiang.beans.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiang
 * @date 2019/6/11
 * @time 9:30
 */
@Configuration
public class ConfigPerson {
    @Bean
    public Person person(){
        return new Person();
    }
}
