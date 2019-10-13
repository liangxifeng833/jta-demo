package com.mybatis.jta.demo.entity.car;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 消息包编号表
 * </p>
 *
 * @author liangxifeng
 */
public class MessagePackageNo {

    private static final long serialVersionUID = 1L;

    /**
     * 消息包编号
     */
    private Integer no;
    /**
     * 创建时间
     */
    private Timestamp createTime;


    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
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
        sb.append("\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"no\":")
                .append(no);
        sb.append('}');
        return sb.toString();
    }
}
