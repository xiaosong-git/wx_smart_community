package com.company.project.model;

import javax.persistence.*;

@Table(name = "h_staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户的user_id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * delete-- 删除
     */
    private String status;

    /**
     * 小区id
     */
    @Column(name = "area_id")
    private String areaId;

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
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户的user_id
     *
     * @return user_id - 用户的user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户的user_id
     *
     * @param userId 用户的user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取delete-- 删除
     *
     * @return status - delete-- 删除
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置delete-- 删除
     *
     * @param status delete-- 删除
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取小区id
     *
     * @return areaId - 小区id
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * 设置小区id
     *
     * @param areaId 小区id
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId;
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