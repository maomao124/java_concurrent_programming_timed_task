package mao.t1;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Project name(项目名称)：java并发编程_定时任务
 * Package(包名): mao.t1
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/10
 * Time(创建时间)： 12:29
 * Version(版本): 1.0
 * Description(描述)： 何让每周四 18:00:00 定时执行任务
 */

public class Test
{
    public static void main(String[] args)
    {
        //当前时间
        LocalDateTime now = LocalDateTime.now();
        //这周周四 18:00:00的时间
        LocalDateTime thursday = now.with(DayOfWeek.THURSDAY).withHour(18).withMinute(0).withSecond(0).withNano(0);
        //判断当前时间是否已经超过了本周的星期四的18:00:00
        if (now.isAfter(thursday))
        {
            //下一周
            thursday = thursday.plusWeeks(1);
        }
        //计算时间差，即延时执行时间
        long initialDelay = Duration.between(now, thursday).toMillis();
        //一周的时间间隔
        long oneWeek = 7 * 24 * 3600 * 1000;
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(2);
        threadPool.scheduleAtFixedRate(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("运行");
            }
        }, initialDelay, oneWeek, TimeUnit.MILLISECONDS);
    }
}
