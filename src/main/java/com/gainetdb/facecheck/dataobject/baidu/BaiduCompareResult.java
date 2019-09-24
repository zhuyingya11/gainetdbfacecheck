/**
  * Copyright 2019 bejson.com 
  */
package com.gainetdb.facecheck.dataobject.baidu;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaiduCompareResult {

    private int error_code;
    private String error_msg;
    private long log_id;
    private long timestamp;
    private int cached;
    private Result result;


}