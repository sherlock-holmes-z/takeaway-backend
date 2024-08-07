package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper {

    /**
     * 根据分类id查询套餐的数量
     * @param id
     * @return
     */
    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);

    void insert(Setmeal setmeal);

    Page<Setmeal> selectPage(SetmealPageQueryDTO pageParam);

    Setmeal selectById(Long id);

    SetmealVO selectSetmealWithDish(Long id);

    void updateById(Setmeal setmeal);

    void deleteBatch(List<Long> ids);

    List<Setmeal> selectList(Setmeal setmeal);

    List<DishItemVO> selectDishItemById(Long id);
}
