package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chocolate
 * 2024/6/25 16:54
 */
@RestController
@RequestMapping("/admin/dish")
@ApiOperation("菜品管理")
public class DishController {

    @PostMapping("")
    public Result<String> save(@RequestBody DishDTO dishDTO){
        return Result.success();
    }
}
