package com.gainetdb.facecheck.dataobject;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestPersonDataDto {
    private String url;
    private String requreid;
    private RequestPersonDto param;

}
