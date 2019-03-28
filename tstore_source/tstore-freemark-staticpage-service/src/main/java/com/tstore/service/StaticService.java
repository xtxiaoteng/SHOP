package com.tstore.service;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tstore.freemarker.RequestUtil;
import com.tstore.pojo.Item;
import com.tstore.service.item.IItemService;
import com.tstore.util.TmStringUtils;

import ch.qos.logback.classic.Logger;
import freemarker.template.Configuration;
import freemarker.template.Template;


@Service
public class StaticService {

	@Reference(check = false)
	private IItemService iItemService;
	
	
	/**
	 * 
	 * 静态化商品详细页
	 * 方法名：staticItem<br/>
	 * 创建人：xiaoteng <br/>
	 * 时间：2019年3月12日-下午8:42:43 <br/>
	 * 手机/微信:18820148139<br/> void<br/>
	 * @exception <br/>
	 * @since  1.0.0<br/>
	 * 
	 * 为什么写入tomcat---因为浏览器可以访问---
	 * 
	 * http://localhost:8080/pages/2019/03/12/s234sdf23423.html
	 */
	public void staticItem(Long itemId,HttpServletRequest request) {
		try {
			//读取当前工程路径
			File templatePath = ResourceUtils.getFile("classpath:templates");
			//1.创建配置类
			Configuration configuration=new Configuration(Configuration.getVersion());
			//2.设置模板所在的目录  classpath:
			configuration.setDirectoryForTemplateLoading(templatePath);
			//3.设置字符集
			configuration.setDefaultEncoding("utf-8");
			//4.加载模板
			Template template = configuration.getTemplate("item.html");
			
			
			//5.创建数据模型 --- 从数据库 List---肯定从数据库里来
			Map<String,Object> root=new HashMap<String,Object>();
			// 图片 + css 服务路径
			String basePath = RequestUtil.getBasePath(request);
			root.put("basePath",basePath);
			//dubbo远程调用商品的服务数据
			Item item = iItemService.getItemById(itemId);
			root.put("item", item);
			
			
			//6.创建Writer对象---指定tomcat的工程目录下，就可以通过http去访问
			String fileName = null;
			File pageFloder = null;
			String dateFinshPath = null;
			String targetPath  = null;
			if(item!=null && TmStringUtils.isNotEmpty(item.getUrl())) {
				String dateFilename = item.getUrl();
				dateFinshPath = dateFilename.substring(0,dateFilename.lastIndexOf("/")+1);
				targetPath = request.getServletContext().getRealPath(dateFinshPath);
				pageFloder = new File(targetPath);
				if(!pageFloder.exists())pageFloder.mkdirs();
				//截图路径的最后一部分名称
				fileName = dateFilename.substring(dateFilename.lastIndexOf("/")+1);///pages/2019/03/12/xxoshdfkahsdk.html
				File file = new File(pageFloder,fileName);
				//如果资源在服务器不存在，那么接下就重新给一个名字
				if(!file.exists()) {
					fileName = TmStringUtils.getRandomString(8)+itemId+TmStringUtils.getRandomString(8) + ".html";
				}
			}else {
				String datePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
				dateFinshPath = "pages/"+datePath+"/";
				targetPath = request.getServletContext().getRealPath(dateFinshPath);
				System.out.println("targetPath:"+targetPath);
				pageFloder = new File(targetPath);
				if(!pageFloder.exists())pageFloder.mkdirs();
				fileName = TmStringUtils.getRandomString(8)+itemId+TmStringUtils.getRandomString(8) + ".html";
			}
			
			//java --- io---磁盘？---文件上传
			// 给文件重命名
			// 写入到服务tomcat中，为什么因为可以通过浏览器访问 nginx
			Writer out =new FileWriter(new File(pageFloder,fileName));
			template.process(root, out);
			
			//更新数据库url，如果你静态资源和静态化资源要经常迁移，建议把服务器路径和资源分开存储。
			//String url = basePath+dateFinshPath+fileName;
			String url = dateFinshPath+fileName;
			iItemService.updateItemUrl(itemId,url);
			
			//8.关闭Writer对象
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	//1：如何关联
	//2:如果有实时改变的数据，如果去更新。--ajax
	//3:数据改变---更新的时候--调用一下静态化即可，
	
}
