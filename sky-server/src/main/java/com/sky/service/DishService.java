package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

/**
 * DishService
 *
 * @author Chocolate
 * @since 2024/6/25 23:59
 */
public interface DishService {

    void saveDishWithFlavor(DishDTO dishDTO);

    PageResult queryPage(DishPageQueryDTO dishPageQueryDTO);

    void deleteBatch(List<Long> ids);

    void updateStatus(Long id, Integer status);

    DishVO getDishWithFlavor(Long id);

    void updateDishWithFlavor(DishDTO dishDTO);

    List<Dish> listByCategoryId(Long categoryId);
}
