package com.company.service;

import com.company.bean.LogCountByTime;
import com.company.bean.LogResult;
import com.company.bean.Page;

import java.util.List;
import java.util.Map;


public interface LogService {
    Page<LogResult> queryByIdAndName(String bookName, String userName, Integer status, String createTime, String backTime, Integer userId, int pageNo, int pageSize);

    int save(Integer bookId, Integer userId, Integer status);

    int update(Integer logId, Integer status);

    List<LogCountByTime> getLogCountByTime();
}
