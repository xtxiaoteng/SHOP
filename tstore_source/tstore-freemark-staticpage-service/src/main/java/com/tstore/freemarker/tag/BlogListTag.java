//package com.itbooking.freemarker.tag;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.ServletContext;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.ServletContextAware;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import com.github.pagehelper.PageInfo;
//import com.rumo.service.blog.BlogServiceImpl;
//import com.rumo.service.blog.IBlogService;
//import com.rumo.tag.TmTemplateDirectiveModelUtil;
//import com.rumo.util.TmStringUtils;
//import com.rumo.vo.BlogVo;
//import com.rumo.vo.ServerResponse;
//
//import freemarker.core.Environment;
//import freemarker.ext.beans.BeansWrapper;
//import freemarker.ext.beans.BeansWrapperBuilder;
//import freemarker.template.Configuration;
//import freemarker.template.TemplateDirectiveBody;
//import freemarker.template.TemplateDirectiveModel;
//import freemarker.template.TemplateException;
//import freemarker.template.TemplateModel;
//
///**
// *内容博文模块
// *BlogListTag
//  *  创建人:中华墨风
//  *  时间：2018年09月26日 21:46:48
// * @version 1.0.0
// *
// */
//@Component("blogList")
//public class BlogListTag implements TemplateDirectiveModel, ServletContextAware {
//	private ServletContext servletContext;
//
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
//			throws TemplateException, IOException {
//		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//		IBlogService blogService = (IBlogService) applicationContext.getBean(BlogServiceImpl.class);
//		String var = TmTemplateDirectiveModelUtil.getString("var", params);
//		Integer pageNo = TmTemplateDirectiveModelUtil.getInteger("pageNo", params);
//		Integer pageSize = TmTemplateDirectiveModelUtil.getInteger("pageSize", params);
//		Integer categoryId = TmTemplateDirectiveModelUtil.getInteger("cid", params);
//		Integer courseId = TmTemplateDirectiveModelUtil.getInteger("courseId", params);
//		String sort = TmTemplateDirectiveModelUtil.getString("sort", params);
//
//		if (TmStringUtils.isEmpty(var))
//			var = "blog";
//		if (pageNo == null)
//			pageNo = 0;
//		if (pageSize == null)
//			pageSize = 10;
//		if (sort == null)
//			sort = " c.create_time desc ";
//
//		BlogVo blogVo = new BlogVo();
//		blogVo.setSort(sort);
//		blogVo.setPageNo(pageNo);
//		blogVo.setCategoryId(categoryId);
//		blogVo.setCourseId(courseId);
//		blogVo.setPageSize(pageSize);
//
//		BeansWrapper beansWrapper =  new BeansWrapperBuilder(Configuration.VERSION_2_3_28).build();
//		ServerResponse serverResponse = blogService.queryBlogAll(blogVo);
//		PageInfo<Map<String,Object>> pageInfo = (PageInfo<Map<String,Object>>) serverResponse.getData();
//		List<Map<String,Object>> blogs = pageInfo.getList();
//		env.setVariable(var+"_page", beansWrapper.wrap(pageInfo));
//		if (body != null) {
//			int i = 0;
//			env.setVariable(var + "_size", beansWrapper.wrap(blogs.size()));
//			for (Map<String,Object> blog : blogs) {
//				env.setVariable(var,beansWrapper.wrap(blog));
//				env.setVariable(var + "_index", beansWrapper.wrap(i));
//				body.render(env.getOut());
//				i++;
//			}
//		} else {
//		}
//	}
//
//	@Override
//	public void setServletContext(ServletContext servletContext) {
//		this.servletContext = servletContext;
//	}
//}