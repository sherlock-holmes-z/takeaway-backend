package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.vo.UserLoginVO;

/**
 * UserService
 *
 * @author Chocolate
 * @since 2024/7/1 23:40
 */
public interface UserService {

    UserLoginVO wxLogin(UserLoginDTO userLoginDTO);
}
