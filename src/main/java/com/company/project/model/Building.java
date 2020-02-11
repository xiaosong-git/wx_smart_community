package com.company.project.model;

import javax.persistence.*;

@Table(name = "h_building")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 小区id
     */
    @Column(name = "area_id")
    private Long areaId;

    /**
     * 楼层数
     */
    @Column(name = "total_floor")
    private Integer totalFloor;

    /**
     * 楼名
     */
    private String name;

    /**
     * 门牌范围
     */
    @Column(name = "number_range")
    private String numberRange;

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
     * 获取小区id
     *
     * @return area_id - 小区id
     */
    public Long getAreaId() {
        return areaId;
    }

    /**
     * 设置小区id
     *
     * @param areaId 小区id
     */
    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    /**
     * 获取楼层数
     *
     * @return total_floor - 楼层数
     */
    public Integer getTotalFloor() {
        return totalFloor;
    }

    /**
     * 设置楼层数
     *
     * @param totalFloor 楼层数
     */
    public void setTotalFloor(Integer totalFloor) {
        this.totalFloor = totalFloor;
    }

    /**
     * 获取楼名
     *
     * @return name - 楼名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置楼名
     *
     * @param name 楼名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取门牌范围
     *
     * @return number_range - 门牌范围
     */
    public String getNumberRange() {
        return numberRange;
    }

    /**
     * 设置门牌范围
     *
     * @param numberRange 门牌范围
     */
    public void setNumberRange(String numberRange) {
        this.numberRange = numberRange;
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