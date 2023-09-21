package com.fast.start.basic.utils;

import cn.hutool.core.convert.Convert;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class FastConvertUtil extends Convert {


    public static <E,T> List<T> convertToDto(List<E> fromList, Class<T> toClass, String ...ignoreProperties) {
        List<T> list = new ArrayList<>();
        if (FastCollectionUtil.isNotEmpty(fromList)) {
            fromList.forEach(fromClass -> {
                try {
                    list.add(convertToDto(fromClass, toClass, ignoreProperties));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return list;
    }


    public static <E, T> T convertToDto(E fromClass, Class<T> toClass, String... ignoreProperties) {
        if (FastObjUtil.isNull(fromClass)) {
            return null;
        }
        try {
            T toClazz =  toClass.newInstance();
            FastBeanUtil.copyProperties(fromClass, toClazz, ignoreProperties);
            return toClazz;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public static <E,T> List<T> convertToEntity(List<E> fromList, Class<T> toClass, String ...ignoreProperties) {
        List<T> list = new ArrayList<>();
        if(FastCollectionUtil.isNotEmpty(fromList)) {
            fromList.forEach(fromClass -> {
                try {
                    list.add(convertToEntity(fromClass, toClass, ignoreProperties));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return list;
    }



    public static <E, T> T convertToEntity(E fromClass, Class<T> toClass, String... ignoreProperties) {
        if (FastObjUtil.isNull(fromClass)) {
            return null;
        }
        try {
            T toClazz =  toClass.newInstance();
            FastBeanUtil.copyProperties(fromClass, toClazz, ignoreProperties);
            return toClazz;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
