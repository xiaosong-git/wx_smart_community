package com.company.project.weixin;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxMenu;
import com.soecode.wxtools.bean.result.WxError;
import com.soecode.wxtools.bean.result.WxUserListResult;
import com.soecode.wxtools.bean.result.WxUserTagResult;
import com.soecode.wxtools.exception.WxErrorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.company.project.weixin.MenuKey.URL;

/**
 * @program: weixin
 * @description: 菜单
 * @author: cwf
 * @create: 2019-09-20 10:38
 **/
public class Menu {


    public static void creatMenu(){
        IService iService = new WxService();
        WxMenu menu = new WxMenu();
        //菜单列表
        List<WxMenu.WxMenuButton> btnList = new ArrayList<>();

//        WxMenu.WxMenuButton btn1 = new WxMenu.WxMenuButton();
//        btn1.setName("通行验证");
//        btn1.setUrl(URL+PERSONINFOR);
//        btn1.setType(WxConsts.MENU_BUTTON_VIEW);
//        btnList.add(btn1);
        setBtn(btnList);
//        WxMenu.WxMenuButton btn2 = new WxMenu.WxMenuButton();
//
//        btn2.setName("实人认证");
//        btn2.setUrl(URL+"auth");
//        btn2.setType(WxConsts.MENU_BUTTON_VIEW);
//        btnList.add(btn2);
        menu.setButton(btnList);

        //调用API即可
        try {
            //参数1--menu  ，参数2--是否是个性化定制。如果是个性化菜单栏，需要设置MenuRule
            iService.createMenu(menu, false);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
    //默认按钮
    public static void setBtn(List<WxMenu.WxMenuButton> btnList){
        //按钮二
        WxMenu.WxMenuButton btn2 = new WxMenu.WxMenuButton();
        btn2.setName("通行码");
        btn2.setUrl(URL+MenuKey.QRCODE);
        btn2.setType(WxConsts.MENU_BUTTON_VIEW);
        //按钮三
        WxMenu.WxMenuButton btn3=new WxMenu.WxMenuButton();
        btn3.setName("我的");
        List<WxMenu.WxMenuButton> subList3 = new ArrayList<>();
//        String url=null;
//        WxMenu.WxMenuButton btn3_4 = new WxMenu.WxMenuButton();
//        try {
//            IService iService = new WxService();
////            通过公众号访问地址授权
//
//            url = iService.oauth2buildAuthorizationUrl(URL + "login", "snsapi_userinfo", "233");
//            btn3_4.setType(WxConsts.MENU_BUTTON_VIEW);
//            btn3_4.setUrl(url);
//            //授权登入
//            btn3_4.setName("登入");
//        }catch (WxErrorException w){
//            w.getStackTrace();
//        }
        WxMenu.WxMenuButton btn3_1 = new WxMenu.WxMenuButton();
        btn3_1.setType(WxConsts.MENU_BUTTON_VIEW);
        btn3_1.setName("清除缓存");
        btn3_1.setUrl(URL+"clear");
        //子按钮绑定父按钮

        WxMenu.WxMenuButton btn3_2 = new WxMenu.WxMenuButton();
        btn3_2.setName("我的家庭");
        btn3_2.setUrl(URL+MenuKey.FAMILY);
        btn3_2.setType(WxConsts.MENU_BUTTON_VIEW);
        subList3.addAll(Arrays.asList(btn3_1,btn3_2));
        btn3.setSub_button(subList3);
        btnList.add(btn2);
        btnList.add(btn3);

    }
    //个性化菜单 超管
    public static void initMatchruleMenu() {
        IService iService = new WxService();
        WxMenu menu = new WxMenu();
        List<WxMenu.WxMenuButton> btnList = new ArrayList<>();
        WxMenu.WxMenuButton btn1=new WxMenu.WxMenuButton();
        btn1.setName("物业管理");
        //个性化按钮
        WxMenu.WxMenuButton btn1_1 = new WxMenu.WxMenuButton();
        btn1_1.setName("通行验证");
        btn1_1.setUrl(URL+MenuKey.PERSONINFOR);
        btn1_1.setType(WxConsts.MENU_BUTTON_VIEW);

        WxMenu.WxMenuButton btn1_2 = new WxMenu.WxMenuButton();
        btn1_2.setType(WxConsts.MENU_BUTTON_VIEW);
        btn1_2.setName("添加管理员");
        //添加管理员菜单
        btn1_2.setUrl(URL+"adminInfor");
        WxMenu.WxMenuButton btn1_3 = new WxMenu.WxMenuButton();
        btn1_3.setType(WxConsts.MENU_BUTTON_VIEW);
        btn1_3.setName("下载通行记录");
        //添加管理员菜单
        btn1_3.setUrl(URL+"download");
        List<WxMenu.WxMenuButton> subList1 = new ArrayList<>();
        subList1.addAll(Arrays.asList(btn1_1,btn1_2,btn1_3));
        btn1.setSub_button(subList1);
        btnList.add(btn1);
        //默认按钮
        setBtn(btnList);
        menu.setButton(btnList);
        //配置个性化规则
        WxMenu.WxMenuRule menuRule=new WxMenu.WxMenuRule();
        //需要得知标签的id
        menuRule.setTag_id("100");
        menu.setMatchrule(menuRule);
        try {
            //参数1--menu  ，参数2--是否是个性化定制。如果是个性化菜单栏，需要设置MenuRule
            iService.createMenu(menu, true);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
    //普通管理员
    public static void initManageMenu() {
        IService iService = new WxService();
        WxMenu menu = new WxMenu();
        List<WxMenu.WxMenuButton> btnList = new ArrayList<>();
        //个性化按钮
        WxMenu.WxMenuButton btn1 = new WxMenu.WxMenuButton();
        btn1.setName("通行验证");
        btn1.setUrl(URL+MenuKey.PERSONINFOR);
        btn1.setType(WxConsts.MENU_BUTTON_VIEW);
        //默认按钮
        setBtn(btnList);
        menu.setButton(btnList);
        //配置个性化规则
        WxMenu.WxMenuRule menuRule=new WxMenu.WxMenuRule();
        //需要得知标签的id
        menuRule.setTag_id("101");
        menu.setMatchrule(menuRule);
        try {
            //参数1--menu  ，参数2--是否是个性化定制。如果是个性化菜单栏，需要设置MenuRule
            iService.createMenu(menu, true);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
    public static int createTags() throws WxErrorException {
        IService iService = new WxService();
        WxUserTagResult manageResutl = iService.createUserTag("物业管理");
        System.out.println(manageResutl.getTag().getId());
        return manageResutl.getTag().getId();

    }
    //修改标签名
    public static int updateUserTagName() throws WxErrorException {

        IService iService = new WxService();
        WxError wxError = iService.updateUserTagName(100, "物业超管");
        System.out.println(wxError.toString());
//        return manageResutl.getTag().getId();
    return 0;
    }

    public static int getTags(String manage) throws WxErrorException {
        IService iService = new WxService();
        WxUserTagResult manageResutl = iService.queryAllUserTag();
        List<WxUserTagResult.WxUserTag> tags = manageResutl.getTags();
        for (WxUserTagResult.WxUserTag tag : tags) {
            if(tag.getName().equals(manage)){
                return tag.getId();
            }
        }
        return 0;
    }
    //将用户加入标签
    public static void pushToTags() throws WxErrorException {
        IService iService = new WxService();
        List<String> openIds=new LinkedList<>();
        openIds.add("oFw0JwGlkNWM9DByJR8C76hSgYuc");
        WxError wxError = iService.batchMovingUserToNewTag(openIds, 100);

    }
    //检查标签
    public static void check() throws WxErrorException {
        IService iService = new WxService();
//        List<String> openIds=new LinkedList<>();
//        openIds.add("oFw0JwGlkNWM9DByJR8C76hSgYuc");
        WxUserListResult wxUserListResult = iService.queryAllUserUnderByTag(100, "");
        wxUserListResult.getData();

    }
    //将用户加入标签
    public static void checkMenu() throws WxErrorException {
        IService iService = new WxService();
        try {
            WxUserTagResult wxUserTagResult = iService.queryAllUserTag();
            List<WxUserTagResult.WxUserTag> tags = wxUserTagResult.getTags();
            for (WxUserTagResult.WxUserTag tag : tags) {
                System.out.println("-----tag_id:"+tag.getId());
            }
            String s = iService.menuTryMatch("oFw0JwGlkNWM9DByJR8C76hSgYuc");
            System.out.println(s);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws WxErrorException {
//        creatUserToTag();
//        creatMenu();
//        initMatchruleMenu();
//        initManageMenu();
        pushToTags();
//        check();
//        checkMenu();
//        int tags = getTags();
//        System.out.println(tags);
//        int tags = createTags();
//        createTags();
//        int tags = getTags("物业管理");
//        System.out.println(tags);
//        pushToTags();
//        check();
//个性化菜单
//        pushToTags();
//        check();
//        checkMenu();
    }


}
