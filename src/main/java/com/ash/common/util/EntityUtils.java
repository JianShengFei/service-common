package com.ash.common.util;

import com.ash.common.page.PagedResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jianshengfei
 * @Description
 * @create 2020-11-24 10:07
 */
@Slf4j
public class EntityUtils {

    /**
     * 组装结果集 Page<Object[]> List
     * @param objects
     * @param list
     * @return
     */
    public static PagedResult getResultPage(Page<Object[]> objects, List list){
        PagedResult pagedResult = new PagedResult();
        pagedResult.setTotalPage(objects.getTotalPages());
        pagedResult.setRecords(objects.getTotalElements());
        pagedResult.setCurrentPage(objects.getNumber() + 1);
        pagedResult.setDataContent(list);
        pagedResult.setHasNext(objects.hasNext());
        return pagedResult;
    }

    /**
     * 将数组数据转换为实体类
     * 此处数组元素的顺序必须与实体类构造函数中的属性顺序一致
     * 注意：转换的实体类需要无参，全参，set，get方法
     * @param list  数组对象集合
     * @param clazz 实体类
     * @param <T>   实体类
     * @param model 实例化的实体类
     * @return 实体类集合
     */
    public static <T> List<T> castEntityList(List<Object[]> list, Class<T> clazz, Object model) {
        List<T> returnList = new ArrayList<T>();
        if (list.isEmpty()) {
            return returnList;
        }
        //获取每个数组集合的元素个数
        Object[] co = list.get(0);

        //获取当前实体类的属性名、属性值、属性类别
        List<Map<String, Object>> attributeInfoList = getFiledsInfo(model);
        //创建属性类别数组
        Class[] c2 = new Class[attributeInfoList.size()];
        //如果数组集合元素个数与实体类属性个数不一致则发生错误
        if (attributeInfoList.size() != co.length) {
            return returnList;
        }
        //确定构造方法
        for (int i = 0; i < attributeInfoList.size(); i++) {
            c2[i] = (Class) attributeInfoList.get(i).get("type");
        }
        try {
            for (Object[] o : list) {
                Constructor<T> constructor = clazz.getConstructor(c2);
                returnList.add(constructor.newInstance(o));
            }
        } catch (Exception ex) {
            log.error("实体数据转化为实体类发生异常：异常信息：{}", ex.getMessage());
            return returnList;
        }
        return returnList;
    }
   
    /**
     * 功能描述: 〈数组转对象〉
     *
     * @param clazz   实体类
     * @param objects 数组
     * @param <T>     实体类
     * @param model   实例化的实体类
     * @return : T
     * @author : taoGuoGuo 2020/6/10 15:05
     */
    public static <T> T castEntity(Object[] objects, Class<T> clazz, Object model) {
        T t = null;
        List<Object[]> list = new ArrayList<>();
        if (objects.length > 0) {
            list.add(objects);
        }
        List<T> ts = castEntityList(list, clazz, model);
        if (!CollectionUtils.isEmpty(ts)) {
            t = ts.get(0);
        }
        return t;
    }
   
     /**
     * 根据属性名获取属性值
     *
     * @param fieldName 属性名
     * @param modle     实体类
     * @return 属性值
     */
    private static Object getFieldValueByName(String fieldName, Object modle) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = modle.getClass().getMethod(getter);
            return method.invoke(modle);
        } catch (Exception e) {
            return null;
        }
    }
   
    /**
     * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
     *
     * @param model 实体类
     * @return list集合
     */
    private static List<Map<String, Object>> getFiledsInfo(Object model) {
        Field[] fields = model.getClass().getDeclaredFields();
        List<Map<String, Object>> list = new ArrayList(fields.length);
        Map<String, Object> infoMap = null;
        for (Field field : fields) {
            infoMap = new HashMap(3);
            infoMap.put("type", field.getType());
            infoMap.put("name", field.getName());
            infoMap.put("value", getFieldValueByName(field.getName(), model));
            if (!"serialVersionUID".equals(field.getName())) {
                list.add(infoMap);
            }
        }
        return list;
    }
   
    /**
     * 功能描述: 〈list分页〉
     *
     * @param list     待分页对象集合
     * @param pageable 分页参数
     * @return : org.springframework.data.domain.Page<T>
     * @author : yangyu 2019/12/4 15:16
     */
    public static <T> Page<T> listConvertToPage(List<T> list, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    /**
     * 功能描述: 〈对象转字符串数组〉
     *
     * @param str1 需要转化的字段
     * @return : java.lang.String[]
     * @author : taoGuoGuo 2020/6/10 15:05
     */
    public static <T> String[] object2StrArray(T t, String[] str1) {
        String[] str2 = null;
        List<String> list2 = new ArrayList();
        if ((t!=null && !t.equals("")) && str1.length > 0) {
            Field[] fields = t.getClass().getDeclaredFields();
            // 需要转化的字段
            for (String s : str1) {
                for (Field field : fields) {
                    // 私有属性必须设置访问权限
                    field.setAccessible(true);
                    try {
                        if (s.equals(field.getName())) {
                            Object o = field.get(t);
                            if (o instanceof LocalDateTime) {
                                LocalDateTime time = (LocalDateTime) o;
                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                list2.add(dtf.format(time));
                            } else if (o instanceof LocalDate) {
                                LocalDate date = (LocalDate) o;
                                list2.add(date.toString());
                            } else {
                                list2.add(o == null ? "" : o.toString());
                            }
                            break;
                        }
                    } catch (Exception e) {
                        list2.add("");
                    }
                }
            }
            if (!CollectionUtils.isEmpty(list2)) {
                str2 = list2.toArray(new String[0]);
            }

        }
        return str2;
    }

    /**
     * 功能描述: 〈对象转字符串数组〉
     *
     * @param str 需要转化的字段
     * @return : java.lang.String[]
     * @author : taoGuoGuo 2020/6/10 15:05
     */
    public static <T> List<String[]> object2StrArray(List<T> t, String[] str) {
        List<String[]> list = new ArrayList<>();
        t.forEach(e -> {
            list.add(object2StrArray(e, str));
        });
        return list;
    }

    public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) {
        if (map == null) {
            return null;
        }
        T obj = null;
        try {
            obj = beanClass.newInstance();
            BeanUtils.populate(obj, map);
        } catch (Exception e) {
            return null;
        }

        return obj;
    }


}