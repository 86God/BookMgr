package com.company.service;


import com.company.bean.Page;
import com.company.bean.User;

public interface UserService {


    Page<User> queryByIdAndNameOAndSex(String name, String phone, String sex, int age, int pageNo, int pageSize);

    int deleteById(int id);

    int deleteByIdBatch(String[] split);

    int saveUser(String name, String phone, int age, String userSex);

    User queryById(int id);

    int updateUser(String name, String phone, int age, String userSex, int id);
}
