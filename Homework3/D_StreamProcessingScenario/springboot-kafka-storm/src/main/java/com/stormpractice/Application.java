package com.stormpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.stormpractice.storm.TopologyApp;
import com.stormpractice.util.GetSpringBean;

@SpringBootApplication
public class Application{

	public static void main(String[] args) {
		// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		GetSpringBean springBean=new GetSpringBean();
		springBean.setApplicationContext(context);
		TopologyApp app = context.getBean(TopologyApp.class);
		app.runStorm(args);
	}
}
