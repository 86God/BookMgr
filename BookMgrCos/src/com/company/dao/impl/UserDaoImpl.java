package com.company.dao.impl;

import com.company.bean.User;
import com.company.dao.UserDao;
import com.company.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public List<User> queryForPageItems(String name, String phone, String sex, int age, int begin, int pageSize) {

        String sql = "select id,name,phone,sex,age from t_user  where 1 = 1 ";

        List<Object> list = new ArrayList<>();
        if (name != null && name != "") {
            sql += " and name like ? ";
            name = "%" + name + "%";
            list.add(name);
        }
        if (phone != null && phone != "") {
            sql += " and phone like ? ";
            phone = "%" + phone + "%";
            list.add(phone);
        }
        if (sex != null && sex != "") {
            sql += " and sex = ? ";
            list.add(sex);
        }
        if (age != 0) {
            sql += " and age = ? ";
            list.add(age);
        }
        list.add(begin);
        list.add(pageSize);
        sql += " limit ?,? ";

        Connection conn = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = null;
        List<User> userList = new ArrayList<>();
        try {
            preparedStatement = conn.prepareStatement(sql);

            for (int i = 1; i <= list.size(); i++) {
                preparedStatement.setObject(i, list.get(i - 1));
            }
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //  id,name,phone,sex,age
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPhone(resultSet.getString("phone"));
                user.setSex(resultSet.getString("sex"));
                user.setAge(resultSet.getInt("age"));
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (conn != null) {

                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        return userList;

    }

    @Override
    public Integer queryForPageTotalCount(String name, String phone, String sex, int age) {
        String sql = "select count(*) count from t_user  where 1 = 1 ";

        List<Object> list = new ArrayList<>();
        if (name != null && name != "") {
            sql += " and name like ? ";
            name = "%" + name + "%";
            list.add(name);
        }
        if (phone != null && phone != "") {
            sql += " and phone like ? ";
            phone = "%" + phone + "%";
            list.add(phone);
        }
        if (sex != null && sex != "") {
            sql += " and sex = ? ";
            list.add(sex);
        }
        if (age != 0) {
            sql += " and age like ? ";
            list.add(age);
        }

        Connection conn = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = null;

        int count = -1;
        try {
            preparedStatement = conn.prepareStatement(sql);

            for (int i = 1; i <= list.size(); i++) {
                preparedStatement.setObject(i, list.get(i - 1));
            }
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                count = resultSet.getInt("count");

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        } finally {
            if (conn != null) {

                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return count;
    }

    @Override
    public int deleteById(int id) {
        String sql = "delete from t_user where id = ?";
        return update(sql, id);
    }


    @Override
    public int deleteByIdBeach(String[] split) {
        int[] a = new int[split.length];
        if (split.length == 1) {
            String sql = "delete from t_user where id = ?";
            return update(sql, Integer.parseInt(split[0]));
        } else {
            String sql = "delete from t_user where id in ( ";
            for (int i = 0; i < split.length; i++) {
                sql += "? ,";
                a[i] = Integer.parseInt(split[i]);
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";

            Connection conn = JdbcUtils.getConnection();
            PreparedStatement preparedStatement = null;

            try {
                preparedStatement = conn.prepareStatement(sql);

                for (int i = 1; i <= a.length; i++) {
                    preparedStatement.setObject(i, a[i - 1]);
                }
                boolean execute = preparedStatement.execute();
                if (execute) {
                    return 0;
                } else {
                    return 1;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return 0;
            } finally {
                if (conn != null) {

                    try {
                        conn.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public int save(String name, String phone, int age, String userSex) {
        String sql = "insert into t_user(name,phone,sex,age) values(?,?,?,?)";
        return update(sql,name,phone,userSex,age);
    }

    @Override
    public User queryById(int id) {
        String sql = "select * from t_user where id = ?";
        return queryForOne(User.class,sql,id);
    }

    @Override
    public int update(String name, String phone, int age, String userSex,int id) {
        String sql = "update t_user set name = ?,phone = ?,sex = ?,age = ? where id = ?";
        return update(sql,name,phone,userSex,age,id);
    }

}
