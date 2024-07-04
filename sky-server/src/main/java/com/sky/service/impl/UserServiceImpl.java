package com.sky.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.exception.LoginFailedException;
import com.sky.mapper.UserMapper;
import com.sky.properties.JwtProperties;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import com.sky.utils.JwtUtil;
import com.sky.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * UserServiceImpl
 *
 * @author Chocolate
 * @since 2024/7/1 23:41
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    JwtProperties jwtProperties;
    @Autowired
    WeChatProperties weChatProperties;
    @Autowired
    UserMapper userMapper;

    @Override
    public UserLoginVO wxLogin(UserLoginDTO userLoginDTO) {
        // 调用微信接口服务，获取用户的唯一标识openid
        String openid = getOpenid(userLoginDTO);

        // 判断openid是否为空，如果为空，则表示登录失败
        if (StringUtils.isBlank(openid)) {
            throw new LoginFailedException("登录失败");
        }

        // 根据openid查询用户是否为新用户
        // 如果是新用户，自动完成注册，返回用户信息；非新用户，查询用户信息
        User user = userMapper.selectOne(User.builder().openid(openid).build());
        if (user == null) {
            user = User.builder()
                    .openid(openid)
                    .createTime(LocalDateTime.now())
                    .build();
            userMapper.insert(user);
        }

        // 为微信用户生成JWT令牌
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);

        return UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .token(token)
                .build();
    }

    private String getOpenid(UserLoginDTO userLoginDTO) {
        HashMap<String, String> map = new HashMap<>();
        map.put("appid", weChatProperties.getAppid());
        map.put("secret", weChatProperties.getSecret());
        map.put("js_code", userLoginDTO.getCode());
        map.put("grant_type", "authorization_code");
        String doGet = HttpClientUtil.doGet("https://api.weixin.qq.com/sns/jscode2session", map);

        JSONObject jsonObject = JSONObject.parseObject(doGet);
        return jsonObject.getString("openid");
    }
}
