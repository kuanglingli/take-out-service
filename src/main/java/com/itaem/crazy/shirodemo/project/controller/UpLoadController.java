package com.itaem.crazy.shirodemo.project.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UpLoadController {

    @PostMapping("/imgUpload")
    public String imgUpload(@RequestParam("file") MultipartFile file){
        String fileName = file.getOriginalFilename();
        String[] arr = fileName.split("\\.");
        String type = arr[arr.length-1];
        fileName = System.currentTimeMillis()+"."+type;
//        fileName = System.currentTimeMillis()+"";
        // 构建上传文件的存放 "文件夹" 路径
        String fileDirPath = new String("src/main/resources/static/file");

        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        String path = fileDir.getAbsolutePath() + File.separator + fileName;
        try {
            // 构建真实的文件路径
            File newFile = new File(path);
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/file/" + fileName;
    }

}
