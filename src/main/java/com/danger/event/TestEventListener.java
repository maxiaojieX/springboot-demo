package com.danger.event;

import com.danger.bean.EventBean;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by xiaojie.Ma on 2018/9/12.
 */
@Component
public class TestEventListener implements ApplicationListener<TestEvent> {
    @Override
    public void onApplicationEvent(TestEvent testEvent) {
        EventBean eventBean = (EventBean) testEvent.getSource();
        System.out.println(eventBean);
        System.out.println("EVENT");
    }
}
