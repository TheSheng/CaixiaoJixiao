package com.bishe.java.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @since 2020-01-13
 */
@TableName("bishe_caixiao")
public class CaiXiao implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "staffId")
    private Integer staffId;
    private String time;
    private String text;
    private String hascheck;

    public String getHascheck() {
        return hascheck;
    }

    public void setHascheck(String hascheck) {
        this.hascheck = hascheck;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CaiXiao(Integer id, Integer staffId, String time, String text,String hascheck) {
        this.id = id;
        this.staffId = staffId;
        this.time = time;
        this.text = text;
        this.hascheck=hascheck;
    }

    public CaiXiao() {

    }
}
