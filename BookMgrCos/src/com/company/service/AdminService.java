package com.company.service;

import com.company.bean.Admin;

public interface AdminService {

    /**
     * 登录
     */
    Admin login(String name, String password);

    //上传修改用户图像
    int updateUserImge(String userName, String fileName);
}
