package com.yzb.common.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * ClassName: DeepBeanUtils
 * Description: 扩展BeanUtils，因为Spring提供的工具类只能实现单个对象的复制
 * date: 2021/10/11 20:26
 *
 * @author ZhenBang-Yi
 * @version 1.0
 */
public class DeepBeanUtils extends BeanUtils {
    //因为是个工具类所以没必要使用到构造方法，里面都是静态方法
    private DeepBeanUtils() {
    }

    public static <S, T> List<S> copyListProperties(List<T> sources, Supplier<S> supplier) {
        List<S> list = new ArrayList(sources.size());
        for (T source : sources) {
            S obj = supplier.get();
            copyProperties(source, obj);
            list.add(obj);
        }
        return list;
    }
}
