package com.company.project.model;

import javax.persistence.*;

@Table(name = "h_access_record")
public class AccessRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 进出类型(in/out)
     */
    @Column(name = "turn_over")
    private String turnOver;

    /**
     * 进出日期
     */
    @Column(name = "pass_date")
    private String passDate;

    /**
     * 进出时间
     */
    @Column(name = "pass_time")
    private String passTime;

    /**
     * 进出人员类型
     */
    @Column(name = "personType")
    private String persontype;

    /**
     * 用户进出小区的id
     */
    @Column(name = "area_id")
    private Long areaId;

    /**
     * 工作人员ID
     */
    @Column(name = "manager_id")
    private String managerId;

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
     * 获取进出类型(in/out)
     *
     * @return turn_over - 进出类型(in/out)
     */
    public String getTurnOver() {
        return turnOver;
    }

    /**
     * 设置进出类型(in/out)
     *
     * @param turnOver 进出类型(in/out)
     */
    public void setTurnOver(String turnOver) {
        this.turnOver = turnOver;
    }

    /**
     * 获取进出日期
     *
     * @return pass_date - 进出日期
     */
    public String getPassDate() {
        return passDate;
    }

    /**
     * 设置进出日期
     *
     * @param passDate 进出日期
     */
    public void setPassDate(String passDate) {
        this.passDate = passDate;
    }

    /**
     * 获取进出时间
     *
     * @return pass_time - 进出时间
     */
    public String getPassTime() {
        return passTime;
    }

    /**
     * 设置进出时间
     *
     * @param passTime 进出时间
     */
    public void setPassTime(String passTime) {
        this.passTime = passTime;
    }

    /**
     * 获取进出人员类型
     *
     * @return personType - 进出人员类型
     */
    public String getPersontype() {
        return persontype;
    }

    /**
     * 设置进出人员类型
     *
     * @param persontype 进出人员类型
     */
    public void setPersontype(String persontype) {
        this.persontype = persontype;
    }

    /**
     * 获取用户进出小区的id
     *
     * @return area_id - 用户进出小区的id
     */
    public Long getAreaId() {
        return areaId;
    }

    /**
     * 设置用户进出小区的id
     *
     * @param areaId 用户进出小区的id
     */
    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    /**
     * 获取工作人员ID
     *
     * @return manager_id - 工作人员ID
     */
    public String getManagerId() {
        return managerId;
    }

    /**
     * 设置工作人员ID
     *
     * @param managerId 工作人员ID
     */
    public void setManagerId(String managerId) {
        this.managerId = managerId;
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