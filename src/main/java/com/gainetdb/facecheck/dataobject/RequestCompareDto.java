package com.gainetdb.facecheck.dataobject;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
/**
 * 人脸比对传参封装
 */
public class RequestCompareDto {
    private String url;
    private String requreid;
    private String idCardNo;
    private String imageBase64;

}
