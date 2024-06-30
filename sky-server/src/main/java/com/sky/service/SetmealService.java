package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

import java.util.List;

/**
 * SetMealService
 *
 * @author Chocolate
 * @since 2024/6/29 22:26
 */
public interface SetmealService {

    void saveSetmealWithDish(SetmealDTO setmealDTO);

    PageResult queryPage(SetmealPageQueryDTO pageQueryDTO);

    SetmealVO getSetmealWithDish(Long id);

    void updateStatus(Long id, Integer status);

    void updateSetmealWithDish(SetmealDTO setmealDTO);

    void deleteSetmealWithDish(List<Long> ids);
}
