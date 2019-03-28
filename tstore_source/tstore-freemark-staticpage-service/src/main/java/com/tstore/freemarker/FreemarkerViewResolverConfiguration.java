package com.tstore.freemarker;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import freemarker.template.TemplateModel;


/**
 * 
 * freemaker的自动装配类
 * FreemarkerViewResolverConfiguration<br/>
 * 创建人:xioateng<br/>
 * 手机/微信:18820148139<br/> 
 * 时间：2019年3月11日-下午10:56:37 <br/>
 * @version 1.0.0<br/>
 * 
 * 
 * CommandLineRunner 
 * springboot容器初始化完毕之后，会执行run方法
 *
 * spring容器之后---还可以去执行一个通知--Aware、event,beanProcess
 */
@Component
public class FreemarkerViewResolverConfiguration  implements   ApplicationContextAware, CommandLineRunner {
	
	private ApplicationContext context;

	@Autowired
	private FreeMarkerViewResolver resolver;
	
	//@PostConstruct
	// 把springmvc加载FreeMarkerViewResolver额外给Map<String,Object>增加数据.
	public void init() {
		System.out.println("Freemaker 加载进来，，，开始进行数据追加和标签的注册....");
		// 注册路由视图
		resolver.setViewClass(FreemarkerView.class);
		
		// 注册自定义标签
		Map<String, TemplateModel> beans = (Map<String, TemplateModel>) context.getBeansOfType(TemplateModel.class);
		
		for (Entry<String, TemplateModel> entry : beans.entrySet()) {
			resolver.getAttributesMap().put(entry.getKey(), entry.getValue());
		}
		
		System.out.println("Freemaker 数据追加和标签的注册完毕....");
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}

	@Override
	public void run(String... args) throws Exception {
		init();
	}

}