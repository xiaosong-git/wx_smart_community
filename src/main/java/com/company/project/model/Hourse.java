package com.company.project.model;

import javax.persistence.*;

@Table(name = "h_hourse")
public class Hourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 楼栋id
     */
    @Column(name = "building_id")
    private Long buildingId;

    /**
     * 门牌号
     */
    private String num;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 是否出租 -- T --F
     */
    @Column(name = "is_rent")
    private String isRent;

    /**
     * 租户id
     */
    @Column(name = "rent_id")
    private String rentId;

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
     * 获取楼栋id
     *
     * @return building_id - 楼栋id
     */
    public Long getBuildingId() {
        return buildingId;
    }

    /**
     * 设置楼栋id
     *
     * @param buildingId 楼栋id
     */
    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    /**
     * 获取门牌号
     *
     * @return num - 门牌号
     */
    public String getNum() {
        return num;
    }

    /**
     * 设置门牌号
     *
     * @param num 门牌号
     */
    public void setNum(String num) {
        this.num = num;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取是否出租 -- T --F
     *
     * @return is_rent - 是否出租 -- T --F
     */
    public String getIsRent() {
        return isRent;
    }

    /**
     * 设置是否出租 -- T --F
     *
     * @param isRent 是否出租 -- T --F
     */
    public void setIsRent(String isRent) {
        this.isRent = isRent;
    }

    /**
     * 获取租户id
     *
     * @return rent_id - 租户id
     */
    public String getRentId() {
        return rentId;
    }

    /**
     * 设置租户id
     *
     * @param rentId 租户id
     */
    public void setRentId(String rentId) {
        this.rentId = rentId;
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