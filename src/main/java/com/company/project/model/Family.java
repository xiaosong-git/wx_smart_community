package com.company.project.model;

import javax.persistence.*;

@Table(name = "h_family")
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 房间id
     */
    @Column(name = "house_id")
    private Long houseId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 0 --正常  1--删除
     */
    private String status;

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
    
    @Column(name = "is_join")
    private String isJoin;


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
     * 获取房间id
     *
     * @return house_id - 房间id
     */
    public Long getHouseId() {
        return houseId;
    }

    /**
     * 设置房间id
     *
     * @param houseId 房间id
     */
    public void setHouseId(Long houseId) {
        this.houseId = houseId;
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
     * 获取0 --正常  1--删除
     *
     * @return status - 0 --正常  1--删除
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置0 --正常  1--删除
     *
     * @param status 0 --正常  1--删除
     */
    public void setStatus(String status) {
        this.status = status;
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

	public String getIsJoin() {
		return isJoin;
	}

	public void setIsJoin(String isJoin) {
		this.isJoin = isJoin;
	}
    
    
}