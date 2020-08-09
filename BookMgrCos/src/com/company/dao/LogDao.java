package com.company.dao;

import com.company.bean.LogCountByTime;
import com.company.bean.LogResult;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface LogDao {
    Integer queryByIdAndNameForPageTotalCount(String bookName, String userName, Integer status, String createTime, String backTime, Integer userId);

    List<LogResult> queryByIdAndNameForPageItems(String bookName, String userName, Integer status, String createTime, String backTime, Integer userId, int begin, int pageSize);

    int saveLog(Integer bookId, Integer userId, Integer status, Date date);

    int update(Integer logId, Integer status, Date date);

    List<LogCountByTime> getLogCountByTime();
}
