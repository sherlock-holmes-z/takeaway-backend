package com.sky.controller.admin;

import com.sky.result.Result;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * CommonController
 *
 * @author Chocolate
 * @since 2024/6/23 1:34
 */
@RestController
@RequestMapping("/admin/common")
public class CommonController {

    private static final String uploadFilePath = System.getProperty("user.dir") + File.separator + "upload" + File.separator;

    @PostMapping("/upload")
    @ConditionalOnMissingBean
    public Result<String> upload(MultipartFile multipartFile) throws IOException {
        System.out.println("1");
        byte[] bytes = multipartFile.getBytes();
        String filename = multipartFile.getOriginalFilename();
        filename = Objects.requireNonNull(filename).substring(filename.lastIndexOf(".") + 1);
        File file = new File(uploadFilePath + filename);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(bytes);
        }
        return Result.success();
    }

}
