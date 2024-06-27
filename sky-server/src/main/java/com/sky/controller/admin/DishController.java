package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Result<String> save(@RequestBody DishDTO dishDTO) {
        dishService.saveDishWithFlavor(dishDTO);
        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        PageResult page = dishService.queryPage(dishPageQueryDTO);
        return Result.success(page);
    }

    @DeleteMapping("")
    public Result<String> delete(@RequestParam List<Long> ids) {
        dishService.deleteBatch(ids);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    public Result<String> updateStatus(@PathVariable Integer status, Long id) {
        dishService.updateStatus(id,status);
        return Result.success();
    }

}
