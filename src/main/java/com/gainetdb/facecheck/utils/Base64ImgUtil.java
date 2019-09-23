package com.gainetdb.facecheck.utils;

import java.io.*;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64ImgUtil {
    public static void main(String[] args) {
        String strImg = GetImageStr("D:\\1.jpg");
        System.out.println(strImg);
       // GenerateImage(strImg,"F:\\2-1111.jpg");
    }
    /**
     *  图片转化成base64字符串
     * @param imgFile - 转换的图片路径
     * @return imgStr --图片转换后的二进制字节
     */
    public static String GetImageStr(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }
    /**
     * 对字节数组字符串进行Base64解码并生成图片
     * @param imgStr - 图片二进制字节 imgFilePath-新生成的图片路径
     * @return
     */
    public static boolean GenerateImage(String imgStr,String imgFilePath) {
        if (imgStr == null){
            return false;// 图像数据为空
        } 
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
