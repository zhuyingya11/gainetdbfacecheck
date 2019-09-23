package com.gainetdb.facecheck.dataobject;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestPersonDto {
    private String idCardNo;
    private String idCardNoBase64;

}
