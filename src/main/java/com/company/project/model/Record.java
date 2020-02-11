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
}