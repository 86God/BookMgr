package com.company.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class LogResult {

    private Integer userId;
    private Integer logId;
    private Integer bookId;
    private String userName;
    private String bookName;

    private Timestamp createTime;
    private Timestamp backTime;

    private Timestamp sumTime;

    public Timestamp getSumTime() {
        return sumTime;
    }

    public void setSumTime(Timestamp sumTime) {
        this.sumTime = sumTime;
    }

    private Integer status;

    public LogResult() {
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getBackTime() {
        return backTime;
    }

    public void setBackTime(Timestamp backTime) {
        this.backTime = backTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LogResult{" +
                "userId=" + userId +
                ", logId=" + logId +
                ", bookId=" + bookId +
                ", userName='" + userName + '\'' +
                ", bookName='" + bookName + '\'' +
                ", createTime=" + createTime +
                ", backTime=" + backTime +
                ", sumTime=" + sumTime +
                ", status=" + status +
                '}';
    }
}
