package com.gainetdb.facecheck.service.facecompare;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import com.gainetdb.facecheck.dataobject.PersonDataDto;
import com.gainetdb.facecheck.dataobject.Result;
import com.gainetdb.facecheck.utils.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class GetPersonData {
    @Autowired
    private PersonDataDto personDataDto;
    public Result getPersonData(Map param){
        log.info("GetPersonData->getPersonData->根据身份证号查询人员信息传入参数:{}",param);
        personDataDto.setParam(param);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", " application/json;charset=UTF-8");
        ResponseEntity<Result> responseEntity =  RestTemplateUtils.post(personDataDto.getPosturl(),headers, JSON.toJSONString(personDataDto),Result.class);
        Result personData=   responseEntity.getBody();
        log.info("GetPersonData->getPersonData->根据身份证号查询人员信息的结果:{}",personData);
        return personData;
    }
}
