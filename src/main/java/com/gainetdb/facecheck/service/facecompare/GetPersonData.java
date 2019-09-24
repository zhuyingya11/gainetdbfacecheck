package com.gainetdb.facecheck.service.facecompare;

import com.alibaba.fastjson.JSON;
import com.gainetdb.facecheck.dataobject.RequestPersonDataDto;
import com.gainetdb.facecheck.dataobject.RequestPersonDto;
import com.gainetdb.facecheck.dataobject.PropConfig;
import com.gainetdb.facecheck.dataobject.Result;
import com.gainetdb.facecheck.utils.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetPersonData {
    @Autowired
    private RequestPersonDataDto requestPersonDataDto;
    @Autowired
    private PropConfig propConfig;
    public Result getPersonData(RequestPersonDto requestPersonDto){
        log.info("GetPersonData->getPersonData->根据身份证号查询人员信息传入参数:{}",requestPersonDto);
        requestPersonDataDto.setRequreid(propConfig.getRequreid());
        requestPersonDataDto.setUrl(propConfig.getUrl());
        requestPersonDataDto.setParam(requestPersonDto);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", " application/json;charset=UTF-8");
        ResponseEntity<Result> responseEntity =  RestTemplateUtils.post(propConfig.getGetpersonurl(),headers, JSON.toJSONString(requestPersonDataDto),Result.class);
        Result personData=   responseEntity.getBody();
        log.info("GetPersonData->getPersonData->根据身份证号查询人员信息的结果:{}",personData);
        return personData;
    }
}
