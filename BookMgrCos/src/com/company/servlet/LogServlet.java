package com.company.servlet;

import com.company.bean.*;
import com.company.dao.BooksDao;
import com.company.dao.UserDao;
import com.company.dao.impl.BooksDaoImpl;
import com.company.dao.impl.UserDaoImpl;
import com.company.service.BooksService;
import com.company.service.LogService;
import com.company.service.UserService;
import com.company.service.impl.BooksServiceImpl;
import com.company.service.impl.LogServiceImpl;
import com.company.service.impl.UserServiceImpl;
import com.company.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/logServlet")
public class LogServlet extends BaseServlet{


    LogService logService = new LogServiceImpl();
    BooksService booksService = new BooksServiceImpl();
    UserService userService = new UserServiceImpl();

    protected void getPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String,Object> map = new HashMap<>();

        try {
            int pageNo = WebUtils.parseInt(req.getParameter("page"), 1);
            int pageSize = WebUtils.parseInt(req.getParameter("limit"), 10);

            String bookName = req.getParameter("bookName");
            String userName = req.getParameter("userName");
            Integer status = WebUtils.parseInt(req.getParameter("bookType"), -1);
            Integer userId = WebUtils.parseInt(req.getParameter("userId"), 0);
            String createTime = req.getParameter("startTime");
            String backTime = req.getParameter("endTime");
            Page<LogResult> page = logService.queryByIdAndName(bookName,userName,status,createTime,backTime,userId,pageNo,pageSize);

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

    protected void saveLog(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String,Object> map = new HashMap<>();

        try {

            Integer bookId = WebUtils.parseInt(req.getParameter("bookId"), -1);
            Books books = booksService.queryById(bookId);
            Integer userId = WebUtils.parseInt(req.getParameter("userId"), -1);
            User user = userService.queryById(userId);

            if (books == null || user == null){
                map.put("code",-10);
                return;
            }
            Integer status = WebUtils.parseInt(req.getParameter("status"), 0);

            int i = logService.save(bookId,userId,status);
            map.put("code",1);
            if (i == 1){
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

    protected void updateLog(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String,Object> map = new HashMap<>();

        try {

            Integer logId = WebUtils.parseInt(req.getParameter("logId"), -1);

            Integer status = WebUtils.parseInt(req.getParameter("status"), 1);


            int i = logService.update(logId,status);
            map.put("code",1);
            if (i == 1){
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


    protected void getLogCountByTime(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String,Object> map = new HashMap<>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd",
                new Locale("zh", "CN"));
        try {
            List<LogCountByTime> logCountByTimeList = logService.getLogCountByTime();
            List<String> dateList = new ArrayList<>();
            List<Integer> createCountList = new ArrayList<>();
            List<Integer> borrowCountList = new ArrayList<>();
            for(int i = 0;i<logCountByTimeList.size();i++){
                LogCountByTime logCountByTime = logCountByTimeList.get(i);
                String data = format.format(logCountByTime.getDate());
                dateList.add(data);
                createCountList.add(logCountByTime.getCreateCount());
                borrowCountList.add(logCountByTime.getBorrowCount());
            }
            map.put("code",0);
            map.put("dateList",dateList);
            map.put("createCountList",createCountList);
            map.put("borrowCountList",borrowCountList);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",1);
        } finally {
            Gson gson = new Gson();
            String json = gson.toJson(map);
            resp.getWriter().write(json);
        }
    }
}
