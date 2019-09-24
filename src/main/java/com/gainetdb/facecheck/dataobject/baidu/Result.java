/**
  * Copyright 2019 bejson.com 
  */
package com.gainetdb.facecheck.dataobject.baidu;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Result {

    private float score;
    private List<Face_list> face_list;
}