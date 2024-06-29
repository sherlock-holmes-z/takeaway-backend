package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * DishServiceImpl
 *
 * @author Chocolate
 * @since 2024/6/26 0:00
 */
@Service
public class DishServiceImpl implements DishService {
    @Autowired
    DishMapper dishMapper;
    @Autowired
    DishFlavorMapper flavorMapper;
    @Autowired
    SetmealDishMapper setmealDishMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDishWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        dishMapper.insert(dish);

        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (!CollectionUtils.isEmpty(flavors)) {
            flavors.forEach(f -> f.setDishId(dish.getId()));
            flavorMapper.insertBatch(dishDTO.getFlavors());
        }
    }

    @Override
    public PageResult queryPage(DishPageQueryDTO param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());
        Page<DishVO> page = dishMapper.selectPage(param);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    @Transactional
    public void deleteBatch(List<Long> ids) {
        // 启用中的菜品不能删除
        Integer count = dishMapper.selectEnableCount(ids);
        if (count != ids.size()) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
        }

        // 存在套餐的菜品不能删除
        Integer dishCount = setmealDishMapper.selectDishCount(ids);
        if (dishCount > 0) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }

        // 删除菜品，菜品口味
        dishMapper.deleteByIds(ids);
        flavorMapper.deleteByDishIds(ids);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Dish dish = new Dish();
        dish.setId(id);
        dish.setStatus(status);
        dishMapper.updateById(dish);
    }

    @Override
    public DishVO getDishWithFlavor(Long id) {
        return dishMapper.selectDishWithFlavor(id);
    }

    @Override
    @Transactional
    public void updateDishWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.updateById(dish);

        // 清空口味，再添加
        flavorMapper.deleteByDishIds(Collections.singletonList(dishDTO.getId()));
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (!CollectionUtils.isEmpty(flavors)) {
            flavors.forEach(f -> f.setDishId(dish.getId()));
            flavorMapper.insertBatch(flavors);
        }
    }

    @Override
    public List<Dish> listByCategoryId(Long categoryId) {
        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(StatusConstant.ENABLE);
        return dishMapper.selectList(dish);
    }
}
