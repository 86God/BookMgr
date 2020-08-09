package com.company.service.impl;


import com.company.bean.LogCountByTime;
import com.company.bean.LogResult;
import com.company.bean.Page;
import com.company.dao.LogDao;
import com.company.dao.impl.LogDaoImpl;
import com.company.service.LogService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class LogServiceImpl implements LogService {

    LogDao logDao = new LogDaoImpl();

    @Override
    public Page<LogResult> queryByIdAndName(String bookName, String userName, Integer status, String createTime, String backTime, Integer userId, int pageNo, int pageSize) {
        Page<LogResult> page = new Page<>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = logDao.queryByIdAndNameForPageTotalCount(bookName,userName,status,createTime,backTime,userId);
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
        List<LogResult> items = logDao.queryByIdAndNameForPageItems(bookName,userName,status,createTime,backTime,userId,begin,pageSize);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public int save(Integer bookId, Integer userId, Integer status) {
        Date date = new Date();
        return logDao.saveLog(bookId, userId, status, date);
    }

    @Override
    public int update(Integer logId, Integer status) {
        return logDao.update(logId,status,new Date());
    }

    @Override
    public List<LogCountByTime> getLogCountByTime() {
        return logDao.getLogCountByTime();
    }
}
