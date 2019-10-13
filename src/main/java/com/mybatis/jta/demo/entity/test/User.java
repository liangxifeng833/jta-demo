package com.mybatis.jta.demo.entity.test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author lcj
 * @since 2017-09-27
 */
public class User{

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 名字
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;

    private Timestamp createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCreateTime() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(createTime == null) return null;
        return sdf.format(createTime);
    }

    public void setCreateTime(String createTime) {
        if(createTime == null)
        {
            java.util.Date now = new java.util.Date();
            Timestamp timestamp = new Timestamp(now.getTime());
            this.createTime = timestamp;
        }else
        {
            this.createTime = Timestamp.valueOf(createTime);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"age\":")
                .append(age);
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"id\":")
                .append(id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append('}');
        return sb.toString();
    }
}