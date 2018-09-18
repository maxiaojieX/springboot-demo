package com.danger.event;

import com.danger.bean.DemoEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by xiaojie.Ma on 2018/9/12.
 */
public class DemoListener implements ApplicationListener<DemoEvent> {
    @Override
    public void onApplicationEvent(DemoEvent demoEvent) {
        String msg = demoEvent.getMsg();
        System.out.println("我[bean-demoListener]接受到了[DemoEvent]发布的消息:" + msg);
    }
}
