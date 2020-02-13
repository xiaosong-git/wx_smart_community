package com.company.project.model;

import javax.persistence.*;

@Table(name = "h_area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 物业id
     */
    @Column(name = "org_id")
    private Long orgId;

    /**
     * 0--限制 1--不限制
     */
    @Column(name = "pass_limit")
    private Integer passLimit;

    /**
     * 频率 暂时用varchar类型
     */
    private String frequency;
    @Column(name = "area_name")
    private String areaName;
    
    @Column(name = "area_code")
    private String areaCode;
    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String area;



    /**
     * 操作员
     */
    private String opre;

    /**
     * 0--有效 1-无效
     */
    @Column(name = "is_valid")
    private Integer isValid;

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
     * 获取物业id
     *
     * @return org_id - 物业id
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     * 设置物业id
     *
     * @param orgId 物业id
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取0--限制 1--不限制
     *
     * @return pass_limit - 0--限制 1--不限制
     */
    public Integer getPassLimit() {
        return passLimit;
    }

    /**
     * 设置0--限制 1--不限制
     *
     * @param passLimit 0--限制 1--不限制
     */
    public void setPassLimit(Integer passLimit) {
        this.passLimit = passLimit;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    /**
     * 获取频率 暂时用varchar类型
     *
     * @return frequency - 频率 暂时用varchar类型
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * 设置频率 暂时用varchar类型
     *
     * @param frequency 频率 暂时用varchar类型
     */
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    /**
     * 获取省
     *
     * @return province - 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取市
     *
     * @return city - 市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市
     *
     * @param city 市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取区
     *
     * @return area - 区
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置区
     *
     * @param area 区
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取操作员
     *
     * @return opre - 操作员
     */
    public String getOpre() {
        return opre;
    }

    /**
     * 设置操作员
     *
     * @param opre 操作员
     */
    public void setOpre(String opre) {
        this.opre = opre;
    }

    /**
     * 获取0--有效 1-无效
     *
     * @return is_valid - 0--有效 1-无效
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * 设置0--有效 1-无效
     *
     * @param isValid 0--有效 1-无效
     */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
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

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}


}