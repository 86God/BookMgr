package com.company.dao;

import com.company.bean.User;

import java.util.List;

public interface UserDao {

    List<User> queryForPageItems(String name, String phone, String sex, int age, int begin, int pageSize);

    Integer queryForPageTotalCount(String name, String phone, String sex, int age);

    int deleteById(int id);

    int deleteByIdBeach(String[] split);

    int save(String name, String phone, int age, String userSex);

    User queryById(int id);

    int update(String name, String phone, int age, String userSex, int id);
}
