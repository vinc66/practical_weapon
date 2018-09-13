package com.zxc.task;

import com.zxc.service.app.UserService;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.NDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TaskSchedule {

    @Autowired
    private UserService userService;

    //    校验是否有需要拜访的用户信息并发送通知
    @Scheduled(cron = "${task.notify.check}")
    public void execNotify() {
        NDC.push(String.valueOf(System.currentTimeMillis()));
        // FIXME: 2018/9/13
        NDC.clear();
    }


}
