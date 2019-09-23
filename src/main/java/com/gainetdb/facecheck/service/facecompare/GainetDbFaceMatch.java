package com.gainetdb.facecheck.service.facecompare;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gainetdb.facecheck.dataobject.PersonDataDto;
import com.gainetdb.facecheck.dataobject.Result;
import com.gainetdb.facecheck.utils.GsonUtils;
import com.gainetdb.facecheck.utils.HttpUtil;
import com.gainetdb.facecheck.utils.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 人脸对比
*/
@Component
@Slf4j
public class GainetDbFaceMatch {
    @Autowired
    private AuthService authService;
    @Autowired
    private PersonDataDto personDataDto;
    /**
     * 景安大数据人脸比对方法
     * @param image1
     * @param image2
     * @return
     */
    public  Result match(JSONObject requestPersonDataDto) {

        log.info("GainetDbFaceMatch->match->景安大数据人脸比对方法传入参数:{}",param);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", " application/json;charset=UTF-8");
        ResponseEntity<Result> responseEntity =  RestTemplateUtils.post(personDataDto.getPosturl(),headers, JSON.toJSONString(requestPersonDataDto),Result.class);
        Result personData=   responseEntity.getBody();
        log.info("GainetDbFaceMatch->match->景安大数据人脸比对方法执行的的结果:{}",personData);
        return personData;

    }

    public static void main(String[] args) {



    }
}