package com.company.project.weixin;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxMenu;
import com.soecode.wxtools.exception.WxErrorException;

import java.util.ArrayList;
import java.util.Arrays;
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
        setBtn(btnList);
        WxMenu.WxMenuButton btn1 = new WxMenu.WxMenuButton();
        btn1.setName("通行验证");
        btn1.setUrl(URL+"personInfor");
        btn1.setType(WxConsts.MENU_BUTTON_VIEW);
        btnList.add(btn1);
//        WxMenu.WxMenuButton btn2 = new WxMenu.WxMenuButton();
//
//        btn2.setName("实人认证");
//        btn2.setUrl(URL+"auth");
//        btn2.setType(WxConsts.MENU_BUTTON_VIEW);
//        btnList.add(btn2);
        menu.setButton(btnList);
//        String url=null;
//        try {
            //通过公众号访问地址授权
//            url = iService.oauth2buildAuthorizationUrl(URL + "login", "snsapi_userinfo", "233");
//        }catch (WxErrorException w){
//            w.getStackTrace();
//        }
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
        btn2.setName("门禁卡");
        btn2.setUrl(URL+MenuKey.GUARDCARD);
        btn2.setType(WxConsts.MENU_BUTTON_VIEW);
        //按钮三
        WxMenu.WxMenuButton btn3=new WxMenu.WxMenuButton();
        btn3.setName("我的家庭");
        btn3.setUrl(URL+MenuKey.FAMILY);
        btn3.setType(WxConsts.MENU_BUTTON_VIEW);
        btnList.add(btn2);
        btnList.add(btn3);
    }
    public static void initMatchruleMenu() {
        IService iService = new WxService();
        WxMenu menu = new WxMenu();
        List<WxMenu.WxMenuButton> btnList = new ArrayList<>();
        //个性化按钮
        WxMenu.WxMenuButton btn1 = new WxMenu.WxMenuButton();
        btn1.setName("通行验证");
        btn1.setUrl(URL+MenuKey.PASS);
        btn1.setType(WxConsts.MENU_BUTTON_VIEW);
        btnList.add(btn1);
        //默认按钮
        setBtn(btnList);
        menu.setButton(btnList);
        //配置个性化规则
        WxMenu.WxMenuRule menuRule=new WxMenu.WxMenuRule();
        //需要得知标签的id
        menuRule.setTag_id("2");
        menu.setMatchrule(menuRule);
        try {
            //参数1--menu  ，参数2--是否是个性化定制。如果是个性化菜单栏，需要设置MenuRule
            iService.createMenu(menu, true);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        creatMenu();
//个性化菜单
//        initMatchruleMenu();
        IService iService = new WxService();
        try {
//            WxUserTagResult wxUserTagResult = iService.queryAllUserTag();
//            List<WxUserTagResult.WxUserTag> tags = wxUserTagResult.getTags();
//            for (WxUserTagResult.WxUserTag tag : tags) {
//                System.out.println("-----tag_id:"+tag.getId());
//            }
            String s = iService.menuTryMatch("otlyluFmKy3-oThjxdBYGQj2hzLI");
            System.out.println(s);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }


}
