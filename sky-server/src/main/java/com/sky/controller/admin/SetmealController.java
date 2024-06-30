package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.result.Result;

import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SetmealController
 *
 * @author Chocolate
 * @since 2024/6/28 22:15
 */
@RestController
@RequestMapping("/admin/setmeal")
public class SetmealController {
    @Autowired
    SetmealService setmealService;

    @PostMapping("")
    public Result<String> save(@RequestBody SetmealDTO setmealDTO){
        setmealService.saveSetmealWithDish(setmealDTO);
        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult> page(SetmealPageQueryDTO pageQueryDTO){
        PageResult result = setmealService.queryPage(pageQueryDTO);
        return Result.success(result);
    }

    @GetMapping("/{id}")
    public Result<SetmealVO> getById(@PathVariable Long id){
        SetmealVO setmealVO = setmealService.getSetmealWithDish(id);
        return Result.success(setmealVO);
    }

    @PostMapping("/status/{status}")
    public Result<String> updateStatus(@PathVariable Integer status, Long id){
        setmealService.updateStatus(id, status);
        return Result.success();
    }

    @PutMapping("")
    public Result<String> update(@RequestBody SetmealDTO setmealDTO){
        setmealService.updateSetmealWithDish(setmealDTO);
        return Result.success();
    }

    @DeleteMapping("")
    public Result<String> delete(@RequestParam List<Long> ids){
        setmealService.deleteSetmealWithDish(ids);
        return Result.success();
    }
}
