package com.jiang.property;

import com.jiang.spring.condition.annotation.ConfigurationProperty;
import lombok.Data;

/**
 * @author jiang
 * @date 2019/5/30
 * @time 13:38
 */
@Data
@ConfigurationProperty(prefix = "")
public class DefaultProperties {
    private Integer order;
    private String dateFormat;
}
