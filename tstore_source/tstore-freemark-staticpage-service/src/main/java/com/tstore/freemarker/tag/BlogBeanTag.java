//package com.itbooking.freemarker.tag;
//
//import java.io.IOException;
//import java.util.Map;
//
//import javax.servlet.ServletContext;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.ServletContextAware;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import com.rumo.service.blog.BlogServiceImpl;
//import com.rumo.service.blog.IBlogService;
//import com.rumo.tag.TmTemplateDirectiveModelUtil;
//import com.rumo.util.TmStringUtils;
//
//import freemarker.core.Environment;
//import freemarker.ext.beans.BeansWrapperBuilder;
//import freemarker.template.Configuration;
//import freemarker.template.TemplateDirectiveBody;
//import freemarker.template.TemplateDirectiveModel;
//import freemarker.template.TemplateException;
//import freemarker.template.TemplateModel;
//
///**
// * 内容博文模块
//  BlogBeanTag
//  *  创建人:中华墨风 
//  *  时间：2018年09月26日 21:46:48
// * @version 1.0.0
// *
// */
//@Component("blogBean")
//public class BlogBeanTag implements TemplateDirectiveModel ,ServletContextAware {
//	private ServletContext servletContext;
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public void execute(Environment env, Map params, TemplateModel[] loopVars,
//			TemplateDirectiveBody body) throws TemplateException, IOException {
//		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//		IBlogService blogService = (IBlogService) applicationContext.getBean(BlogServiceImpl.class);
//		String var = TmTemplateDirectiveModelUtil.getString("var", params);
//		Integer id = TmTemplateDirectiveModelUtil.getInteger("id", params);
//		if(TmStringUtils.isEmpty(var))var="blog";
//		Map<String, Object> blog = (Map<String, Object>) blogService.getBlogMapById(id).getData();
//		if (blog != null) {
//			env.setVariable(var, new BeansWrapperBuilder(Configuration.VERSION_2_3_28).build().wrap(blog));
//		}
//
//		if (body != null) {
//			if (blog != null) {
//				body.render(env.getOut());
//			}
//		}
//	}
//	
//	@Override
//	public void setServletContext(ServletContext servletContext) {
//		this.servletContext = servletContext;
//	}
//}
