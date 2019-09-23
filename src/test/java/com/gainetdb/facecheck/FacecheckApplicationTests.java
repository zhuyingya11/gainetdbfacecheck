package com.gainetdb.facecheck;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gainetdb.facecheck.dataobject.Result;
import com.gainetdb.facecheck.service.facecompare.FaceMatch;
import com.gainetdb.facecheck.service.facecompare.GetPersonData;
import com.gainetdb.facecheck.utils.Base64ImgUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FacecheckApplicationTests {
    @Autowired
    private FaceMatch faceMatch;
	@Autowired
	private GetPersonData getPersonData;
	@Test
	public void contextLoads() {

	    faceMatch.match("","");
	}
	@Test
	public void getPersonData() {
		//	attendMachineService.attendMachineCheck();
		Map param=new HashMap<>();
		param.put("idCardNo","41012219890726743X");
		Result personData= getPersonData.getPersonData(param);
		log.info("返回结果:{}",personData);

		Base64ImgUtil.GenerateImage(JSONObject.parseObject(JSON.toJSONString(personData.getResult()).toString()).get("XP").toString(),"D://1.jpg");

	}

}
