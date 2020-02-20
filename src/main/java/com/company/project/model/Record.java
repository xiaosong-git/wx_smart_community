package com.company.project.model;

import javax.persistence.*;

@Table(name = "h_record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 通行时间
     */
    @Column(name = "pass_time")
    private String passTime;

    /**
     * 是否通行 0 是 1 否
     */
    @Column(name = "is_pass")
    private String isPass;
    @Column(name = "area_id")
    private String areaId;

    /**
     * 更新时间
     */
    @Column(name = "update_time")

    private String updateTime;

    /**
     * 生成时间
     */
    @Column(name = "create_time")
    private String createTime;

    private String type;
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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取通行时间
     *
     * @return pass_time - 通行时间
     */
    public String getPassTime() {
        return passTime;
    }

    /**
     * 设置通行时间
     *
     * @param passTime 通行时间
     */
    public void setPassTime(String passTime) {
        this.passTime = passTime;
    }

    /**
     * 获取是否通行 0 是 1 否
     *
     * @return is_pass - 是否通行 0 是 1 否
     */
    public String getIsPass() {
        return isPass;
    }

    /**
     * 设置是否通行 0 是 1 否
     *
     * @param isPass 是否通行 0 是 1 否
     */
    public void setIsPass(String isPass) {
        this.isPass = isPass;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAreaId() {
        return areaId;
    }
    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
}