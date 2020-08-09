package com.company.dao.impl;


import com.company.bean.Books;
import com.company.bean.LogCountByTime;
import com.company.bean.LogResult;
import com.company.dao.LogDao;
import com.company.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class LogDaoImpl extends BaseDao implements LogDao {
    @Override
    public Integer queryByIdAndNameForPageTotalCount(String bookName, String userName, Integer status, String createTime, String backTime,Integer userId) {
        String sql = " SELECT count(*) count FROM t_log l \n" +
                "LEFT JOIN t_user u \n" +
                "on u.id = l.user_id\n" +
                "LEFT JOIN t_books b\n" +
                "on b.id = l.book_id where 1 = 1 ";

        List<Object> list = new ArrayList<>();
        if (bookName != null && bookName != "") {
            sql += " and b.name like ? ";
            bookName = "%" + bookName +"%";
            list.add(bookName);
        }

        if (userName != null && userName != "") {
            sql += " and u.name like ? ";
            userName = "%" + userName +"%";
            list.add(userName);
        }
        if (status == 0 || status == 1) {
            sql += " and l.status = ? ";
            list.add(status);
        }
        if (userId != 0 ) {
            sql += " and u.id = ? ";
            list.add(userId);
        }
        if (createTime != null && createTime != "") {
            sql += " and l.create_time >= ? ";
            list.add(createTime);
        }

        if (backTime != null && backTime != "") {
            sql += " and l.back_time <= ? ";
            list.add(backTime);
        }

        Connection conn = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(sql);

            for(int i = 1;i<=list.size();i++){
                preparedStatement.setObject(i,list.get(i-1));
            }
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                count = resultSet.getInt("count");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (conn != null){

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
    public List<LogResult> queryByIdAndNameForPageItems(String bookName, String userName, Integer status, String createTime, String backTime, Integer userId, int begin, int pageSize) {
        String sql = " SELECT u.id userId,u.name userName,l.create_time createTime,l.back_time backTime," +
                "l.book_id bookId,b.name bookName,l.status status,l.id logId FROM t_log l \n" +
                "LEFT JOIN t_user u \n" +
                "on u.id = l.user_id\n" +
                "LEFT JOIN t_books b\n" +
                "on b.id = l.book_id where 1 = 1 ";

        List<Object> list = new ArrayList<>();
        if (bookName != null && bookName != "") {
            sql += " and b.name like ? ";
            bookName = "%" + bookName +"%";
            list.add(bookName);
        }

        if (userName != null && userName != "") {
            sql += " and u.name like ? ";
            userName = "%" + userName +"%";
            list.add(userName);
        }
        if (status == 0 || status == 1) {
            sql += " and l.status = ? ";
            list.add(status);
        }

        if (userId != 0 ) {
            sql += " and u.id = ? ";
            list.add(userId);
        }
        if (createTime != null && createTime != "") {
            sql += " and l.create_time >= ? ";
            list.add(createTime);
        }

        if (backTime != null && backTime != "") {
            sql += " and l.create_time <= ? ";
            list.add(backTime);
        }

        list.add(begin);
        list.add(pageSize);
        sql += "limit ?,?";

        Connection conn = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = null;
        List<LogResult> logResults = new ArrayList<>();
        try {
            preparedStatement = conn.prepareStatement(sql);

            for(int i = 1;i<=list.size();i++){
                preparedStatement.setObject(i,list.get(i-1));
            }
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                // userId,u.name userName,l.create_time createTime,l.back_time backTime," +
                //                "l.book_id bookId,b.name bookName,l.status status
               LogResult logResult = new LogResult();
                logResult.setUserId(resultSet.getInt("userId"));
                logResult.setBookId(resultSet.getInt("bookId"));
                logResult.setStatus(resultSet.getInt("status"));
                logResult.setLogId(resultSet.getInt("logId"));
                logResult.setCreateTime(resultSet.getTimestamp("createTime"));
                logResult.setBackTime(resultSet.getTimestamp("backTime"));
                logResult.setUserName(resultSet.getString("userName"));
                logResult.setBookName(resultSet.getString("bookName"));

                logResults.add(logResult);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (conn != null){

                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        return logResults;
    }

    @Override
    public int saveLog(Integer bookId, Integer userId, Integer status, Date date) {
        String sql = "insert into t_log(user_id,book_id,create_time,status) values(?,?,?,?)";
        return update(sql,userId,bookId,date,status);
    }

    @Override
    public int update(Integer logId, Integer status, Date date) {
        String sql = "update t_log set status = ? ,back_time = ? where id = ?";
        return  update(sql,status,date,logId);
    }

    @Override
    public List<LogCountByTime> getLogCountByTime() {
        String sql = "select tc.date date,tc.createCount,tb.borrowCount \n" +
                "\tfrom(select t_time.date,ifnull(count(date(tlog1.create_time)),0) createCount\n" +
                "\t\t\tfrom (select date(create_time) date \n" +
                "\t\t\t\t\t\tfrom t_log \n" +
                "\t\t\t\t\t\tgroup by date(create_time) \n" +
                "\t\t\t\t\t\tunion\n" +
                "\t\t\t\t\t\tselect date(back_time) date\n" +
                "\t\t\t\t\t\tfrom t_log \n" +
                "\t\t\t\t\t\twhere date(back_time) is not null \n" +
                "\t\t\t\t\t\tgroup by date(back_time)  \n" +
                "\t\t\t\t\t\torder by date) t_time\n" +
                "\t\t\tleft join t_log tlog1\n" +
                "\t\t\ton t_time.date = date(tlog1.create_time)\n" +
                "\t\t\tgroup by t_time.date\n" +
                "\t\t\tORDER BY t_time.date) tc\n" +
                "\tleft join\n" +
                "\t(select t_time.date,ifnull(count(date(tlog1.back_time)),0) borrowCount\n" +
                "\t\t\tfrom (select date(create_time) date \n" +
                "\t\t\t\t\t\tfrom t_log \n" +
                "\t\t\t\t\t\tgroup by date(create_time) \n" +
                "\t\t\t\t\t\tunion\n" +
                "\t\t\t\t\t\tselect date(back_time) date\n" +
                "\t\t\t\t\t\tfrom t_log \n" +
                "\t\t\t\t\t\twhere date(back_time) is not null \n" +
                "\t\t\t\t\t\tgroup by date(back_time)  \n" +
                "\t\t\t\t\t\torder by date) t_time\n" +
                "\t\t\tleft join t_log tlog1\n" +
                "\t\t\ton t_time.date = date(tlog1.back_time)\n" +
                "\t\t\tgroup by t_time.date\n" +
                "\t\t\tORDER BY t_time.date) tb\n" +
                "\ton tc.date = tb.date";
        return queryForList(LogCountByTime.class, sql);
    }

}
