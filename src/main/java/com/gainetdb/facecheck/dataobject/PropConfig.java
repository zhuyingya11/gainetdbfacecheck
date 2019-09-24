package com.gainetdb.facecheck.dataobject;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "propconfig")
public class PropConfig {
    private String getpersonurl;
    /**
     * 前缀url
     */
    private String url;
    private String requreid;
    private String idCardNoBase64;
    private String compareurl;
    private Map param;

}
