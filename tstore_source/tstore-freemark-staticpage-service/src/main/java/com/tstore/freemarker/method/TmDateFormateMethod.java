package com.tstore.freemarker.method;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Component;

import freemarker.template.SimpleDate;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;


/**
 * 
 * 使用：${dateFormat(date,"yyyy-MM-dd")}
 * TmCharacterMethod<br/>
 * 创建人:xuchengfeifei<br/>
 * 手机/微信:15074816437<br/> 
 * 时间：2019年3月11日-下午11:03:52 <br/>
 * @version 1.0.0<br/>
 *
 */
@Component("dateFormat")
public class TmDateFormateMethod implements TemplateMethodModelEx {
	// 这里的长度计算，以汉字为标准，两个字母作为一个字符
	@SuppressWarnings("rawtypes")
	// args 是参数
	public Object exec(List args) throws TemplateModelException {
		if (args.size() > 2) {
			throw new TemplateModelException("Wrong arguments!");
		}
		SimpleDate date = (SimpleDate) args.get(0);//获取第一个参数
		String pattern = String.valueOf(args.get(1));//获取第二个参数
		return new SimpleDateFormat(pattern).format(date.getAsDate());
	}
}
