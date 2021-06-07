package com.github.jianglinglong.condition;

import com.github.jianglinglong.beans.Cat;
import com.github.jianglinglong.beans.Dog;
import com.github.jianglinglong.beans.Person;
import com.github.amrjlg.spring.condition.annotation.ConditionOnBean;
import com.github.amrjlg.spring.condition.annotation.ConditionOnBeanMiss;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @author jiang
 * @date 2019/5/30
 * @time 10:07
 */
@Configuration
@ConditionOnBeanMiss
public class Config {

    @Bean
    @ConditionOnBeanMiss
    public Person person() {
        Person person = new Person();
        person.setId(500383199012227330L);
        person.setName("姜玲珑");
        person.setSex(true);
        return person;
    }

    @Bean
    @ConditionOnBeanMiss
    @ConditionOnBean(Person.class)
    public Dog dog(Person person){
        Dog dog = new Dog();
        dog.setPerson(person);
        dog.setName("小狗");
        return dog;
    }
    @Bean
    @DependsOn("person")
    public Cat cat(Person person){
        Cat cat = new Cat();
        cat.setPerson(person);
        cat.setName("小花");
        return cat;
    }

}
