package com.gainetdb.facecheck;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gainetdb.facecheck.dataobject.RequestCompareDto;
import com.gainetdb.facecheck.dataobject.RequestPersonDto;
import com.gainetdb.facecheck.dataobject.Result;
import com.gainetdb.facecheck.service.facecompare.BaiduFaceMatch;
import com.gainetdb.facecheck.service.facecompare.GainetDbFaceMatch;
import com.gainetdb.facecheck.service.facecompare.GetPersonData;
import com.gainetdb.facecheck.utils.Base64ImgUtil;
import com.gainetdb.facecheck.utils.Base64Util;
import com.gainetdb.facecheck.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FacecheckApplicationTests {
    @Autowired
    private BaiduFaceMatch baiduFaceMatch;
	@Autowired
	private GetPersonData getPersonData;
	@Autowired
	private GainetDbFaceMatch gainetDbFaceMatch;
	@Test
	public void contextLoads() throws  Exception {
       // String image1=FileUtil.readFileAsString("D:\\workspacemy\\gainetdbfacecheck\\src\\main\\java\\com\\gainetdb\\facecheck\\service\\facecompare\\1.jpg");
       // String image2=FileUtil.readFileAsString("D:\\workspacemy\\gainetdbfacecheck\\src\\main\\java\\com\\gainetdb\\facecheck\\service\\facecompare\\2.jpg");
		  byte[] bytes1 = FileUtil.readFileByBytes("D:\\workspacemy\\gainetdbfacecheck\\src\\main\\java\\com\\gainetdb\\facecheck\\service\\facecompare\\1.jpg");
		  byte[] bytes2 = FileUtil.readFileByBytes("D:\\workspacemy\\gainetdbfacecheck\\src\\main\\java\\com\\gainetdb\\facecheck\\service\\facecompare\\2.jpg");
		  String image1 = Base64Util.encode(bytes1);
		  String image2 = Base64Util.encode(bytes2);
		baiduFaceMatch.match("aa",image2);
	}
	@Test
	public void getPersonData() {
		RequestPersonDto requestPersonDto=new RequestPersonDto();
		requestPersonDto.setIdCardNo("41012219890726743X");
		Result personData= getPersonData.getPersonData(requestPersonDto);
		log.info("返回结果:{}",personData);

		Base64ImgUtil.GenerateImage(JSONObject.parseObject(JSON.toJSONString(personData.getResult()).toString()).get("XP").toString(),"D://1.jpg");

	}

	@Test
	public void GainetDbFaceCompare() throws  Exception {
		RequestCompareDto requestCompareDto=new RequestCompareDto();
		requestCompareDto.setIdCardNo("41012219890726743X");
		requestCompareDto.setImageBase64( Base64Util.encode(FileUtil.readFileByBytes("D:\\workspacemy\\gainetdbfacecheck\\src\\main\\java\\com\\gainetdb\\facecheck\\service\\facecompare\\1.jpg")));
		requestCompareDto.setUrl("faceComparison");
		requestCompareDto.setRequreid("3d9e4e6c04f7063eb969c6e13a3fd00b");
		JSONObject paramJson=(JSONObject)JSON.toJSON(requestCompareDto);
		Result personData= gainetDbFaceMatch.match(paramJson);
		log.info("景安人脸比对单元测试返回结果:{}",personData);


	}

}
