package com.sky.controller.admin;

import com.sky.constant.FileConstant;
import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * CommonController
 *
 * @author Chocolate
 * @since 2024/6/23 1:34
 */
@RestController
@RequestMapping("/admin/common")
@Slf4j
public class CommonController {

    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        log.info("文件上传:{}", multipartFile);
        FileOutputStream fileOutputStream = null;
        try {
            String filename = multipartFile.getOriginalFilename();
            filename = UUID.randomUUID() + Objects.requireNonNull(filename).substring(filename.lastIndexOf("."));
            File file = new File(FileConstant.uploadFilePath + filename);
            fileOutputStream = new FileOutputStream(file);
            return Result.success(FileConstant.uploadFilePath + filename);
        } catch (Exception e) {
            log.error(e.getMessage());
        }finally {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }

}
