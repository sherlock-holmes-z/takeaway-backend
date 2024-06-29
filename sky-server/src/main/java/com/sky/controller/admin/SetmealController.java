package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.result.Result;

import com.sky.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
