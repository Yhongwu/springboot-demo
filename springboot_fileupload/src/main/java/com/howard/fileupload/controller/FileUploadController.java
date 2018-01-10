package com.howard.fileupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.List;

@Controller
public class FileUploadController {
    @RequestMapping("/file")
    public String file(){
        return "file";
    }

    @RequestMapping("/multifile")
    public String multifile(){
        return "mutifile";
    }
    /**
     * 文件上传具体实现方法;
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file")MultipartFile file){
        if(!file.isEmpty()){
            try {
                /*
                 * 图片上传到了工程的跟路径；
                 */
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();

//                String path="E:/"+new Date().getTime()+file.getOriginalFilename();
//
//                File newFile=new File(path);
//                //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
//                file.transferTo(newFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return"上传失败,"+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return"上传失败,"+e.getMessage();
            }
            return"上传成功";
        }else{
            return"上传失败，因为文件是空的.";
        }
    }

    /**
     * 多文件具体上传时间，主要是使用了MultipartHttpServletRequest和MultipartFile
     * @param request
     * @return
     */
    @RequestMapping(value="/batch/upload", method= RequestMethod.POST)
    public@ResponseBody
    String handleFileUpload(HttpServletRequest request){
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i =0; i< files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream =
                            new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream =  null;
                    return"You failed to upload " + i + " => " + e.getMessage();
                }
            } else {
                return"You failed to upload " + i + " because the file was empty.";
            }
        }
        return"upload successful";
    }
}
