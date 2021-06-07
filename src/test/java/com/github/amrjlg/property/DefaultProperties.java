package com.github.jianglinglong.property;

import com.github.amrjlg.spring.condition.annotation.ConfigurationProperty;
import lombok.Data;

/**
 * @author jiang
 * @date 2019/5/30
 * @time 13:38
 */
@Data
@ConfigurationProperty(prefix = "properties")
//@PropertySource("file:C:\\Users\\jiang\\Desktop\\application.properties")
public class DefaultProperties {
    private Integer order;
    private String dateFormat;
    private int[] numbers;
}
