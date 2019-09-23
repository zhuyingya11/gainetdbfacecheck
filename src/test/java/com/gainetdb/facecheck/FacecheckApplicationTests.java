package com.gainetdb.facecheck;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gainetdb.facecheck.dataobject.RequestPersonDataDto;
import com.gainetdb.facecheck.dataobject.RequestPersonDto;
import com.gainetdb.facecheck.dataobject.Result;
import com.gainetdb.facecheck.service.facecompare.FaceMatch;
import com.gainetdb.facecheck.service.facecompare.GainetDbFaceMatch;
import com.gainetdb.facecheck.service.facecompare.GetPersonData;
import com.gainetdb.facecheck.utils.Base64ImgUtil;
import com.gainetdb.facecheck.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
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
	@Autowired
	private GainetDbFaceMatch gainetDbFaceMatch;
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

	@Test
	public void GainetDbFaceCompare() throws  Exception {
		//	attendMachineService.attendMachineCheck();
		RequestPersonDto requestPersonDto=new RequestPersonDto();
		requestPersonDto.setIdCardNo("41012219890726743X");
		requestPersonDto.setIdCardNoBase64(FileUtil.readFileAsString("D:\\workspaceidea2019\\facecheck\\src\\test\\java\\com\\gainetdb\\facecheck\\1.txt"));
		RequestPersonDataDto  requestPersonDataDto=new RequestPersonDataDto();
		requestPersonDataDto.setUrl("getFaceResults/faceResults");
		requestPersonDataDto.setRequreid("3d9e4e6c04f7063eb969c6e13a3fd00b ");
		requestPersonDataDto.setParam(requestPersonDto);
		JSONObject paramJson=(JSONObject)JSON.toJSON(requestPersonDataDto);
		Result personData= gainetDbFaceMatch.match(paramJson);
		log.info("返回结果:{}",personData);


	}

}
