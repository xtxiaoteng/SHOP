package com.tstore.freemarker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

/**
 * 
 *  freemaker 在调用模板渲染的时候，都会经过这个类FreeMarkerView
 * FreemarkerView<br/>
 * 创建人:xuchengfeifei<br/>
 * 手机/微信:15074816437<br/> 
 * 时间：2019年3月11日-下午10:55:01 <br/>
 * @version 1.0.0<br/>
 *
 */
public class FreemarkerView extends FreeMarkerView {

	@Override
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
		String base = RequestUtil.getBasePath(request);
		model.put("basePath", base);
		model.put("adminPath", base+"/admin");
		model.put("resourcePath", "http://www.itbooking.net/itresources");
		super.exposeHelpers(model, request);
	}
}
