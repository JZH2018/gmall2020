package com.jiang.gmall.manage.util;


import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class PmsUploadUtil {

    public static String uploadImage(MultipartFile multipartFile){

        String imgUrl ="http://192.168.43.251";

        //上传图片
        String file = PmsUploadUtil.class.getResource("/tracker.conf").getFile();
        try {
            ClientGlobal.init(file);
            TrackerClient trackerClient=new TrackerClient();
            TrackerServer trackerServer = trackerClient.getTrackerServer();
            StorageClient storageClient=new StorageClient(trackerServer,null);
            byte[] bytes = multipartFile.getBytes();    //获取上传的二进制对象
            String filename = multipartFile.getOriginalFilename();
            int i = filename.lastIndexOf(".");
            String extName = filename.substring(i+1);
            String[] upload_file = storageClient.upload_file(bytes, extName, null);
            for (String uploadInfo : upload_file) {
                imgUrl+="/"+uploadInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return imgUrl;

    }
}
