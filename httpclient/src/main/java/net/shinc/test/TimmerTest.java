package net.shinc.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时器
 * @ClassName timmer 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年8月13日 上午10:32:14
 */
public class TimmerTest {

	public static void main(String[] args) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				System.out.println("Hello");
			}
			
		};
		
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable, 5, 3, TimeUnit.SECONDS);
	}

}
