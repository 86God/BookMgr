package com.company.servlet;

import com.company.bean.Books;
import com.company.bean.Page;
import com.company.service.BooksService;
import com.company.service.impl.BooksServiceImpl;
import com.company.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/bookServlet")
public class BookServlet extends BaseServlet {


    BooksService booksService = new BooksServiceImpl();


    protected void searchType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> map = new HashMap<>();

        try {
            int pageNo = WebUtils.parseInt(req.getParameter("page"), 1);
            int pageSize = WebUtils.parseInt(req.getParameter("limit"), 10);

            String name = req.getParameter("bookName");
            String publishing = req.getParameter("publishing");
            Integer type = WebUtils.parseInt(req.getParameter("bookType"), 0);
            String author = req.getParameter("author");
            Page<Books> page = booksService.queryByIdOrNameOrType(name, publishing, author, type, pageNo, pageSize);

            map.put("data", page.getItems());
            map.put("count", page.getPageTotalCount());
            map.put("code", 0);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1);
        } finally {

            Gson gson = new Gson();
            String json = gson.toJson(map);
            resp.getWriter().write(json);
        }

    }



    protected void bookPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,Object> map = new HashMap<>();

        try {
            int pageNo = WebUtils.parseInt(req.getParameter("page"), 1);
            int pageSize = WebUtils.parseInt(req.getParameter("limit"), 10);

            Page<Books> page = booksService.page(pageNo, pageSize);


            map.put("data",page.getItems());
            map.put("count",page.getPageTotalCount());
            map.put("code",0);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",1);
        } finally {

            Gson gson = new Gson();
            String json = gson.toJson(map);
            resp.getWriter().write(json);
        }

    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,Object> map = new HashMap<>();

        try {
            int id = WebUtils.parseInt(req.getParameter("bookId"), -1);

            Books books = booksService.queryById(id);
            map.put("code",1);
            if (books != null){
                map.put("book",books);
                map.put("code",0);
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",2);
        } finally {

            Gson gson = new Gson();
            String json = gson.toJson(map);
            resp.getWriter().write(json);
        }

    }

    protected void updateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,Object> map = new HashMap<>();

        try {
            int id = WebUtils.parseInt(req.getParameter("bookId"), 1);
            int bookNum = WebUtils.parseInt(req.getParameter("bookNum"), 1);
            String name = req.getParameter("bookName");
            float price = Float.parseFloat(req.getParameter("price"));
            String publishing = req.getParameter("publishing");
            String sort = req.getParameter("sort");
            Integer type = WebUtils.parseInt(req.getParameter("bookType"), 1);
            String img = req.getParameter("bookImgSrc");
            String author = req.getParameter("author");

            int i = booksService.updateById(id,name,price,bookNum,publishing,sort,type,img,author);
            Books books = booksService.queryById(id);
            if (i == 1) {
                map.put("book",books);
                map.put("code",0);
            }else {
                map.put("code",1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",2);
        } finally {

            Gson gson = new Gson();
            String json = gson.toJson(map);
            resp.getWriter().write(json);
        }

    }

    protected void saveBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,Object> map = new HashMap<>();

        try {
            int num = WebUtils.parseInt(req.getParameter("num"), 1);
            String name = req.getParameter("bookName");
            float price = Float.parseFloat(req.getParameter("price"));
            String publishing = req.getParameter("publishing");
            String sort = req.getParameter("sort");
            Integer type = WebUtils.parseInt(req.getParameter("bookType"), 1);
            String img = req.getParameter("bookImgSrc");
            String author = req.getParameter("author");



            int i = booksService.saveBook(name,price,publishing,sort,type,img,author,num);
            map.put("code",1);
            if (i == 1) {
                map.put("code",0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",2);
        } finally {

            Gson gson = new Gson();
            String json = gson.toJson(map);
            resp.getWriter().write(json);
        }

    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,Object> map = new HashMap<>();

        try {
            int id = WebUtils.parseInt(req.getParameter("id"),-1);

            int i = booksService.deleteById(id);
            if (i == 1){
                map.put("code",0);
            }else {
                map.put("code",1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",2);
        } finally {

            Gson gson = new Gson();
            String json = gson.toJson(map);
            resp.getWriter().write(json);
        }

    }

    protected void deleteBatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,Object> map = new HashMap<>();

        try {
            String idList = req.getParameter("idList");

            String[] split = idList.split(",");
            int x = 0;
            for (int i = 0; i < split.length; i++) {
               x = booksService.deleteByIdBatch(split);
            }
            if (x == 1){

                map.put("code",0);
            }else {
                map.put("code",1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",2);
        } finally {

            Gson gson = new Gson();
            String json = gson.toJson(map);
            resp.getWriter().write(json);
        }

    }

}
