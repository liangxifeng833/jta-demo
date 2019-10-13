package com.mybatis.jta.demo.entity.yun;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

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
@Setter
@Getter
@ToString
public class AppTag {

    private static final long serialVersionUID = 1L;

    private Integer tagId;
    /**
     * 名字
     */
    private String tagName;

    private Integer tagType;

}