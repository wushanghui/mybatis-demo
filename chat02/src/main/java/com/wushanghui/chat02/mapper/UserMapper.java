package com.wushanghui.chat02.mapper;

import com.wushanghui.chat02.model.UserModel;

import java.util.List;

/**
 * @author 吴尚慧
 * @since 2022/7/6 13:32
 */
public interface UserMapper {

    int insertUser(UserModel model);

    int updateUser(UserModel model);

    int deleteUser(Long userId);

    List<UserModel> getUserList();
}
