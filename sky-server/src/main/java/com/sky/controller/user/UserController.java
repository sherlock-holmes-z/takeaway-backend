package com.sky.controller.user;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.result.Result;
import com.sky.service.UserService;
import com.sky.vo.UserLoginVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * UserController
 *
 * @author Chocolate
 * @since 2024/7/1 23:18
 */
@RestController
@RequestMapping("/user/user")
@ApiOperation("用户服务")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO dto) {
        log.info("微信用户登录：{}", dto.getCode());
        UserLoginVO userLoginVO = userService.wxLogin(dto);
        return Result.success(userLoginVO);
    }


}
