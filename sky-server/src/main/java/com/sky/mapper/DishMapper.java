package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     *
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    void insert(Dish dish);

    Page<DishVO> selectPage(DishPageQueryDTO param);

    List<Dish> selectList(Dish dish);

    default Dish selectById(Long id) {
        Dish dish = new Dish();
        dish.setId(id);
        List<Dish> list = selectList(dish);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    Integer selectEnableCount(List<Long> ids);

    void deleteByIds(List<Long> ids);

    void updateById(Dish dish);

    List<DishVO> selectDishWithFlavor(Dish dish);
}
