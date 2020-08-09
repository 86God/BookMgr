package com.company.test;

import com.company.dao.impl.BooksDaoImpl;
import org.junit.Test;

public class BookDaoTest {

    @Test
    public void test(){
        BooksDaoImpl booksDao = new BooksDaoImpl();
        int i = booksDao.deleteById(15);
        System.out.println(i);
    }
}
