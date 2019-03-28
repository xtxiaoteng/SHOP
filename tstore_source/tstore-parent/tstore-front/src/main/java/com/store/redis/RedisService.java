package com.store.redis;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	@Autowired
	RedisTemplate<String, String> redisTemplate;

	/*****************************************
	 * string
	 **********************************************/
	public void set(String key, String value) {
		int addstatus = redisTemplate.boundValueOps(key).append(value);
		redisTemplate.boundValueOps(key).set(value); // 直接追加，如果存在就覆盖,
		redisTemplate.boundValueOps(key).setIfAbsent(value); // 添加，如果存在就不添加，
		redisTemplate.boundValueOps(key).getAndSet(value);// 添加并且获取上一次的值
		System.out.println("添加以后的状态是：" + addstatus);
	}
	
	public void setEx(String key, String value,int timeout) {
		
		redisTemplate.boundValueOps(key).set(value, timeout, TimeUnit.SECONDS);
	}

	public String get(String key) {
		String value = redisTemplate.boundValueOps(key).get();
		System.out.println("获取到的值是：" + value);
		return value;
	}

	public void del(String key) {
		redisTemplate.delete(key);
	}

	/*****************************************
	 * set
	 **********************************************/
	public void setValue() {
		redisTemplate.boundSetOps("nameset").add("曹操");
		redisTemplate.boundSetOps("nameset").add("刘备");
		redisTemplate.boundSetOps("nameset").add("孙权");
	}

	/**
	 * 提取值
	 */
	public void getValue() {
		Set<String> members = redisTemplate.boundSetOps("nameset").members();
		System.out.println(members);
	}

	/**
	 * 删除集合中的某一个值
	 */
	public void deleteValue() {
		redisTemplate.boundSetOps("nameset").remove("孙权");
	}

	/**
	 * 删除整个集合
	 **/
	public void deleteAllValue() {
		redisTemplate.delete("nameset");
	}

	/*****************************************
	 * list
	 **********************************************/

	/**
	 * 右压栈：后添加的对象排在后边
	 */
	public void testSetValue1() {
		redisTemplate.boundListOps("namelist1").rightPush("刘备");
		redisTemplate.boundListOps("namelist1").rightPush("关羽");
		redisTemplate.boundListOps("namelist1").rightPush("张飞");
	}

	/**
	 * 显示右压栈集合
	 */
	public void testGetValue1() {
		List<String> list = redisTemplate.boundListOps("namelist1").range(0, 10);
		System.out.println(list);
	}

	/**
	 * 左压栈：后添加的对象排在前边
	 */
	public void testSetValue2() {
		redisTemplate.boundListOps("namelist2").leftPush("刘备");
		redisTemplate.boundListOps("namelist2").leftPush("关羽");
		redisTemplate.boundListOps("namelist2").leftPush("张飞");
	}

	/**
	 * 显示左压栈集合
	 */
	public void testGetValue2() {
		List<String> list = redisTemplate.boundListOps("namelist2").range(0, 10);
		System.out.println(list);
	}

	/**
	 * （3）根据索引查询元素 查询集合某个元素
	 */
	public void testSearchByIndex() {
		String s = (String) redisTemplate.boundListOps("namelist1").index(1);
		System.out.println(s);
	}

	/**
	 * （4）移除某个元素的值 移除集合某个元素
	 */
	public void testRemoveByIndex() {
		redisTemplate.boundListOps("namelist1").remove(1, "关羽");
	}

	/*****************************************
	 * hash
	 **********************************************/

	// 添加值到hash中
	public void testSetValue() {
		redisTemplate.boundHashOps("namehash").put("a", "唐僧");
		redisTemplate.boundHashOps("namehash").put("b", "悟空");
		redisTemplate.boundHashOps("namehash").put("c", "八戒");
		redisTemplate.boundHashOps("namehash").put("d", "沙僧");
	}

	// （2）提取所有的KEY
	public void testGetKeys() {
		Set s = redisTemplate.boundHashOps("namehash").keys();
		System.out.println(s);
	}

	// （3）提取所有的值
	public void testGetValues() {
		List values = redisTemplate.boundHashOps("namehash").values();
		System.out.println(values);
	}

	// （4）根据KEY提取值
	public void testGetValueByKey() {
		Object object = redisTemplate.boundHashOps("namehash").get("b");
		System.out.println(object);
	}

	// （5）根据KEY移除值
	public void testRemoveValueByKey() {
		redisTemplate.boundHashOps("namehash").delete("c");
	}

	
	/*****************************************
	 * zset
	 **********************************************/

	// 添加值到hash中
	public void testzSetValue() {
		redisTemplate.boundZSetOps("fans").add("a", 40.0);
		redisTemplate.boundZSetOps("fans").add("b", 60.0);
		redisTemplate.boundZSetOps("fans").add("c", 70.0);
		redisTemplate.boundZSetOps("fans").add("d", 80.0);
		redisTemplate.boundZSetOps("fans").add("e", 90.0);
		redisTemplate.boundZSetOps("fans").add("f", 100.0);
	}

	public void testgetZSetValue() {
		Set<String> strings = redisTemplate.boundZSetOps("fans").range(0, -1);
	}

	public void removeZSetValue() {
		Long long1 = redisTemplate.boundZSetOps("fans").remove("a");
		System.out.println("删除成功  = " + long1);
	}

	public void testAdd() {
		redisTemplate.delete("fan1");
		ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
		// 将值添加到键中的排序集合，如果已存在，则更新其分数。
		System.out.println(opsForZSet.add("fan1", "a", 1));// true （这里的1.0可以用1代替,因为用double收参）
		ZSetOperations.TypedTuple<String> objectTypedTuple1 = new DefaultTypedTuple<String>("b", 2.0);// 这里必须是2.0，因为那边是用Double收参
		ZSetOperations.TypedTuple<String> objectTypedTuple2 = new DefaultTypedTuple<String>("c", 3.0);
		Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<ZSetOperations.TypedTuple<String>>();
		tuples.add(objectTypedTuple1);
		tuples.add(objectTypedTuple2);
		System.out.println(opsForZSet.add("fan1", tuples));// 2
		// 通过索引区间返回有序集合指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
		System.out.println(opsForZSet.range("fan1", 0, -1));// [a, b, c]
	}

	public void testRemove() {
		ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
		redisTemplate.delete("fan2");
		opsForZSet.add("fan2", "a", 1);
		System.out.println(opsForZSet.range("fan2", 0, -1));// [a]
		opsForZSet.remove("fan2", "a");
		System.out.println(opsForZSet.range("fan2", 0, -1));// []
	}

	public void testIncrementScore() {
		ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
		redisTemplate.delete("fan3");
		System.out.println(redisTemplate.keys("fan3"));// []
		// 通过增量增加排序集中的元素的分数
		System.out.println(opsForZSet.incrementScore("fan3", "a", -1));// -1.0(可见默认技术为0)
		System.out.println(redisTemplate.keys("fan3"));// [fan3]
	}

	public void testRank() {
		ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
		redisTemplate.delete("fan4");
		opsForZSet.add("fan4", "a", 1);
		opsForZSet.add("fan4", "b", 3);
		opsForZSet.add("fan4", "c", 2);
		opsForZSet.add("fan4", "d", -1);
		System.out.println(opsForZSet.range("fan4", 0, -1));// [d, a, c, b]（从小到大）
		// 在排序集中确定具有值的元素的索引,并返回其索引(从低到高)
		System.out.println(opsForZSet.rank("fan4", "b"));// 3(从小到大且从零开始)
	}

	public void testReverseRank() {
		ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
		redisTemplate.delete("fan5");
		opsForZSet.add("fan5", "a", 1);
		opsForZSet.add("fan5", "b", 3);
		opsForZSet.add("fan5", "c", 2);
		opsForZSet.add("fan5", "d", -1);
		// 当从高到低时，确定排序集中的值的元素的索引。
		System.out.println(opsForZSet.reverseRank("fan5", "b"));// 0(从大到小且从零开始)
	}

	public void testRangeWithScores() {
		ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
		redisTemplate.delete("fan6");
		redisTemplate.boundZSetOps("fan6").add("a", 1);
		redisTemplate.boundZSetOps("fan6").add("b", 3);
		redisTemplate.boundZSetOps("fan6").add("c", 2);
		redisTemplate.boundZSetOps("fan6").add("d", -1);
		// 从排序集中获取开始和结束之间的元组(Tuple)。
		Set<TypedTuple<String>> rangeWithScores = redisTemplate.boundZSetOps("fan6").rangeWithScores(0, -1);
		Iterator<TypedTuple<String>> iterator = rangeWithScores.iterator();
		while (iterator.hasNext()) {
			TypedTuple<String> next = iterator.next();
			System.out.println("value:" + next.getValue() + " score:" + next.getScore());
		}
	}

	public void testRangeByScore() {
		ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
		redisTemplate.delete("fan7");
		opsForZSet.add("fan7", "a", 1);
		opsForZSet.add("fan7", "b", 3);
		opsForZSet.add("fan7", "c", 2);
		opsForZSet.add("fan7", "d", -1);
		// 得到分数在最小和最大值之间的元素。(从小到大)
		Set<String> rangeByScore = opsForZSet.rangeByScore("fan7", 1, 2);
		System.out.println(rangeByScore);// [a, c]
		// 从开始到结束的范围内获取元素，其中分数在分类集合的最小值和最大值之间。
		Set<String> rangeByScore2 = opsForZSet.rangeByScore("fan7", 0, 10, 0, -1);
		System.out.println(rangeByScore2);// [a, c, b]
		Set<String> rangeByScore3 = opsForZSet.rangeByScore("fan7", -1, 3, 0, 1);
		System.out.println(rangeByScore3);// [d]
	}

	public void testCount() {
		ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
		redisTemplate.delete("fan10");
		opsForZSet.add("fan10", "a", 1);
		opsForZSet.add("fan10", "b", 3);
		opsForZSet.add("fan10", "c", 2);
		opsForZSet.add("fan10", "d", -1);
		// 计算排序集中在最小和最大分数之间的元素数。
		Long count = opsForZSet.count("fan10", -1, 2);
		System.out.println(count);// 3
	}

	public void testSizeAndZCard() {
		ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
		redisTemplate.delete("fan11");
		opsForZSet.add("fan11", "a", 1);
		opsForZSet.add("fan11", "b", 3);
		opsForZSet.add("fan11", "c", 2);
		opsForZSet.add("fan11", "d", -1);
		// 返回使用给定键存储的排序集的元素数(其实size()底层就是调用的zCard())
		Long size = opsForZSet.size("fan11");
		System.out.println(size);// 4
		// 使用键获取排序集的大小。
		Long zCard = opsForZSet.zCard("fan11");
		System.out.println(zCard);// 4
	}

	public void testScore() {
		ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
		redisTemplate.delete("fan12");
		opsForZSet.add("fan12", "a", 1);
		opsForZSet.add("fan12", "b", 3);
		opsForZSet.add("fan12", "c", 2);
		opsForZSet.add("fan12", "d", -1);
		// 使用键值从排序集中获取具有值的元素的分数
		Double score = opsForZSet.score("fan12", "b");
		System.out.println(score);// 3.0
	}

	public void testRemoveRange() {
		ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
		redisTemplate.delete("fan13");
		opsForZSet.add("fan13", "a", 1);
		opsForZSet.add("fan13", "b", 3);
		opsForZSet.add("fan13", "c", 2);
		opsForZSet.add("fan13", "d", -1);
		// 使用键从排序集中删除开始和结束之间范围内的元素
		Long removeRange = opsForZSet.removeRange("fan13", 1, 3);
		System.out.println(removeRange);// 3
		System.out.println(opsForZSet.zCard("fan13"));// 1
	}

	public void testRemoveRangeByScore() {
		ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
		redisTemplate.delete("fan13");
		opsForZSet.add("fan13", "a", 1);
		opsForZSet.add("fan13", "b", 3);
		opsForZSet.add("fan13", "c", 2);
		opsForZSet.add("fan13", "d", -1);
		// 使用键从排序集中移除最小和最大值之间的元素
		Long removeRangeByScore = opsForZSet.removeRangeByScore("fan13", 2, 100);
		System.out.println(removeRangeByScore);// 2
	}

	public void testUnionAndStore() {
		ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
		redisTemplate.delete("fan14");
		redisTemplate.delete("fan15");
		redisTemplate.delete("fan16");
		redisTemplate.delete("fan17");
		redisTemplate.delete("fan18");
		opsForZSet.add("fan14", "a", 1);
		opsForZSet.add("fan14", "b", 3);
		opsForZSet.add("fan14", "c", 2);
		opsForZSet.add("fan14", "d", -1);

		opsForZSet.add("fan15", "c", 1);
		opsForZSet.add("fan15", "d", 3);
		opsForZSet.add("fan15", "e", 2);
		opsForZSet.add("fan15", "f", -1);
		// 在键和其他键上的联合排序集合，并将结果存储在目标destIny中(注意相交的元素分数相加)
		Long unionAndStore = opsForZSet.unionAndStore("fan14", "fan15", "fan16");
		System.out.println(unionAndStore);// 6
		Set<TypedTuple<String>> rangeWithScores = opsForZSet.rangeWithScores("fan16", 0, -1);
		Iterator<TypedTuple<String>> iterator = rangeWithScores.iterator();
		while (iterator.hasNext()) {
			TypedTuple<String> next = iterator.next();
			System.out.println("value:" + next.getValue() + " score:" + next.getScore());
			/*
			 * value:f score:-1.0 value:a score:1.0 value:d score:2.0 value:e score:2.0
			 * value:b score:3.0 value:c score:3.0 可以看出，score相加了
			 */
		}
		opsForZSet.add("fan17", "e", 5);
		opsForZSet.add("fan17", "f", -7);
		opsForZSet.add("fan17", "g", 31);
		opsForZSet.add("fan17", "h", -11);
		opsForZSet.add("fan17", "c", -11);
		// 计算给定的多个有序集的并集，并存储在新的 destKey中
		Long unionAndStore2 = opsForZSet.unionAndStore("fan14", Arrays.asList("fan15", "fan17"), "fan18");
		System.out.println(unionAndStore2);// 8
		Set<TypedTuple<String>> rangeWithScores2 = opsForZSet.rangeWithScores("fan18", 0, -1);
		Iterator<TypedTuple<String>> iterator2 = rangeWithScores2.iterator();
		while (iterator2.hasNext()) {
			TypedTuple<String> next = iterator2.next();
			System.out.println("value:" + next.getValue() + " score:" + next.getScore());
			/*
			 * value:h score:-11.0 value:c score:-8.0 value:f score:-8.0 value:a score:1.0
			 * value:d score:2.0 value:b score:3.0 value:e score:7.0 value:g score:31.0
			 */
		}
	}

	public void testIntersectAndStore() {
		ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
		redisTemplate.delete("fan19");
		redisTemplate.delete("fan20");
		redisTemplate.delete("fan21");
		redisTemplate.delete("fan22");
		redisTemplate.delete("fan23");
		opsForZSet.add("fan19", "a", 1);
		opsForZSet.add("fan19", "b", 3);
		opsForZSet.add("fan19", "c", 2);
		opsForZSet.add("fan19", "d", -1);

		opsForZSet.add("fan20", "c", 1);
		opsForZSet.add("fan20", "d", 3);
		opsForZSet.add("fan20", "e", 8);
		opsForZSet.add("fan20", "f", -5);

		opsForZSet.add("fan21", "e", 1);
		opsForZSet.add("fan21", "f", 3);
		opsForZSet.add("fan21", "g", 2);
		opsForZSet.add("fan21", "h", -1);
		opsForZSet.add("fan21", "c", 9);
		// 计算给定的一个与另一个有序集的交集并将结果集存储在新的有序集合 key 中
		Long intersectAndStore = opsForZSet.intersectAndStore("fan19", "fan20", "fan22");
		System.out.println(intersectAndStore);// 2
		Set<TypedTuple<String>> rangeWithScores = opsForZSet.rangeWithScores("fan22", 0, -1);
		Iterator<TypedTuple<String>> iterator = rangeWithScores.iterator();
		while (iterator.hasNext()) {
			TypedTuple<String> next = iterator.next();
			System.out.println("value:" + next.getValue() + " score:" + next.getScore());
			/*
			 * value:d score:2.0 value:c score:3.0
			 */
		}
		// 计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 key 中
		Long intersectAndStore2 = opsForZSet.intersectAndStore("fan19", Arrays.asList("fan20", "fan21"), "fan23");
		System.out.println(intersectAndStore2);// 1
		Set<TypedTuple<String>> rangeWithScores2 = opsForZSet.rangeWithScores("fan23", 0, -1);
		Iterator<TypedTuple<String>> iterator2 = rangeWithScores2.iterator();
		while (iterator2.hasNext()) {
			TypedTuple<String> next = iterator2.next();
			System.out.println("value:" + next.getValue() + " score:" + next.getScore());
			/*
			 * value:c score:12.0
			 */
		}
	}

	public void testScan() {
		ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
		redisTemplate.delete("fan24");
		opsForZSet.add("fan24", "a", 1);
		opsForZSet.add("fan24", "b", 3);
		opsForZSet.add("fan24", "c", 2);
		opsForZSet.add("fan24", "d", -1);
		// 跟iterator一毛一样，遍历集合
		Cursor<TypedTuple<String>> scan = opsForZSet.scan("fan24", ScanOptions.NONE);
		while (scan.hasNext()) {
			ZSetOperations.TypedTuple<String> item = scan.next();
			System.out.println(item.getValue() + ":" + item.getScore());
			/*
			 * d:-1.0 a:1.0 c:2.0 b:3.0
			 */
		}
	}
}
