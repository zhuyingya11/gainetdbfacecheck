package com.gainetdb.facecheck.dataobject;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "personconfig")
public class PersonDataDto {
    private String posturl;
    private String url;
    private String requreid;
    private String idCardNoBase64;
    private Map param;

}
