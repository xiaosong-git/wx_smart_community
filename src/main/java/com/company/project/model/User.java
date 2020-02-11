package com.company.project.model;

import javax.persistence.*;

@Table(name = "h_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 身份证
     */
    @Column(name = "id_no")
    private String idNo;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 是否实人
     */
    @Column(name = "is_auth")
    private String isAuth;

    /**
     * 认证日期
     */
    @Column(name = "auth_date")
    private String authDate;

    /**
     * 微信号
     */
    @Column(name = "wx_open_id")
    private String wxOpenId;

    /**
     * 是否管理员
     */
    @Column(name = "is_manager")
    private String isManager;

    /**
     * 生成时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private String updateTime;

    private String ext1;

    private String ext2;

    private String ext3;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return name - 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名
     *
     * @param name 用户名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取身份证
     *
     * @return id_no - 身份证
     */
    public String getIdNo() {
        return idNo;
    }

    /**
     * 设置身份证
     *
     * @param idNo 身份证
     */
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    /**
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取是否实人
     *
     * @return is_auth - 是否实人
     */
    public String getIsAuth() {
        return isAuth;
    }

    /**
     * 设置是否实人
     *
     * @param isAuth 是否实人
     */
    public void setIsAuth(String isAuth) {
        this.isAuth = isAuth;
    }

    /**
     * 获取认证日期
     *
     * @return auth_date - 认证日期
     */
    public String getAuthDate() {
        return authDate;
    }

    /**
     * 设置认证日期
     *
     * @param authDate 认证日期
     */
    public void setAuthDate(String authDate) {
        this.authDate = authDate;
    }

    /**
     * 获取微信号
     *
     * @return wx_open_id - 微信号
     */
    public String getWxOpenId() {
        return wxOpenId;
    }

    /**
     * 设置微信号
     *
     * @param wxOpenId 微信号
     */
    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    /**
     * 获取是否管理员
     *
     * @return is_manager - 是否管理员
     */
    public String getIsManager() {
        return isManager;
    }

    /**
     * 设置是否管理员
     *
     * @param isManager 是否管理员
     */
    public void setIsManager(String isManager) {
        this.isManager = isManager;
    }

    /**
     * 获取生成时间
     *
     * @return create_time - 生成时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置生成时间
     *
     * @param createTime 生成时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return ext1
     */
    public String getExt1() {
        return ext1;
    }

    /**
     * @param ext1
     */
    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    /**
     * @return ext2
     */
    public String getExt2() {
        return ext2;
    }

    /**
     * @param ext2
     */
    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    /**
     * @return ext3
     */
    public String getExt3() {
        return ext3;
    }

    /**
     * @param ext3
     */
    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }
}