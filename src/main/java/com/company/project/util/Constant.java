package com.company.project.util;

/**
 * 项目参数
 */
public class Constant {

    /**
     * 是否是开发模式，项目部署到正式环境要改为false
     */
    public static final Boolean IS_DEVELOP = true;
    /**
     * 时间日期默认格式
     */
    public static final String DATE_FORMAT_DEFAULT ="yyyy-MM-dd HH:mm:ss";
    /**
     *  分页信息：默认每页显示几条记录
     */
    public static final Integer PAGESIZE = 10;

    public static final String DEFAULT_BACCOLOR = "#313942";
    //普通消息
    public static final Integer MASSEGETYPE_NOMAL=1;
    //访客
    public static final Integer MASSEGETYPE_VISITOR=2;
    //回应邀请
    public static final Integer MASSEGETYPE_REPLY=3;
    //访问
    public static final Integer RECORDTYPE_VISITOR=1;
    //邀约信息
    public static final Integer RECORDTYPE_INVITE=2;
    //房间预订状态
    public static final Integer ROOM_STATUS_RESERVE=1;
    //预定成功可退款状态
    public static final Integer ROOM_STATUS_SUCCESS=2;
    //预定成功不可退款状态
    public static final Integer ROOM_STATUS_FINISH=3;
    //房间取消预订状态或超时未支付
    public static final Integer ROOM_STATUS_CANCLE=4;
    //设备类型 ios
    public static final Integer DEVICE_IOS=1;
    //设备类型 Andriod
    public static final Integer DEVICE_ANDRIOD=2;

    //本地同意邀约url
    public static final String URL="http://47.96.71.163:8082/visitor/qrcode.jsp?id=";


}
