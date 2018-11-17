package com.fang.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/5/7 11:44
 * @Modified by:
 */
@Entity
public class Visitor {
    @GeneratedValue
    @Id
    private Integer id;
    private String ip;
    private Integer times;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }
}
