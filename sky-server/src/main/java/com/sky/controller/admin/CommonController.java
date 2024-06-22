package com.sky.controller.admin;

import com.sky.result.Result;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * CommonController
 *
 * @author Chocolate
 * @since 2024/6/23 1:34
 */
@RestController
@RequestMapping("/admin/common")
public class CommonController {

    @PostMapping("/upload")
    @ConditionalOnMissingBean
    public Result<String> upload(MultipartFile file){
        // todo upload
        return Result.success();
    }
}
