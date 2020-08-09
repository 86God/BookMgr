package com.company.service;

import com.company.bean.Books;
import com.company.bean.Page;

public interface BooksService {

    Page<Books> queryByIdOrNameOrType(String name, String publishing, String author, Integer type, int pageNo, int pageSize);


    Page<Books> page(int pageNo, int pageSize);

    Books queryById(int id);

    int deleteById(int id);



    int saveBook(String name, float price, String publishing, String sort, Integer type, String img, String author, int num);

    int updateById(int id, String name, float price, int bookNum, String publishing, String sort, Integer type, String img, String author);

    int deleteByIdBatch(String[] split);

    int updateBookImg(String updatePara, String fileName);
}
