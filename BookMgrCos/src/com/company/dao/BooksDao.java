package com.company.dao;

import com.company.bean.Books;

import java.util.List;

public interface BooksDao {

    Integer queryForPageTotalCount();

    List<Books> queryForPageItems(int begin, int end);

    Books queryBookById(int id);

    int deleteById(int id);

    Integer queryByIdOrNameOrTypeForPageTotalCount(String name, Integer type, String publishing, String author);

    List<Books> queryByIdOrNameOrTypeForPageItems(String name, Integer type, String publishing, String author, int begin, int pageSize);

    int saveBook(String name, float price, String publishing, String sort, Integer type, String img, String author, int num);

    int updateById(int id, String name, float price, int bookNum, String publishing, String sort, Integer type, String img, String author);

    int deleteByIdBeach(String[] split);

    int updateBookImg(String bookId, String fileName);
}
