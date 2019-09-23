package com.gainetdb.facecheck.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gainetdb.facecheck.dataobject.BaiduCompareResult;
import com.gainetdb.facecheck.dataobject.PersonDataDto;
import com.gainetdb.facecheck.dataobject.Result;
import com.gainetdb.facecheck.service.facecompare.FaceMatch;
import com.gainetdb.facecheck.service.facecompare.GetPersonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class FaceCompareController {
      @Autowired
      private PersonDataDto personDataDto;
    @Autowired
    private GetPersonData getPersonData;
    @Autowired
    private FaceMatch faceMatch;
      @PostMapping("/faceCompare")
      public Result faceCompare(@RequestBody JSONObject jsonObject){
          String url=jsonObject.getString("url");
          String requreid=jsonObject.getString("requreid");
          JSONObject param=jsonObject.getJSONObject("param");
          String idCardNo=param.getString("idCardNo");
          String idCardNoBase64=param.getString("idCardNoBase64");//现场照片
          Result personData= getPersonData.getPersonData(param);
          if (personData.getResult()==null){
              return new Result(-1,"未查询到此人员信息","");
          }
          String result =faceMatch.match(idCardNoBase64,JSONObject.parseObject(JSON.toJSONString(personData.getResult()).toString()).get("XP").toString());
          log.info("人脸对比结果Result：{}",result);
          JSONObject jsonResult=JSONObject.parseObject(result);
          log.info("百度比对人脸比对结果{}",jsonObject.getJSONObject("result"));
          BaiduCompareResult  baiduCompareResult=JSONObject.toJavaObject(jsonObject.getJSONObject("result"),BaiduCompareResult.class);

          return null;
      }
}
