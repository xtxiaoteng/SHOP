package com.xt.elasticsearch.lucene.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;

public class VariousAnalyzers {

	// 为什么不对，为什么要学习中文分词器，会影响后续相关什么？

	private static String str ="蓝瘦香菇，洪荒之力人民中华共和国简称中国,  是一个有13亿人口的国家 i love you";
	public static void main(String[] args) throws IOException {
		Analyzer analyzer = null;
		// 1：为什么要学习分词器 -- 提升查询速度 ，减小索引库体积， 更加语义化和搜索精准和匹配内容
		// 2: 分词器影响es哪里写相关API
		analyzer = new StandardAnalyzer();// 标准分词
		System.out.println("标准分词:" + analyzer.getClass());
		printAnalyzer(analyzer);

		analyzer = new WhitespaceAnalyzer(); // 空格分词
		System.out.println("空格分词:" + analyzer.getClass());
		printAnalyzer(analyzer);

		analyzer = new SimpleAnalyzer(); // 简单分词
		System.out.println("简单分词:" + analyzer.getClass());
		printAnalyzer(analyzer);

		analyzer = new CJKAnalyzer(); // 二分法分词
		System.out.println("二分法分词:" + analyzer.getClass());
		printAnalyzer(analyzer);

		analyzer = new KeywordAnalyzer(); // 关键字分词
		System.out.println("关键字分词:" + analyzer.getClass());
		printAnalyzer(analyzer);

//		analyzer = new StopAnalyzer(Paths.get("src/main/resources/stopwords.dic"));
//
		// 停用词分词
//		System.out.println("停用词分词:" + analyzer.getClass());
//		printAnalyzer(analyzer);

		analyzer = new SmartChineseAnalyzer(); // 中文智能分词
		System.out.println("中文智能分词:" + analyzer.getClass());
		printAnalyzer(analyzer);
	}

	public static void printAnalyzer(Analyzer analyzer) throws IOException {
		StringReader reader = new StringReader(str);
		TokenStream toStream = analyzer.tokenStream(str, reader);
		toStream.reset();// 清空流
		CharTermAttribute teAttribute = toStream.getAttribute(CharTermAttribute.class);
		while (toStream.incrementToken()) {
			System.out.print(teAttribute.toString() + "|");
		}
		System.out.println("\n");
		analyzer.close();
	}
}
