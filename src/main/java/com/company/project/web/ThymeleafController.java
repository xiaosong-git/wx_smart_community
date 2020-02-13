package com.company.project.web;
import com.company.project.annotation.AuthCheckAnnotation;
import com.company.project.model.User;
import com.company.project.service.UserService;
import com.company.project.weixin.MenuKey;
import com.company.project.weixin.WxController;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConfig;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxJsapiConfig;
import com.soecode.wxtools.bean.WxUserList;
import com.soecode.wxtools.bean.result.WxOAuth2AccessTokenResult;
import com.soecode.wxtools.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


/**
 * @program: weixin
 * @description: 动态网页
 * @author: cwf
 * @create: 2019-09-21 09:24
 **/
@Controller
@RequestMapping("/")
public class ThymeleafController {
    Logger logger = LoggerFactory.getLogger(WxController.class);
    private IService iService = new WxService();
    @Autowired
    private UserService userService;

        @RequestMapping(value = "/login",method= RequestMethod.GET)
        public String login(@RequestParam(name="code",required=false) String code,
                            @RequestParam(name="state",defaultValue = "0" ) String state,Model model) throws WxErrorException {
            model.addAttribute("state", state);
            WxOAuth2AccessTokenResult wxOAuth2AccessTokenResult = iService.oauth2ToGetAccessToken(code);
            //获取微信登入的openid
            WxUserList.WxUser.WxUserGet wxUser=new WxUserList.WxUser.WxUserGet();
            wxUser.setOpenid(wxOAuth2AccessTokenResult.getOpenid());
            wxUser.setLang("zh_CN");
            WxUserList.WxUser wxUser1 = iService.oauth2ToGetUserInfo(wxOAuth2AccessTokenResult.getAccess_token(), wxUser);
            User user = userService.getUser(wxUser1.getOpenid());

            model.addAttribute("user",user);

            return "login";
        }


    @RequestMapping(value = "/bindphone",method= RequestMethod.GET)
    public String bindphone() {
        return "bindphone";
    }

    @RequestMapping(value = "/firstrecord",method= RequestMethod.GET)
    public String firstrecord() {
        return "firstrecord";
    }
    @RequestMapping(value = "/secondrecord",method= RequestMethod.GET)
    public String secondrecord() {
        return "secondrecord";
    }
    @RequestMapping(value = "/recorddetail",method= RequestMethod.GET)
    public String recorddetail() {
        return "recorddetail";
    }
    @RequestMapping(value = "/familyIndex",method= RequestMethod.GET)
    public String familyIndex() {
        return "familyIndex";
    }
    @RequestMapping(value = "/verifyFamily1",method= RequestMethod.GET)
    public String verifyFamily1() {
        return "verifyFamily1";
    }

    @RequestMapping(value = "/verifyFamily2",method= RequestMethod.GET)
    public String verifyFamily2() {
        return "verifyFamily2";
    }

    @RequestMapping(value = "/joinFamily1",method= RequestMethod.GET)
    public String joinFamily1() {
        return "joinFamily1";
    }
    @RequestMapping(value = "/joinFamily2",method= RequestMethod.GET)
    public String joinFamily2() {
        return "joinFamily2";
    }


    @RequestMapping(value = "/familyInfor",method= RequestMethod.GET)
    public String familyInfor() {
        return "familyInfor";
    }

    @RequestMapping(value = "/addPerson",method= RequestMethod.GET)
    public String addPerson() {
        return "addPerson";
    }

    @RequestMapping(value = "/houseInfor",method= RequestMethod.GET)
    public String houseInfor() {
        return "houseInfor";
    }

    @RequestMapping(value = "/authIndex",method= RequestMethod.GET)
    public String authIndex() {
        return "authIndex";
    }

    @RequestMapping(value = "/personInfor",method= RequestMethod.GET)
    public String personInfor(Model model) {
        List<String> jsApiList = new ArrayList<>();
        //需要用到哪些JS SDK API 就设置哪些
        jsApiList.add("scanQRCode");//扫一扫
        logger.info("已进入scan界面");
        try {
            //把config返回到前端进行js调用即可。
            WxJsapiConfig config = iService.createJsapiConfig(MenuKey.URL + "personInfor", jsApiList);
            config.setAppid(WxConfig.getInstance().getAppId());
            System.out.println(config.getNoncestr());
            model.addAttribute("config", config);
            logger.info("进入扫一扫");
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return "personInfor";
    }
    @RequestMapping(value = "/qrcode",method= RequestMethod.GET)
    public String qrcode() {
        return "qrcode";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String auth(Model model) {
        List<String> jsApiList = new ArrayList<>();
        //需要用到哪些JS SDK API 就设置哪些
        jsApiList.add("chooseImage");//拍照或从手机相册中选图接口
        jsApiList.add("onMenuShareQZone");//获取“分享到QQ空间”按钮点击状态及自定义分享内容接口
        jsApiList.add("previewImage");//预览图片接口
        jsApiList.add("uploadImage");//上传图片接口
        jsApiList.add("downloadImage");//下载图片接口
        logger.info("已进入auth界面");
        try {
            //把config返回到前端进行js调用即可。
            WxJsapiConfig config = iService.createJsapiConfig(MenuKey.URL + "auth", jsApiList);
            config.setAppid(WxConfig.getInstance().getAppId());
            System.out.println(config.getNoncestr());
            model.addAttribute("config", config);
            logger.info("进入设置权限");
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return "auth";
    }
    @RequestMapping(value = "/scan", method = RequestMethod.GET)
    public String scan(Model model) {
        List<String> jsApiList = new ArrayList<>();
        //需要用到哪些JS SDK API 就设置哪些
        jsApiList.add("scanQRCode");//扫一扫
        logger.info("已进入scan界面");
        try {
            //把config返回到前端进行js调用即可。
            WxJsapiConfig config = iService.createJsapiConfig(MenuKey.URL + "scan", jsApiList);
            config.setAppid(WxConfig.getInstance().getAppId());
            System.out.println(config.getNoncestr());
            model.addAttribute("config", config);
            logger.info("进入扫一扫");
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return "scan";
    }

}
