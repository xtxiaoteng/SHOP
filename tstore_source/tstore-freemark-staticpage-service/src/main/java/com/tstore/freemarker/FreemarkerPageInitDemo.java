package com.tstore.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tstore.freemarker.method.TmCharacterMethod;
import com.tstore.freemarker.method.TmDateFormateMethod;

import freemarker.template.Configuration;
import freemarker.template.Template;
/**
 *freemaker 原生的注册方式 
 *
 */
public class FreemarkerPageInitDemo {

	public static void main(String[] args)  {
		
		try {
		
			//读取当前工程路径
			String useDir = System.getProperty("user.dir");
			
			//1.创建配置类
			Configuration configuration=new Configuration(Configuration.getVersion());
			//2.设置模板所在的目录  classpath:
			configuration.setDirectoryForTemplateLoading(new File(useDir+"/src/main/resources/templates/"));
			//3.设置字符集
			configuration.setDefaultEncoding("utf-8");
			
			
			//4.加载模板
			Template template = configuration.getTemplate("index2.html");
			
			
			//5.创建数据模型 --- 从数据库 List
			Map<String,Object> root=new HashMap<String,Object>();
			
			List goodsList=new ArrayList();
	
			Map goods1=new HashMap();
			goods1.put("name", null);
			goods1.put("num", 1);
			goods1.put("price", 5.8);
			goods1.put("date", new Date());
	
			Map goods2=new HashMap();
			goods2.put("name", "香蕉");
			goods2.put("num", 2);
			goods2.put("price", 2.5);
			goods2.put("date", new Date());
	
			Map goods3=new HashMap();
	
			goods3.put("name", "橘子");
			goods3.put("num", 3);
			goods3.put("price", 3.2);
			goods3.put("date", new Date());
			
			goodsList.add(goods1);
			goodsList.add(goods2);
			goodsList.add(goods3);
	
			
			root.put("goodsList", goodsList);
			root.put("name", "张三 ");
			root.put("message", "欢迎来到神奇的品优购世界！");
			//注册自定义部分--方法和标签
			root.put("character", new TmCharacterMethod());
			root.put("dateFormat", new TmDateFormateMethod());
			
			//freemarker---springmvc--管理起来的？
			
			//6.创建Writer对象---指定tomcat的工程目录下，就可以通过http去访问
			File pageFloder = new File(useDir+"/src/main/resources/static/pages/");
			if(!pageFloder.exists())pageFloder.mkdirs();
			Writer out =new FileWriter(new File(pageFloder,"test2.html"));
			//new OutputStreamWriter(System.out)
			//往浏览器
			//7.输出 out 
			template.process(root, out);
			//8.关闭Writer对象
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
