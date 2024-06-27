package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * DIshFlavorMapper
 *
 * @author Chocolate
 * @since 2024/6/26 1:05
 */
@Mapper
public interface DishFlavorMapper {

    void insertBatch(List<DishFlavor> dishFlavors);

    void deleteByDishIds(List<Long> dishIds);
}
