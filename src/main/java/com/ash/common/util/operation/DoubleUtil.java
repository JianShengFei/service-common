package com.ash.common.util.operation;

import java.math.BigDecimal;

public class DoubleUtil {
	
	/**
	 * 相加
	 */
    public static double sum(double d1, double d2) { 
        BigDecimal bd1 = new BigDecimal(Double.toString(d1)); 
        BigDecimal bd2 = new BigDecimal(Double.toString(d2)); 	
        return bd1.add(bd2).doubleValue(); 
    } 

	/**
	 * 相减
	 */    
    public static double sub(double d1, double d2) { 
        BigDecimal bd1 = new BigDecimal(Double.toString(d1)); 
        BigDecimal bd2 = new BigDecimal(Double.toString(d2)); 
        return bd1.subtract(bd2).doubleValue(); 
    } 
 
	/**
	 * 相剩
	 */      
    public static double mul(double d1, double d2) { 
        BigDecimal bd1 = new BigDecimal(Double.toString(d1)); 
        BigDecimal bd2 = new BigDecimal(Double.toString(d2)); 
        return bd1.multiply(bd2).doubleValue(); 
    } 
    
	/**
	 * 相除
	 * @param scale 四舍五入小数点位数 
	 */     			
    public static double div(double d1, double d2, int scale) { 
        BigDecimal bd1 = new BigDecimal(Double.toString(d1)); 
        BigDecimal bd2 = new BigDecimal(Double.toString(d2)); 
        return bd1.divide(bd2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();  
    }   
    			
	/**
	 * 四舍五入
	 * 保留2位小数点
	 */      		
    public static double round(double v) { 
    	BigDecimal b = new BigDecimal(Double.toString(v)); 
    	BigDecimal one = new BigDecimal("1"); 
    	return b.divide(one,2,BigDecimal.ROUND_HALF_UP).doubleValue(); 
    } 
    	
	/**
	 * 四舍五入
	 * @param i 保留i位小数点
	 */       
    public static double round(double v, int i) { 
    	BigDecimal b = new BigDecimal(Double.toString(v)); 
    	BigDecimal one = new BigDecimal("1"); 
    	return b.divide(one,i,BigDecimal.ROUND_HALF_UP).doubleValue(); 
    }     
    					
}
