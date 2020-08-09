package com.company.dao.impl;

import com.company.bean.Books;
import com.company.dao.BooksDao;
import com.company.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BooksDaoImpl extends BaseDao implements BooksDao {

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_books";

        long num = (long) queryForSingleValue(sql);
        return Integer.parseInt(String.valueOf(num));
    }

    @Override
    public List<Books> queryForPageItems(int begin, int pageSize) {
        String sql = "select b.id,b.name,b.price,b.num,b.publishing,b.sort,t.name typeName,b.img\n" +
                "from t_books b\n" +
                "left join t_type t\n" +
                "on b.type = t.type\n" +
                "limit ?,?";
        List<Books> books = queryForList(Books.class, sql, begin, pageSize);
        return books;
    }

    @Override
    public Books queryBookById(int id) {
        String sql = "select * from t_books where id =?";
        return queryForOne(Books.class, sql, id);
    }

    @Override
    public int deleteById(int id) {
        String sql = "delete from t_books where id = ?";
        return update(sql, id);
    }





    @Override
    public Integer queryByIdOrNameOrTypeForPageTotalCount(String name, Integer type, String publishing, String author) {
        String sql = "select count(*) count from t_books b \n" +
                "        left join t_type t \n" +
                "        on b.type = t.type \n" +
                "        where 1 = 1 ";

        List<Object> list = new ArrayList<>();
        if (name != null && name != "") {
            sql += " and b.name like ? ";
            name = "%" + name +"%";
            list.add(name);
        }
        if (publishing != null && publishing != "") {
            sql += " and b.publishing like ? ";
            publishing = "%" + publishing +"%";
            list.add(publishing);
        }
        if (type != 0) {
            sql += " and b.type = ? ";
            list.add(type.toString());
        }
        if (author != null && author != "") {
            sql += " and b.author like ? ";
            author = "%" + author +"%";
            list.add(author);
        }

        Connection conn = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = null;

        int count = -1;
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
    public List<Books> queryByIdOrNameOrTypeForPageItems(String name, Integer type, String publishing, String author, int begin, int pageSize) {
        String sql = "select b.id,b.name,b.price,b.author,b.num,b.publishing,b.sort,t.name type,b.img\n" +
                "        from t_books b\n" +
                "        left join t_type t\n" +
                "        on b.type = t.type\n" +
                "        where 1 = 1 ";

        List<Object> list = new ArrayList<>();
        if (name != null && name != "") {
            sql += " and b.name like ? ";
            name = "%" + name +"%";
            list.add(name);
        }
        if (publishing != null && publishing != "") {
            sql += " and b.publishing like ? ";
            publishing = "%" + publishing +"%";
            list.add(publishing);
        }
        if (type != 0) {
            sql += " and b.type = ? ";
            list.add(type.toString());
        }
        if (author != null && author != "") {
            sql += " and b.author like ? ";
            author = "%" + author +"%";
            list.add(author);
        }
        list.add(begin);
        list.add(pageSize);
        sql += "limit ?,?";

        Connection conn = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = null;
        List<Books> booksList = new ArrayList<>();
        try {
            preparedStatement = conn.prepareStatement(sql);

            for(int i = 1;i<=list.size();i++){
                preparedStatement.setObject(i,list.get(i-1));
            }
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                // b.id,b.name,b.price,b.num,b.publishing,b.sort,t.name type,b.img
                Books books = new Books();
                books.setId(resultSet.getInt("id"));
                books.setNum(resultSet.getInt("num"));
                books.setName(resultSet.getString("name"));
                books.setPublishing(resultSet.getString("publishing"));
                books.setAuthor(resultSet.getString("author"));
                books.setSort(resultSet.getString("sort"));
                books.setTypeName(resultSet.getString("type"));
                books.setImg(resultSet.getString("img"));
                books.setPrice(resultSet.getFloat("price"));

                booksList.add(books);
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

        return booksList;
    }

    @Override
    public int saveBook(String name, float price, String publishing, String sort, Integer type, String img, String author, int num) {
        String sql = "insert into t_books(name,author,price,num,publishing,sort,type,img) values(?,?,?,?,?,?,?,?)";

        return update(sql,name,author,price,num,publishing,sort,type,img);
    }

    @Override
    public int updateById(int id, String name, float price, int bookNum, String publishing, String sort, Integer type, String img, String author) {
        String sql = "update t_books set name = ?,author = ?,price = ?,publishing = ?,sort = ?,type = ?,num = ?,img = ? where id = ?";
        return update(sql, name, author, price, publishing, sort, type,bookNum,img, id);
    }

    @Override
    public int deleteByIdBeach(String[] split) {
        int[] a = new int[split.length];
        if (split.length == 1){
            String sql = "delete from t_books where id = ?";
            return update(sql, Integer.parseInt(split[0]));
        }else {
            String sql = "delete from t_books where id in ( ";
            for (int i = 0; i < split.length; i++) {
                sql += "? ,";
                a[i] = Integer.parseInt(split[i]);
            }
            sql = sql.substring(0,sql.length() - 1);
            sql += ")";

            Connection conn = JdbcUtils.getConnection();
            PreparedStatement preparedStatement = null;

            try {
                preparedStatement = conn.prepareStatement(sql);

                for(int i = 1;i<=a.length;i++){
                    preparedStatement.setObject(i,a[i-1]);
                }
                boolean execute = preparedStatement.execute();
                if (execute){
                    return 0;
                }else {
                    return 1;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return 0;
            }finally {
                if (conn != null){

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
    public int updateBookImg(String bookId, String fileName) {
        String sql = "update t_books set img = ? where id = ?";
        return update(sql, fileName, bookId);
    }
}
