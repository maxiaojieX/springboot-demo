package com.danger;
import com.danger.bean.*;
import com.danger.dao.DangerDao;
import com.danger.dao.MyBatisTest;
import com.danger.event.DemoPublisher;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xiaojie.Ma
 * Created by xiaojie.Ma on 2018/3/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DangerApplication.class)
public class StatusTest {

    @Autowired
    private DangerDao dangerDao;
    @Test
    public void test() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        try {
            System.out.println(format.format(format.parse(last).getTime()));
            String a = format.format(format.parse(last).getTime());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(sdf.parse(a));
        } catch (ParseException e) {

        }


    }

    @Test
    public void test2() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        System.out.println(format.format(getYearFirst(currentYear)));
    }
    public static Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        System.out.println(currYearFirst);
        return currYearFirst;
    }

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Test
    public void test3() {

        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        System.out.println(format.format(getYearLast(currentYear)));
    }
    public static Date getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeZone(TimeZone.getTimeZone("Etc/GMT+12"));
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        System.out.println(currYearLast);
        return currYearLast;
    }

    SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    @Test
    public void test4() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        String first = format.format(c.getTime());
        try {
            SimpleDateFormat adsad = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(first);
            System.out.println(date);
            adsad.setTimeZone(TimeZone.getTimeZone("Brazil/East"));
            System.out.println(adsad.format(date));

            SimpleDateFormat londonSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 伦敦
            londonSdf.setTimeZone(TimeZone.getTimeZone("Europe/London"));
            System.out.println(londonSdf.format(date));
        } catch (ParseException e) {
//            return null;
        }
    }

    @Test
    public void test5() {
        long time = TimeUtil.getMonthOfDay(new Date().getTime(),TimeZone.getTimeZone("UTC"));
        System.out.println(time);
        System.setProperty("tz","UTC+12");
        System.out.println(new Date(TimeUtil.getMonthOfDay(new Date().getTime(),TimeZone.getTimeZone("UTC"))));
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("UTC"));
        c.setTimeInMillis(time);
        System.out.println(c.getTime());

    }

    @Test
    public void test6() {

        Date date = new Date();
//        SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ti = "2018-08-27T00:00:00";
//        System.out.println(date);

        DateFormat dformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        dformat.setTimeZone(TimeZone.getTimeZone("GMT+8"));


        try {
            Date d = dformat.parse(ti);
            System.out.println(d);

            DateFormat dformat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            dformat2.setTimeZone(TimeZone.getTimeZone("GMT"));
            System.out.println(dformat2.format(d));


        } catch (ParseException e) {
            e.printStackTrace();
        }

//        Date d = sdf.parse(timeStr);

//        System.out.println(dformat.format(date));
//        System.out.println(dformat.f);


//        try {
//            Date startTime1 = dformat.parse(ti);
//            System.out.println(startTime1.getTime());
//            System.out.println(startTime1);
//            System.out.println(dformat.getTimeZone());
//            dformat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//            Date startTime = dformat.parse(ti);
//            System.out.println(startTime.getTime());
//            System.out.println(startTime);
//            System.out.println(dformat.getTimeZone());
//
//
//            System.out.println(dformat.format(startTime));



//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

    }


    /**
     * 获取code
     */
    @Test
    public void weibo_getCode() {

        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody
                .Builder()
                .add("client_id","3748582985")
                .add("redirect_uri","http://127.0.0.1:8083/weibo_oauth")
                .build();
        Request request = new Request
                .Builder()
                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
//                Referer必须存在
                .addHeader("Referer","http://open.weibo.com/widget/followbutton.php")
                .addHeader("Pragma","no-cache")
                .post(formBody)
                .url("https://api.weibo.com/oauth2/authorize")
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            System.out.println(response.body().string());


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取token
     */
    @Test
    public void weibo_getToken() {
        //
        //
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody
                .Builder()
                .add("client_id","3748582985")
                .add("client_secret","40909b77a33949dfa71850178b9bdf33")
                .add("grant_type","authorization_code")
                .add("code","645b56889ff32530d2eacd790213fdsv")
                .add("redirect_uri","http://127.0.0.1:8083/weibo_oauth")
                .build();
        Request request = new Request
                .Builder()
                .post(formBody)
                .url("https://api.weibo.com/oauth2/access_token")
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            System.out.println(response.body().string());


        } catch (IOException e) {
            e.printStackTrace();
        }
        //5712659759
        //5712659759
        //{"access_token":"2.00XljbOGzffgFE2f62793e10EOEzUU","remind_in":"157679999","expires_in":157679999,"uid":"5712659759","isRealName":"true"}
        //{"access_token":"2.00XljbOGzffgFE2f62793e10EOEzUU","remind_in":"157679999","expires_in":157679999,"uid":"5712659759","isRealName":"true"}
    }

    /**
     * 根据token获取用户信息
     */
    @Test
    public void weibo_getUser() {
//        https://api.weibo.com/oauth2/get_token_info

        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody
                .Builder()
                .add("access_token","2.00XljbOGzffgFE2f62793e10EOEzUU")
                .build();
        Request request = new Request
                .Builder()
                .post(formBody)
                .url("https://api.weibo.com/oauth2/get_token_info")
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            System.out.println(response.body().string());
            //{"uid":5712659759,"appkey":"3748582991","scope":"","create_at":1536628583,"expire_in":157679936}

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 授权收回
     */
    @Test
    public void weibo_desOauth() {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody
                .Builder()
                .add("access_token","2.00XljbOGzffgFE2f62793e10EOEzUU")
                .build();
        Request request = new Request
                .Builder()
                .post(formBody)
                .url("https://api.weibo.com/oauth2/revokeoauth2")
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            System.out.println(response.body().string());
            //{"uid":5712659759,"appkey":"3748582992","scope":"","create_at":1536628583,"expire_in":157679936}

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void git_getCode() {
        //3dc5624bce19edf17682
    }

    @Test
    public void get_getToken() {
        System.setProperty("jdk.tls.client.protocols", "TLSv1.2");
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody
                .Builder()
                .add("client_id","1d687315538dc45c7488")
                .add("client_secret","347403d958aae92fe93037cc3be933b433e2bf32")
                .add("code","f678608378e6e4584603")
                .build();
        Request request = new Request
                .Builder()
                .post(formBody)
                .url("https://github.com/login/oauth/access_token")
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            System.out.println(response.body().string());


        } catch (IOException e) {
            e.printStackTrace();
        }
        //access_token=8083e34b44e19a614b512f852cfdb9cf23581e8a&scope=&token_type=bearer
    }

    /**
     * git获取用户信息
     */
    @Test
    public void git_getUserInfo() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request
                .Builder()
                .get()
                .url("https://api.github.com/user?access_token=8083e34b44e19a614b512f852cfdb9cf23581e8a")
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            System.out.println(response.body().string());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    {
//            "login": "maxiaojieX",
//            "id": 32976330,
//            "node_id": "MDQ6VXNlcjMyOTc2MzMw",
//            "avatar_url": "https://avatars2.githubusercontent.com/u/32976330?v=4",
//            "gravatar_id": "",
//            "url": "https://api.github.com/users/maxiaojieX",
//            "html_url": "https://github.com/maxiaojieX",
//            "followers_url": "https://api.github.com/users/maxiaojieX/followers",
//            "following_url": "https://api.github.com/users/maxiaojieX/following{/other_user}",
//            "gists_url": "https://api.github.com/users/maxiaojieX/gists{/gist_id}",
//            "starred_url": "https://api.github.com/users/maxiaojieX/starred{/owner}{/repo}",
//            "subscriptions_url": "https://api.github.com/users/maxiaojieX/subscriptions",
//            "organizations_url": "https://api.github.com/users/maxiaojieX/orgs",
//            "repos_url": "https://api.github.com/users/maxiaojieX/repos",
//            "events_url": "https://api.github.com/users/maxiaojieX/events{/privacy}",
//            "received_events_url": "https://api.github.com/users/maxiaojieX/received_events",
//            "type": "User",
//            "site_admin": false,
//            "name": "maxiaojie",
//            "company": null,
//            "blog": "",
//            "location": null,
//            "email": null,
//            "hireable": null,
//            "bio": "sources for me",
//            "public_repos": 31,
//            "public_gists": 0,
//            "followers": 5,
//            "following": 3,
//            "created_at": "2017-10-21T07:03:01Z",
//            "updated_at": "2018-09-06T12:41:22Z"
//    }

    @Autowired
    ApplicationContext context;

    @Autowired
    DemoPublisher demoPublisher;
    @Test
    public void testSpringEvent() {
        demoPublisher.publisher("hahaha");
    }

    @Test
    public void testShell() {

        Runtime rt = Runtime.getRuntime();
        Process p = null;
        try {
            p = rt.exec("cmd /c  notepad ");
            System.out.println("-----------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(p.toString());

    }

    @Test
    public void testF() {

//        DecimalFormat df = new DecimalFormat("######0.00");
//        Double a = 3.000;
//        Double a1 = 2.00;
//        Double b = 10.0;
//        System.out.println(b/a1);


        MemcachedUtil.set("ma",60,"xiaojie");
        System.out.println(MemcachedUtil.get("ma"));
    }

//    @Autowired
//    private StringRedisTemplate redis;

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private MyBatisTest myBatisTest;
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void testRedis() {

//        redisTemplate.opsForValue().set("maxiaojie","tom");
//        System.out.println(redisTemplate.opsForValue().get("maxiaojie"));

//        System.out.println(redisUtil.get("maxiaojie"));
//        myBatisTest.insert();

//        Map map = new HashMap();
//        map.put("ma","xiaojie");
//        redisUtil.set("key",map);
//        Map map = (Map) redisUtil.get("key");
//        System.out.println(map);
//        System.out.println(map.get("ma"));
//          Map map = new HashMap();
//          map.put("ma","xiaojie");
//
//          redisUtil.hmset("key2",map);
//          Map map2 = redisUtil.hmget("key");
//        System.out.println(map2.get("ma"));
        redisUtil.setDataBase(1);
        System.out.println(redisUtil.get("aa"));

//        redisUtil.set("jie","ge");
//        System.out.println(redisUtil.get("jie"));


    }

}
