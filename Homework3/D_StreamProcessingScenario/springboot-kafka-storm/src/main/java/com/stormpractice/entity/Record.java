package com.stormpractice.entity;

import javax.persistence.*;

/**
 * @author YZH
 * @date 2018/11/23 11:04
 */
@Entity
@Table(name = "Record")
public class Record {
    private String orderid;
    private String username;
    private String bookname;
    private int num;

    @Id
    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "bookname")
    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    @Basic
    @Column(name = "num")
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString(){
        return orderid + ";" + username + ";" + bookname + ";" + num;
    }
}
