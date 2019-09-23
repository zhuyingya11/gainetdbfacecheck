package com.gainetdb.facecheck.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result
{
    private int code;
    private String message;
    private Object result;
}
