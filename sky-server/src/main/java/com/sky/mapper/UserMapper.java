package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * UserMapper
 *
 * @author Chocolate
 * @since 2024/7/2 1:11
 */
@Mapper
public interface UserMapper {

    default User selectOne(User user) {
        List<User> users = selectList(user);
        return CollectionUtils.isEmpty(users) ? null : users.get(0);
    }


    List<User> selectList(User user);

    default void insert(User user){
        insertBatch(Collections.singletonList(user));
    }

    void insertBatch(List<User> users);
}
