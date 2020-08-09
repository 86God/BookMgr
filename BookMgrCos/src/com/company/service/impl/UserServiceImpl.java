package com.company.service.impl;


import com.company.bean.Page;
import com.company.bean.User;
import com.company.dao.UserDao;
import com.company.dao.impl.UserDaoImpl;
import com.company.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public Page<User> queryByIdAndNameOAndSex(String name, String phone, String sex, int age, int pageNo, int pageSize) {
        Page<User> page = new Page<>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = userDao.queryForPageTotalCount(name,phone,sex,age);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (pageNo - 1) * pageSize;

        // 求当前页数据
        List<User> items = userDao.queryForPageItems(name,phone,sex,age,begin,pageSize);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public int deleteById(int id) {
        return userDao.deleteById(id);
    }

    @Override
    public int deleteByIdBatch(String[] split) {
        return userDao.deleteByIdBeach(split);
    }

    @Override
    public int saveUser(String name, String phone, int age, String userSex) {
        return userDao.save(name,phone,age,userSex);
    }

    @Override
    public User queryById(int id) {
        return userDao.queryById(id);
    }

    @Override
    public int updateUser(String name, String phone, int age, String userSex,int id) {
        return userDao.update(name,phone,age,userSex, id);
    }
}
