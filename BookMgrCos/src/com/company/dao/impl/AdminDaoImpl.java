package com.company.dao.impl;

import com.company.bean.Admin;
import com.company.dao.AdminDao;

public class AdminDaoImpl extends BaseDao implements AdminDao {

    @Override
    public Admin queryByNameAndPassword(String name, String password) {
        String sql = "select name,id,img  from t_admin where name = ? and password = ?";
        return queryForOne(Admin.class,sql,name,password);
    }

    @Override
    public int updateUserImge(String userName, String fileName) {
        String sql = "update t_admin set img = ? where name = ?";
        return update(sql, fileName,userName);
    }
}
