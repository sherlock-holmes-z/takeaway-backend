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
    public Result<String> upload(MultipartFile file) {
        log.info("文件上传:{}", file);

        String filename = file.getOriginalFilename();
        filename = UUID.randomUUID() + Objects.requireNonNull(filename).substring(filename.lastIndexOf("."));
        File upFile = new File(FileConstant.UPLOAD_FILE_PATH + filename);
        try (FileOutputStream fileOutputStream = new FileOutputStream(upFile)) {
            fileOutputStream.write(file.getBytes());
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error(MessageConstant.UPLOAD_FAILED);
        }
        return Result.success(FileConstant.UPLOAD_FILE_URL + filename);
    }

}
