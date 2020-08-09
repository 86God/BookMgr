package com.company.servlet;

import com.company.bean.Page;
import com.company.bean.User;
import com.company.service.UserService;

import com.company.service.impl.UserServiceImpl;
import com.company.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/userServlet")
public class UserServlet extends BaseServlet {

    UserService userService = new UserServiceImpl();
    protected void saveUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> map = new HashMap<>();

        try {
            String name = req.getParameter("userName");
            String phone = req.getParameter("phone");
            int sex = WebUtils.parseInt(req.getParameter("userSex"),1);
            int age = WebUtils.parseInt(req.getParameter("userAge"),1);
            String userSex = "";

            if (sex == 1) {
                userSex = "男";
            }
            if (sex == 2) {
                userSex = "女";
            }
            int i = userService.saveUser(name, phone,age,userSex);
            map.put("code", 1);
            if (i == 1) {
                map.put("code", 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 2);
        } finally {

            Gson gson = new Gson();
            String json = gson.toJson(map);
            resp.getWriter().write(json);
        }
    }

    protected void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> map = new HashMap<>();

        try {
            String name = req.getParameter("userName");
            String phone = req.getParameter("phone");
            int sex = WebUtils.parseInt(req.getParameter("userSex"),1);
            int id = WebUtils.parseInt(req.getParameter("userId"),0);
            int age = WebUtils.parseInt(req.getParameter("userAge"),1);
            String userSex = "";

            if (sex == 1) {
                userSex = "男";
            }
            if (sex == 2) {
                userSex = "女";
            }

            int i = userService.updateUser(name, phone,age,userSex,id);
            map.put("code", 1);
            if (i == 1) {
                map.put("code", 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 2);
        } finally {

            Gson gson = new Gson();
            String json = gson.toJson(map);
            resp.getWriter().write(json);
        }
    }
    protected void userPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Object> map = new HashMap<>();

        try {
            int pageNo = WebUtils.parseInt(req.getParameter("page"), 1);
            int pageSize = WebUtils.parseInt(req.getParameter("limit"), 10);

            String name = req.getParameter("userName");
            String phone = req.getParameter("phone");
            int sex = WebUtils.parseInt(req.getParameter("userSex"),0);
            String userSex = "";

            if (sex == 1) {
                userSex = "男";
            }
            if (sex == 2) {
                userSex = "女";
            }
            int age = WebUtils.parseInt(req.getParameter("userAge"), 0);
            Page<User> page = userService.queryByIdAndNameOAndSex(name, phone, userSex, age, pageNo, pageSize);

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

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Object> map = new HashMap<>();

        try {
            int id = WebUtils.parseInt(req.getParameter("userId"), 0);
            int i = userService.deleteById(id);
            System.out.println("i = " + i);
            if (i == 1) {
                map.put("code", 0);
            }else{
                map.put("code", 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 2);
        } finally {

            Gson gson = new Gson();
            String json = gson.toJson(map);
            resp.getWriter().write(json);
        }

    }

    protected void getUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Object> map = new HashMap<>();

        try {
            int id = WebUtils.parseInt(req.getParameter("userId"), 0);
            User user = userService.queryById(id);
            map.put("code", 1);
            if (user != null) {
                map.put("code", 0);
                map.put("user",user);
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 2);
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
                x = userService.deleteByIdBatch(split);
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
