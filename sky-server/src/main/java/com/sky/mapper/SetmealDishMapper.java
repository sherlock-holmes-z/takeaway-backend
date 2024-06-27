package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * SetmealDishMapper
 *
 * @author Chocolate
 * @since 2024/6/27 23:03
 */
@Mapper
public interface SetmealDishMapper {


    Integer selectDishCount(List<Long> dishIds);

}
