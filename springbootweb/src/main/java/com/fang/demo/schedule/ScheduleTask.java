package com.fang.demo.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/4/24 17:41
 * @Modified by:
 */
@Component
public class ScheduleTask {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 1000)
    public void report() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }
}
