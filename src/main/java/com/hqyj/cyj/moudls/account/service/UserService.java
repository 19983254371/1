package com.hqyj.cyj.moudls.account.service;

import com.github.pagehelper.PageInfo;

import com.hqyj.cyj.moudls.account.entity.User;
import com.hqyj.cyj.moudls.common.vo.Result;
import com.hqyj.cyj.moudls.common.vo.SearchVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description UserService
 * @Author HymanHu
 * @Date 2020/8/20 9:01
 */
public interface UserService {

    Result<User> insertUser(User user);

    Result<User> login(User user);

    PageInfo<User> getUsersBySearchVo(SearchVo searchVo);

    Result<User> updateUser(User user);

    Result<Object> deleteUser(int userId);

    User getUserByUserId(int userId);

    Result<String> uploadUserImg(MultipartFile file);

    Result<User> updateUserProfile(User user);

    User getUserByUserName(String userName);

    void logout();
}
