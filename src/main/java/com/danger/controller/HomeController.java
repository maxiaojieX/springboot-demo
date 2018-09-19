package com.danger.controller;

import com.danger.bean.Danger;
import com.danger.bean.RedisUtil;
import com.danger.dao.DangerDao;
import com.danger.dao.MyBatisTest;
import freemarker.template.utility.StringUtil;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiaojie.Ma on 2018/5/9.
 */
@Controller
public class HomeController {

    @Autowired
    private DangerDao dangerDao;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/")
    public String hello() {
        System.out.println(redisUtil.get("maxiaojie"));
        return "index";
    }
    @GetMapping("/db")
    @ResponseBody
    public String hello2() {
        return dangerDao.get().getQq();
    }

    @RequestMapping("/weibo_oauth")
    @ResponseBody
    public String callback(String code){
        return code;
    }

    @RequestMapping("/github_oauth")
    @ResponseBody
    public String callbackGit(String code){
        return code;
    }


    @Autowired
    private MyBatisTest myBatisTest;

    @RequestMapping("/{appId}/te")
    @ResponseBody
    public String te(@PathVariable String appId) {
//        return dangerDao.get().getQq();
        return myBatisTest.find().getQq();
    }
//    @RequestMapping("/save")
//    @ResponseBody
//    public String save(String hello,String very) {
//        if(org.apache.commons.lang.StringUtils.isBlank(hello) || org.apache.commons.lang.StringUtils.isBlank(very)) {
//            return "BLANK";
//        }
//        if(!org.apache.commons.lang.StringUtils.isNumeric(hello) || hello.length()<8) {
//            return "NOTNUMBER";
//        }
//        if(org.apache.commons.lang.StringUtils.isBlank(very) || very.length()<6) {
//            return "PASSWORDERROR";
//        }
//        Danger danger = new Danger();
//        danger.setQq(hello);
//        danger.setPassword(very);
//        dangerDao.save(danger);
//        return "SUCCESS";
//    }
}
