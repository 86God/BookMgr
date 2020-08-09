package com.company.servlet;

import com.company.bean.Admin;
import com.company.service.AdminService;
import com.company.service.impl.AdminServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/adminServlet")
public class AdminServlet extends BaseServlet {

    AdminService adminService = new AdminServiceImpl();

    protected void test(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        System.out.println(session.getId());
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> map = new HashMap<>();
        try {
            String name = req.getParameter("userName");
            String password = req.getParameter("password");

            Admin admin = adminService.login(name, password);

            HttpSession session = req.getSession();
            session.setAttribute("admin", admin);

            System.out.println(session.getId());

            map.put("code", 0);
            if (admin == null) {
                map.put("code", 1);
            }
            map.put("admin", admin);
            map.put("sessionId", session.getId());

        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 2);

        } finally {
            Gson gson = new Gson();
            String json = gson.toJson(map);
            resp.getWriter().write(json);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            HttpSession session = req.getSession();
            session.invalidate();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
