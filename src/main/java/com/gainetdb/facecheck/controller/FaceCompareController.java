package com.gainetdb.facecheck.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gainetdb.facecheck.dataobject.*;
import com.gainetdb.facecheck.dataobject.baidu.BaiduCompareResult;
import com.gainetdb.facecheck.service.facecompare.BaiduFaceMatch;
import com.gainetdb.facecheck.service.facecompare.GainetDbFaceMatch;
import com.gainetdb.facecheck.service.facecompare.GetPersonData;
import com.gainetdb.facecheck.utils.BigDecimalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@Slf4j
public class FaceCompareController {
    @Autowired
    private GetPersonData getPersonData;
    @Autowired
    private GainetDbFaceMatch gainetDbFaceMatch;
    @Autowired
    private BaiduFaceMatch baiduFaceMatch;
      @PostMapping("/faceCompare")
      public Result faceCompare(@RequestBody JSONObject jsonObject){
          RequestCompareDto requestCompareDto=JSON.parseObject(jsonObject.toJSONString(),RequestCompareDto.class);
          Result personData= getPersonData.getPersonData(new RequestPersonDto(requestCompareDto.getIdCardNo()));
          if (personData.getResult()==null){
              return new Result(-1,"未查询到此人员信息","");
          }
          BaiduCompareResult baiduCompareResult =baiduFaceMatch.match(requestCompareDto.getImageBase64(),JSONObject.parseObject(JSON.toJSONString(personData.getResult()).toString()).get("XP").toString());
          log.info("百度人脸对比结果Result：{}",baiduCompareResult);

          if (baiduCompareResult==null||baiduCompareResult.getError_code()!=0){
              if (baiduCompareResult!=null){
                  log.info("百度人脸对比结果出现异常错误码为：{},错误信息为:{}",baiduCompareResult.getError_code(),baiduCompareResult.getError_msg());
              }
              //百度的发生异常调用景安的服务
              Result result=gainetDbFaceMatch.match(jsonObject);
              return result;
          }
          else {
              float baiduScore= baiduCompareResult.getResult().getScore();
              if (BigDecimalUtil.subtract(baiduScore,80)>0d){
                  return new Result(1,"人脸比对成功","");
              }else{
                  return new Result(-1,"人脸比对不通过","");
              }


          }
      }



}
