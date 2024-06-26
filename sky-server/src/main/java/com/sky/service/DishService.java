package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;

/**
 * DishService
 *
 * @author Chocolate
 * @since 2024/6/25 23:59
 */
public interface DishService {

    void saveDishWithFlavor(DishDTO dishDTO);

    PageResult queryPage(DishPageQueryDTO dishPageQueryDTO);

}
