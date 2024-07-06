package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @author chocolate
 * 2024/6/25 16:54
 */
@RestController
@RequestMapping("/admin/dish")
@Api("菜品管理")
public class DishController {
    private static final Logger log = LoggerFactory.getLogger(DishController.class);
    @Autowired
    DishService dishService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @PostMapping("")
    public Result<String> save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品，菜品信息：{}", dishDTO);
        dishService.saveDishWithFlavor(dishDTO);

        // 清理缓存
        String key = "dish_" + dishDTO.getCategoryId();
        stringRedisTemplate.delete(key);
        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        PageResult page = dishService.queryPage(dishPageQueryDTO);
        return Result.success(page);
    }

    @DeleteMapping("")
    public Result<String> delete(@RequestParam List<Long> ids) {
        log.info("批量删除菜品，ids：{}", ids);
        dishService.deleteBatch(ids);

        // 將所有菜品缓存数据删除
        Set<String> keys = stringRedisTemplate.keys("dish_*");
        stringRedisTemplate.delete(keys);
        return Result.success();
    }


    @PostMapping("/status/{status}")
    public Result<String> updateStatus(@PathVariable Integer status, Long id) {
        dishService.updateStatus(id,status);

        // 將所有菜品缓存数据删除
        Set<String> keys = stringRedisTemplate.keys("dish_*");
        stringRedisTemplate.delete(keys);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<DishVO> getById(@PathVariable Long id){
        Dish dish = new Dish();
        dish.setId(id);
        DishVO dishWithFlavor = dishService.getDishWithFlavor(dish);
        return Result.success(dishWithFlavor);
    }

    @PutMapping("")
    public Result<String> updateDish(@RequestBody DishDTO dishDTO){
        log.info("修改菜品信息：{}", dishDTO);
        dishService.updateDishWithFlavor(dishDTO);

        // 將所有菜品缓存数据删除
        Set<String> keys = stringRedisTemplate.keys("dish_*");
        stringRedisTemplate.delete(keys);
        return Result.success();
    }


    @GetMapping("/list")
    public Result<List<Dish>> list(Long categoryId) {
        List<Dish> list = dishService.listByCategoryId(categoryId);
        return Result.success(list);
    }
}
