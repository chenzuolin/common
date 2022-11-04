package com.common.util;

import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: TODO
 * @author: chenzuolin
 * @date: 2021/8/3 10:01
 * @version: 1.0
 */
public class CopyBeanUtil {

    /**
     * @Description: TODO 对象浅拷贝
     * @Author: chenzuolin
     * @CreateDate: 2021/10/26 10:54
     * @Params: [source, target]  需要拷贝的对象,目标对象
     * @Return: T 泛型标示
     */
    public static <T> T copyBean(Object source, Class<T> target) {
        T newInstance = null;
        try {
            newInstance = target.newInstance();
            BeanUtils.copyProperties(source, newInstance);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return newInstance;
    }

    /**
     * @Description: TODO 集合浅拷贝
     * @Author: chenzuolin
     * @CreateDate: 2021/10/26 10:54
     * @Params: [source, target] 源集合对象,目标对象
     * @Return: java.util.List<T> 源集合泛型标示,目标集合泛型
     */
    public static <E, T> List<T> copyBeanList(List<E> source, Class<T> target) {
        if (source.isEmpty() || null == source) {
            return Collections.emptyList();
        }
        return source.stream().map(item -> copyBean(item, target)).collect(Collectors.toList());
    }

}
