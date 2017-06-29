package com.kafka_base.common;

import com.kafka_base.Demo.SenderDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangdg
 * @ClassName:
 * @Description:
 * @date 2017-06-11 00:50:22
 */
@Component
public class FileUploadUtil {
//    private FileUploadUtil (){
//    }
//
//    public static final FileUploadUtil instance = new FileUploadUtil();
//
//    public static FileUploadUtil getInstance(){
//        return instance;
//    }
    @Autowired
    private SenderDemo sender;

    public void doSender(){
        System.out.println(sender);
    }

}
