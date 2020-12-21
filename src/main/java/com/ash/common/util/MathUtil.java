package com.ash.common.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

 /**
 * 数学运算工具类
 * @author Jianshengfei
 * @Description
 * @create 2020-12-18 15:45
 */
public class MathUtil {

	 public static void main(String[] args) {
		 boolean evenNumber = isEvenNumber(1);
		 System.out.println(evenNumber);

		 int reversal = reversal(1);
		 System.out.println(reversal);
	 }

	 /**
	  * 判断是否为奇数
	  * @param num
	  * @return boolean
	  */
	 public static boolean isOddNumber(int num){
		 if((num & 1) == 1)
			 return true;
		 return false;
	 }

	 /**
	  * 判断是否为偶数
	  * @param num
	  * @return boolean
	  */
	 public static boolean isEvenNumber(int num){
		 return !isOddNumber(num);
	 }

	 /**
	  * 判断正负数
	  * 说明：正数返回1，负数返回2，0返回0
	  * @param num
	  * @return
	  */
	 public static int isPositiveNumber(int num){
	 	 return (num >> 31) | (~((~num + 1) >> 31) + 1);
	 }

	 /**
	  * 将数字转换负数
	  * @param num
	  * @return -num
	  */
	 public static int reversal(int num) {
	 	 if(isPositiveNumber(num) < 0) return num;

	 	 return ~num + 1;
	 }
 
	/**
	 * 在指定区间取得随机数(整型操作)
	 * 
	 * @param minNumber
	 *            区间下限值
	 * @param maxNumber
	 *            区间上限值
	 * @return 返回区间内的值(整型)
	 */
	public static int randomNumber(int minNumber, int maxNumber) {
		if (minNumber > maxNumber) {
			int temp = minNumber;
			minNumber = maxNumber;
			maxNumber = temp;
		}
		return (int) (minNumber + Math.random() * (maxNumber - minNumber + 1));
	}
	
 
	/**
	 * 在指定区间取得随机数(浮点操作)
	 * 
	 * @param minNumber
	 *            区间下限值
	 * @param maxNumber
	 *            区间上限值
	 * @return 返回区间内的值(浮点)
	 */
	public static double randomDouble(double minNumber, double maxNumber) {
		if (minNumber > maxNumber) {
			double temp = minNumber;
			minNumber = maxNumber;
			maxNumber = temp;
		}
		return (minNumber + Math.random() * (maxNumber - minNumber));
	}
 
	/**
	 * 对指定浮点数取小数位
	 * 
	 * @param value
	 *            浮点数
	 * @param num
	 *            小数位数
	 * @return
	 */
	public static double roundDoubleDecimal(double value, int num) {
		value = value % 1;
		BigDecimal bd = new BigDecimal(value);
		return bd.setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
 
	/**
	 * 对指定浮点数保留相应小数位
	 * 
	 * @param value
	 *            浮点数
	 * @param num
	 *            小数位数
	 * @return
	 */
	public static double roundDouble(double value, int num) {
		BigDecimal bd = new BigDecimal(value);
		return bd.setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
 
	/**
	 * 从指定数组中随机获得一个数据
	 * 
	 * @param <T>
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T random(T... value) {
		return value[randomNumber(0, value.length - 1)];
	}
 
	/**
	 * 向上取整
	 * 
	 * @param a 分子
	 * @param b 分母
	 * @return
	 */
	public static int ceil(int a, int b) {
		if (a % b != 0) {
			return a / b + 1;
		}
		return a / b;
	}
	
	public static interface RandomObject {
		public int getId();
		public int getRate();
	}
	
	/**
	 * 随机返回一个对象
	 * @param list
	 * @return
	 */
	public static <T extends RandomObject> T randomObject(List<T> list) {
		int totalRate = 0;
		for(T temp : list) {
			totalRate += temp.getRate();
		}
		int randomRate = MathUtil.randomNumber(0, totalRate);
		int currRate = 0;
		T t = null;
		for(T temp : list) {
			currRate += temp.getRate();
			if(randomRate <= currRate) {
				t = temp;
				break;
			}
		}
		return t;
	}
	
	
	/**
	 * 生成n个small-big的不重复随机数字
	 * 
	 * @param n
	 *            随机数字个数
	 * @param n
	 *            随机数字个数
	 * @return 随机数字数组
	 */
	public static List<Integer> generateDifNums(int n, int small, int big) {
 
		int length = big - small + 1;
		int[] seed = new int[length];
		for (int i = 0; i < length; i++) {
			seed[i] = small + i;
		}
		List<Integer> ranArr = new ArrayList<Integer>();
		Random ran = new Random();
		for (int i = 0; i < n; i++) {
			int j = ran.nextInt(length - i);
			ranArr.add(seed[j]);
			seed[j] = seed[length - 1 - i];
 
		}
		return ranArr;
	}
	
	/** 
	 * 
	* @param srcData:组合数组 
	* @param n:生成组合个数 
	* @param dstData:存放所有可能的组合数组列表 
	*/ 
	public static void getZuhe(int[] srcData, int srcIndex, int n, List<Integer> tempList, /*out*/List<List<Integer>> dstData) {    
		if (dstData == null || tempList == null) {
			return;
		}
		
		if (srcData.length < n) {
			return;
		}
		
		for (int j = srcIndex; j < srcData.length - (n - 1); j++ ) {
			Integer value = srcData[j];
			tempList.add(value);
			if (n == 1) {
				List<Integer> temp = new ArrayList<Integer>();
				temp.addAll(tempList);
				dstData.add(temp);
			} else {
				n--;
				getZuhe(srcData, j+1, n, tempList, dstData);
				n++;
			}
			
			tempList.remove(value);
		}
	}
}