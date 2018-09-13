package com.zxc.service.impl;

import com.zxc.cache.VCache;
import com.zxc.service.CommonService;
import com.zxc.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private VCache vCache;

    @Override
    public String uploadPic(CommonsMultipartFile file) {
        String orgName = file.getOriginalFilename().replace(" ", "_");
        if (file.getSize() > Constants.MAX_PIC_SIZE)
            throw new RuntimeException("图片文件不能大于10M");
        String contentType = file.getContentType();
        log.info(contentType);
//        if (!StringUtils.equals(contentType, "image/jpeg") && !StringUtils.equals(contentType, "image/png")) {
//            throw new RuntimeException("图片只支持jpg,png");
//        }
        log.info("fileName：" + orgName);
        String fileName = new Date().getTime() + orgName;
        String savePath = Constants.FILE_PATH + fileName;
        File newFile = new File(savePath);
//        if (!newFile.exists())
//            newFile.mkdirs();
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            throw new RuntimeException("文件上传失败", e);
        }
        log.info("图片保存地址 {}", savePath);
        return savePath;
    }

    @Override
    public boolean uploadFile(CommonsMultipartFile file) {
        String orgName = file.getOriginalFilename().replace(" ", "_");
        log.info("fileName：" + orgName);
        if (file.getSize() > Constants.MAX_FILE_SIZE) {
            throw new RuntimeException("文件尺寸最大为500M");
        }

        String fileName = new Date().getTime() + orgName;
        String savePath = Constants.FILE_PATH + fileName;
        File newFile = new File(savePath);
//        if (!newFile.exists())
//            newFile.mkdirs();
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            throw new RuntimeException("文件上传失败", e);
        }
        log.info("图片保存地址 {}", savePath);
        return true;
    }

    @Override
    public void downLoad(Integer fileId, HttpServletResponse response) {
        File newFile = new File("xxxxx");
        if (!newFile.exists())
            throw new RuntimeException("文件不存在");
        // 以流的形式下载文件。
        InputStream fis = null;
        try {
            fis = new BufferedInputStream(new FileInputStream(newFile));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + "xxxx.doc");
            response.addHeader("Content-Length", "" + newFile.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException e) {
            throw new RuntimeException("文件下载失败");
        }
    }

}
