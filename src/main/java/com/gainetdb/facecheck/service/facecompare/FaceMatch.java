package com.gainetdb.facecheck.service.facecompare;

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
public class FaceMatch {
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
    public  String match(String image1,String image2) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {

          //  byte[] bytes1 = FileUtil.readFileByBytes("D:\\workspacemy\\facecheck\\src\\main\\java\\com\\gainetdb\\facecheck\\service\\facecompare\\1.jpg");
          //  byte[] bytes2 = FileUtil.readFileByBytes("D:\\workspacemy\\facecheck\\src\\main\\java\\com\\gainetdb\\facecheck\\service\\facecompare\\2.jpg");
           // String image1 = Base64Util.encode(bytes1);
           // String image2 = Base64Util.encode(bytes2);

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
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {



    }
}