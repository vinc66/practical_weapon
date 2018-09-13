package com.zxc.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * _User: vinc
 * Date: 2018-08-27
 * Time: 17:26
 */
public interface CommonService {

    String uploadPic(CommonsMultipartFile file);

    boolean uploadFile(CommonsMultipartFile file);

    void downLoad(Integer fileId, HttpServletResponse response);

}
