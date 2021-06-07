package io.github.amrjlg.property;

import io.github.amrjlg.spring.condition.annotation.ConfigurationProperty;
import lombok.Data;

/**
 * @author amrjlg
 * 2019/5/30
 * 13:38
 */
@Data
@ConfigurationProperty(prefix = "properties")
//@PropertySource("file:C:\\Users\\jiang\\Desktop\\application.properties")
public class DefaultProperties {
    private Integer order;
    private String dateFormat;
    private int[] numbers;
}
