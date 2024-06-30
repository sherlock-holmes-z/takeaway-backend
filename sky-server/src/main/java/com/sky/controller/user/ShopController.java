package com.sky.controller.user;

import com.sky.service.ShopService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ShopController
 *
 * @author Chocolate
 * @since 2024/6/30 17:24
 */
@RestController("userShopController")
@RequestMapping("/user/shop")
@ApiOperation("用户端店铺服务")
public class ShopController {
    @Autowired
    ShopService shopService;

    @GetMapping("/status")
    public Integer getStatus() {
        return shopService.getStatus();
    }
}
