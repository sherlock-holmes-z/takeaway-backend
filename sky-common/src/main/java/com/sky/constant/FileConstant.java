package com.sky.constant;

import java.io.File;

/**
 * @author chocolate
 * 2024/6/25 16:59
 */
public class FileConstant {

    public static final String UPLOAD_FILE_PATH = System.getProperty("user.dir") + File.separator + "upload" + File.separator;

    public static final String UPLOAD_FILE_URL = "http://localhost:8080/upload/";
}
