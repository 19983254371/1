package com.hqyj.cyj.moudls.account.service.Impl;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import com.hqyj.cyj.config.ResourceConfigBean;
import com.hqyj.cyj.moudls.account.dao.UserDao;
import com.hqyj.cyj.moudls.account.dao.UserRoleDao;
import com.hqyj.cyj.moudls.account.entity.Role;
import com.hqyj.cyj.moudls.account.entity.User;
import com.hqyj.cyj.moudls.account.service.UserService;
import com.hqyj.cyj.moudls.common.vo.Result;
import com.hqyj.cyj.moudls.common.vo.SearchVo;
import com.hqyj.cyj.utils.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import org.apache.shiro.subject.Subject;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @Description UserServiceImpl
 * @Author HymanHu
 * @Date 2020/8/20 9:01
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private ResourceConfigBean resourceConfigBean;

    @Override
    @Transactional
    public Result<User> insertUser(User user) {
        User userTemp = userDao.getUserByUserName(user.getUserName());
        if (userTemp != null) {
            return new Result<User>(
                    Result.ResultStatus.FAILD.status, "User name is repeat.");
        }

        user.setCreateDate(LocalDateTime.now());
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        userDao.insertUser(user);

        userRoleDao.deleteUserRoleByUserId(user.getUserId());
        List<Role> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()) {
            roles.stream().forEach(item -> {
                userRoleDao.addUserRole(user.getUserId(), item.getRoleId());
            });
        }

        return new Result<User>(
                Result.ResultStatus.SUCCESS.status, "Insert success.", user);
    }

    @Override
    public Result<User> login(User user) {

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken(user.getUserName(),
                        MD5Util.getMD5(user.getPassword()));
        usernamePasswordToken.setRememberMe(user.getRememberMe());

        try {
            subject.login(usernamePasswordToken);
            subject.checkRoles();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<User>(Result.ResultStatus.FAILD.status,
                    "UserName or password is error.");
        }

        Session session = subject.getSession();
        session.setAttribute("user", (User)subject.getPrincipal());

        return new Result<User>(Result.ResultStatus.SUCCESS.status,
                "Login success.", user);

    }

    @Override
    public PageInfo<User> getUsersBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<User>(
                Optional.ofNullable(userDao.getUsersBySearchVo(searchVo))
                    .orElse(Collections.emptyList()));
    }

    @Override
    @Transactional
    public Result<User> updateUser(User user) {
        User userTemp = userDao.getUserByUserName(user.getUserName());
        if (userTemp != null && userTemp.getUserId() != user.getUserId()) {
            return new Result<User>(
                    Result.ResultStatus.FAILD.status, "User name is repeat.");
        }

        userDao.updateUser(user);

        userRoleDao.deleteUserRoleByUserId(user.getUserId());
        List<Role> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()) {
            roles.stream().forEach(item -> {
                userRoleDao.addUserRole(user.getUserId(), item.getRoleId());
            });
        }

        return new Result<User>(
                Result.ResultStatus.SUCCESS.status, "Update success.", user);
    }

    @Override
    @Transactional
    public Result<Object> deleteUser(int userId) {
        userDao.deleteUser(userId);
        userRoleDao.deleteUserRoleByUserId(userId);
        return new Result<>(Result.ResultStatus.SUCCESS.status, "Delete success.");
    }

    @Override
    public User getUserByUserId(int userId) {
        return userDao.getUserByUserId(userId);
    }

    @Override
    public Result<String> uploadUserImg(MultipartFile file) {
        if (file.isEmpty()) {
            return new Result<String>(
                    Result.ResultStatus.FAILD.status, "Please select img.");
        }

        String relativePath = "";
        String destFilePath = "";
        try {
            String osName = System.getProperty("os.name");
            if (osName.toLowerCase().startsWith("win")) {
                destFilePath = resourceConfigBean.getLocationPathForWindows() +
                        file.getOriginalFilename();
            } else {
                destFilePath = resourceConfigBean.getLocationPathForLinux()
                        + file.getOriginalFilename();
            }
            relativePath = resourceConfigBean.getRelativePath() +
                    file.getOriginalFilename();
            File destFile = new File(destFilePath);
            file.transferTo(destFile);

        } catch (IOException e) {
            e.printStackTrace();
            return new Result<String>(
                    Result.ResultStatus.FAILD.status, "Upload FAILD.");
        }

        return new Result<String>(
                Result.ResultStatus.SUCCESS.status, "Upload success.", relativePath);
    }

    @Override
    @Transactional
    public Result<User> updateUserProfile(User user) {
        User userTemp = userDao.getUserByUserName(user.getUserName());
        if (userTemp != null && userTemp.getUserId() != user.getUserId()) {
            return new Result<User>(Result.ResultStatus.FAILD.status, "User name is repeat.");
        }

        userDao.updateUser(user);

        return new Result<User>(Result.ResultStatus.SUCCESS.status, "Edit success.", user);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        Session session = subject.getSession();
        session.setAttribute("user", null);
    }
}
