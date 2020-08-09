package com.company.bean;

import java.util.Date;

public class LogCountByTime {
    private Date date;
    private int createCount;
    private int borrowCount;

    public LogCountByTime(){
    }

    public LogCountByTime(Date date, int createCount, int borrowCount) {
        this.date = date;
        this.createCount = createCount;
        this.borrowCount = borrowCount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCreateCount() {
        return createCount;
    }

    public void setCreateCount(int createCount) {
        this.createCount = createCount;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(int borrowCount) {
        this.borrowCount = borrowCount;
    }

    @Override
    public String toString() {
        return "LogCountByTime{" +
                "date=" + date +
                ", createCount=" + createCount +
                ", borrowCount=" + borrowCount +
                '}';
    }
}
