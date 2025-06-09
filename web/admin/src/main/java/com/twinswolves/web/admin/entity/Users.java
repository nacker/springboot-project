package com.twinswolves.web.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表，存储用户基本信息
 * </p>
 *
 * @author nacker
 * @since 2025-06-09
 */
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID，自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名，允许为空
     */
    private String username;

    /**
     * 电子邮箱，允许为空
     */
    private String email;

    /**
     * 加密后的密码，允许为空
     */
    private String hashedPassword;

    /**
     * 手机号码，允许为空
     */
    private String phone;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Users{" +
        ", id = " + id +
        ", username = " + username +
        ", email = " + email +
        ", hashedPassword = " + hashedPassword +
        ", phone = " + phone +
        ", createTime = " + createTime +
        ", updateTime = " + updateTime +
        "}";
    }
}
