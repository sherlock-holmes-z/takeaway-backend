package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @Autowired
    DishService dishService;

    @PostMapping("")
    public Result<String> save(@RequestBody DishDTO dishDTO){
        dishService.saveDishWithFlavor(dishDTO);
        return Result.success();
    }

    @GetMapping
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        PageResult page = dishService.queryPage(dishPageQueryDTO);
        return Result.success(page);
    }

//    @PutMapping("")
//    public Result<String> update(@RequestBody DishDTO dishDTO){
//        dishService.updateDishWithFlavor(dishDTO);
//        return Result.success();
//    }


}
