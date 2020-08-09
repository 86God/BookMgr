package com.company.dao;

import com.company.bean.Admin;

public interface AdminDao {

    /**
     * 查询管理员
     */
    Admin queryByNameAndPassword(String name, String password);

    int updateUserImge(String userName, String fileName);
}
