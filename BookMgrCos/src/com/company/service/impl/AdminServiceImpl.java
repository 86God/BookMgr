package com.company.service.impl;

import com.company.bean.Admin;
import com.company.dao.AdminDao;
import com.company.dao.impl.AdminDaoImpl;
import com.company.service.AdminService;

public class AdminServiceImpl implements AdminService {

    AdminDao adminDao = new AdminDaoImpl();

    @Override
    public Admin login(String name, String password) {
        return adminDao.queryByNameAndPassword(name,password);
    }

    @Override
    public int updateUserImge(String userName, String fileName) {
        return adminDao.updateUserImge(userName,fileName);
    }
}
