package com.gainetdb.facecheck.service.facecompare;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gainetdb.facecheck.dataobject.PropConfig;
import com.gainetdb.facecheck.dataobject.Result;
import com.gainetdb.facecheck.utils.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
* 人脸对比
*/
@Component
@Slf4j
public class GainetDbFaceMatch {
    @Autowired
    private PropConfig propConfig;
    /**
     * 景安大数据人脸比对方法
     * @param requestCompareDto
     * @return
     */
    public  Result match(JSONObject requestCompareDto) {

        log.info("GainetDbFaceMatch->match->景安大数据人脸比对方法传入参数:{}",requestCompareDto);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", " application/json;charset=UTF-8");
        ResponseEntity<Result> responseEntity =  RestTemplateUtils.post(propConfig.getCompareurl(),headers, JSON.toJSONString(requestCompareDto),Result.class);
        Result personData=   responseEntity.getBody();
        log.info("GainetDbFaceMatch->match->景安大数据人脸比对方法执行的的结果:{}",personData);
        return personData;

    }

    public static void main(String[] args) {



    }
}