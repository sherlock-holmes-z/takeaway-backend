package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * SetmealServiceImpl
 *
 * @author Chocolate
 * @since 2024/6/29 22:29
 */
@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    SetmealMapper setmealMapper;
    @Autowired
    SetmealDishMapper setmealDishMapper;

    @Override
    @Transactional
    public void saveSetmealWithDish(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealMapper.insert(setmeal);

        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        if (CollectionUtils.isEmpty(setmealDishes)) {
            return;
        }
        setmealDishes.forEach(setmealDish -> {
            setmealDish.setSetmealId(setmeal.getId());
        });
        setmealDishMapper.insertBatch(setmealDishes);
    }

    @Override
    public PageResult queryPage(SetmealPageQueryDTO pageParam) {
        PageHelper.startPage(pageParam.getPage(), pageParam.getPageSize());
        Page<Setmeal> pageResult = setmealMapper.selectPage(pageParam);
        return new PageResult(pageResult.getTotal(), pageResult.getResult());
    }

    @Override
    public SetmealVO getSetmealWithDish(Long id) {
        return setmealMapper.selectSetmealWithDish(id);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Setmeal setmeal = new Setmeal() {{
            setId(id);
            setStatus(status);
        }};
        setmealMapper.updateById(setmeal);
    }

    @Override
    @Transactional
    public void updateSetmealWithDish(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealMapper.updateById(setmeal);

        setmealDishMapper.deleteBySetmealId(setmeal.getId());
        if (CollectionUtils.isEmpty(setmealDTO.getSetmealDishes())){
            return;
        }
        setmealDishMapper.insertBatch(setmealDTO.getSetmealDishes());
    }

    @Override
    public void deleteSetmealWithDish(List<Long> ids) {
        setmealMapper.deleteBatch(ids);
        setmealDishMapper.deleteBySetmealIds(ids);
    }
}
