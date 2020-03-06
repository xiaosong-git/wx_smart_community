package com.company.project.model;

import javax.persistence.*;

@Table(name = "h_visitor_record")
public class VisitorRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 访问日期
     */
    @Column(name = "visit_date")
    private String visitDate;

    /**
     * 访问时间
     */
    @Column(name = "visit_time")
    private String visitTime;

    /**
     * 访客id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 被访者id
     */
    @Column(name = "visitor_id")
    private Long visitorId;

    /**
     * 访问原因
     */
    private String reason;

    /**
     * 状态 applying:申请中，applySuccess:接受访问，applyFail:拒绝访问
     */
    private String cstatus;

    /**
     * 开始日期
     */
    @Column(name = "start_date")
    private String startDate;

    /**
     * 结束日期
     */
    @Column(name = "end_date")
    private String endDate;

    /**
     * 被访者回复
     */
    @Column(name = "answer_content")
    private String answerContent;

    /**
     * 被访者小区编码
     */
    @Column(name = "area_id")
    private Long areaId;

    /**
     * 1--访问，2--邀约
     */
    @Column(name = "recordType")
    private Integer recordtype;

    /**
     * 审核日期
     */
    @Column(name = "reply_date")
    private String replyDate;

    /**
     * 审核时间
     */
    @Column(name = "reply_time")
    private String replyTime;

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
     * 获取访问日期
     *
     * @return visit_date - 访问日期
     */
    public String getVisitDate() {
        return visitDate;
    }

    /**
     * 设置访问日期
     *
     * @param visitDate 访问日期
     */
    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    /**
     * 获取访问时间
     *
     * @return visit_time - 访问时间
     */
    public String getVisitTime() {
        return visitTime;
    }

    /**
     * 设置访问时间
     *
     * @param visitTime 访问时间
     */
    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    /**
     * 获取访客id
     *
     * @return user_id - 访客id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置访客id
     *
     * @param userId 访客id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取被访者id
     *
     * @return visitor_id - 被访者id
     */
    public Long getVisitorId() {
        return visitorId;
    }

    /**
     * 设置被访者id
     *
     * @param visitorId 被访者id
     */
    public void setVisitorId(Long visitorId) {
        this.visitorId = visitorId;
    }

    /**
     * 获取访问原因
     *
     * @return reason - 访问原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置访问原因
     *
     * @param reason 访问原因
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * 获取状态 applying:申请中，applySuccess:接受访问，applyFail:拒绝访问
     *
     * @return cstatus - 状态 applying:申请中，applySuccess:接受访问，applyFail:拒绝访问
     */
    public String getCstatus() {
        return cstatus;
    }

    /**
     * 设置状态 applying:申请中，applySuccess:接受访问，applyFail:拒绝访问
     *
     * @param cstatus 状态 applying:申请中，applySuccess:接受访问，applyFail:拒绝访问
     */
    public void setCstatus(String cstatus) {
        this.cstatus = cstatus;
    }

    /**
     * 获取开始日期
     *
     * @return start_date - 开始日期
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * 设置开始日期
     *
     * @param startDate 开始日期
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取结束日期
     *
     * @return end_date - 结束日期
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * 设置结束日期
     *
     * @param endDate 结束日期
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取被访者回复
     *
     * @return answer_content - 被访者回复
     */
    public String getAnswerContent() {
        return answerContent;
    }

    /**
     * 设置被访者回复
     *
     * @param answerContent 被访者回复
     */
    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    /**
     * 获取被访者小区编码
     *
     * @return area_id - 被访者小区编码
     */
    public Long getAreaId() {
        return areaId;
    }

    /**
     * 设置被访者小区编码
     *
     * @param areaId 被访者小区编码
     */
    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    /**
     * 获取1--访问，2--邀约
     *
     * @return recordType - 1--访问，2--邀约
     */
    public Integer getRecordtype() {
        return recordtype;
    }

    /**
     * 设置1--访问，2--邀约
     *
     * @param recordtype 1--访问，2--邀约
     */
    public void setRecordtype(Integer recordtype) {
        this.recordtype = recordtype;
    }

    /**
     * 获取审核日期
     *
     * @return reply_date - 审核日期
     */
    public String getReplyDate() {
        return replyDate;
    }

    /**
     * 设置审核日期
     *
     * @param replyDate 审核日期
     */
    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    /**
     * 获取审核时间
     *
     * @return reply_time - 审核时间
     */
    public String getReplyTime() {
        return replyTime;
    }

    /**
     * 设置审核时间
     *
     * @param replyTime 审核时间
     */
    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }
}