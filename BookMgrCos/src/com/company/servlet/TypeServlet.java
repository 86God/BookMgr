package com.company.servlet;

import com.company.bean.Type;
import com.company.service.TypeService;
import com.company.service.impl.TypeServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/typeServlet")
public class TypeServlet extends BaseServlet {

    TypeService typeService = new TypeServiceImpl();

    protected void getType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String,Object> map = new HashMap<>();
        try {
            List<Type> typeList = typeService.query();
            map.put("type",typeList);
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
}
