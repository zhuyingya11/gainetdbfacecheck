package com.gainetdb.facecheck.service.facecompare;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gainetdb.facecheck.dataobject.baidu.BaiduCompareResult;
import com.gainetdb.facecheck.utils.Base64Util;
import com.gainetdb.facecheck.utils.FileUtil;
import com.gainetdb.facecheck.utils.GsonUtils;
import com.gainetdb.facecheck.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BaiduFaceMatch {
    @Autowired
    private AuthService authService;

    /**
    * 重要提示代码中所需工具类
    * FileUtil,Base64Util,HttpUtil,GsonUtils请从
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * 下载
    */
    public BaiduCompareResult match(String image1, String image2) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {
            int i=1/0;
            List<Map<String, Object>> images = new ArrayList<>();
            Map<String, Object> map1 = new HashMap<>();
            map1.put("image", image1);
            map1.put("image_type", "BASE64");
            map1.put("face_type", "LIVE");
            map1.put("quality_control", "LOW");
            map1.put("liveness_control", "NORMAL");

            Map<String, Object> map2 = new HashMap<>();
            map2.put("image", image2);
            map2.put("image_type", "BASE64");
            map2.put("face_type", "LIVE");
            map2.put("quality_control", "LOW");
            map2.put("liveness_control", "NORMAL");
            images.add(map1);
            images.add(map2);
            String param = GsonUtils.toJson(images);
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = authService.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            log.info("BaiduFaceMatch->match百度人脸识别方法返回结果:{} ",result);
            BaiduCompareResult  baiduCompareResult= JSON.parseObject(JSONObject.parseObject(result).toJSONString(),BaiduCompareResult.class);
            return baiduCompareResult;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {



    }
}