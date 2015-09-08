package net.shinc.config;

import net.shinc.aop.log.LoggingAspect;
import net.shinc.common.ErrorMessage;
import net.shinc.common.IRestMessage;
import net.shinc.common.RestMessage;

import org.apache.http.HttpHost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.LocaleResolver;

@Configuration
public class AppConfig {
    
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * @return 
	 */
	@Bean
	@Scope("request")
	public IRestMessage restMessage(LocaleResolver locale) {
		
		String code = ErrorMessage.ERROR_DEFAULT.getCode();
		RestMessage msg = new RestMessage(code, messageSource, locale.resolveLocale(null));
		return msg;
	}
	
	@Bean
    public LoggingAspect loggingAspect() {
		LoggingAspect la = new LoggingAspect();
		return la;
    }
	
	@Bean
	public CloseableHttpClient httpClient() {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		// Increase max total connection to 200
		cm.setMaxTotal(200);

		CloseableHttpClient httpClient = HttpClients.custom()
		        .setConnectionManager(cm)
		        .build();
		
		return httpClient;
	}
	
	@Bean
	public ThreadPoolTaskExecutor threadPoolExecutor() {
		ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();  
		//线程池所使用的缓冲队列  
		poolTaskExecutor.setQueueCapacity(200);  
		//线程池维护线程的最少数量  
		poolTaskExecutor.setCorePoolSize(5);  
		//线程池维护线程的最大数量  
		poolTaskExecutor.setMaxPoolSize(1000);  
		//线程池维护线程所允许的空闲时间  
		poolTaskExecutor.setKeepAliveSeconds(30000); 
		return poolTaskExecutor;
	}
}
