package com.company.service.impl;

import com.company.bean.Books;
import com.company.bean.Page;
import com.company.dao.BooksDao;
import com.company.dao.impl.BooksDaoImpl;
import com.company.service.BooksService;

import java.util.List;

public class BooksServiceImpl implements BooksService {

    BooksDao booksDao = new BooksDaoImpl();
    @Override
    public Page<Books> page(int pageNo, int pageSize) {
        Page<Books> page = new Page<>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = booksDao.queryForPageTotalCount();
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
        int begin = (page.getPageNo() - 1) * pageSize;



        // 求当前页数据
        List<Books> items = booksDao.queryForPageItems(begin,pageSize);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public Books queryById(int id) {
        return booksDao.queryBookById(id);
    }

    @Override
    public int deleteById(int id) {
        return booksDao.deleteById(id);
    }



    @Override
    public Page<Books> queryByIdOrNameOrType(String name,String publishing, String author, Integer type, int pageNo, int pageSize) {
        Page<Books> page = new Page<>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = booksDao.queryByIdOrNameOrTypeForPageTotalCount(name,type,publishing,author);
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
        List<Books> items = booksDao.queryByIdOrNameOrTypeForPageItems(name,type,publishing,author,begin,pageSize);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public int saveBook(String name, float price, String publishing, String sort, Integer type, String img, String author, int num) {
        return booksDao.saveBook(name,price,publishing,sort,type,img,author,num);
    }

    @Override
    public int updateById(int id, String name, float price, int bookNum, String publishing, String sort, Integer type, String img, String author) {
        return booksDao.updateById(id,name,price,bookNum,publishing,sort,type,img,author);
    }

    @Override
    public int deleteByIdBatch(String[] split) {
        return booksDao.deleteByIdBeach(split);
    }

    @Override
    public int updateBookImg(String bookId, String fileName) {
        return booksDao.updateBookImg(bookId,fileName);
    }
}
