package com.fast.start.basic.utils;

import cn.hutool.core.convert.Convert;
import com.fast.start.basic.web.entity.BaseEntity;
import com.fast.start.basic.web.page.PageDto;
import com.fast.start.basic.web.page.mybatis.MybatisPlusPage;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;


/**
 * 类与类转换
 */
@Slf4j
public class FastConvertUtil extends Convert {


    /**
     * 将 Entity 转换为 Dto
     * @param fromList
     * @param toClass
     * @param ignoreProperties
     * @param <E>
     * @param <T>
     * @return
     */
    public static <E extends BaseEntity, T> List<T> convertToDto(List<E> fromList, Class<T> toClass, String ...ignoreProperties) {
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


    /**
     * 将 Entity 转换为 Dto
     * @param fromClass
     * @param toClass
     * @param ignoreProperties
     * @param <E>
     * @param <T>
     * @return
     */
    public static <E extends BaseEntity, T> T convertToDto(E fromClass, Class<T> toClass, String... ignoreProperties) {
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


    /**
     *  将 Dto 转换为 Entity
     * @param fromList
     * @param toClass
     * @param ignoreProperties
     * @param <E>
     * @param <T>
     * @return
     */
    public static <E, T extends BaseEntity> List<T> convertToEntity(List<E> fromList, Class<T> toClass, String ...ignoreProperties) {
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


    /**
     * 将 Dto 转换为 Entity
     * @param fromClass
     * @param toClass
     * @param ignoreProperties
     * @param <E>
     * @param <T>
     * @return
     */
    public static <E, T extends BaseEntity> T convertToEntity(E fromClass, Class<T> toClass, String... ignoreProperties) {
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


    /**
     * 两个普通Bean之间的转化
     * @param fromClazz
     * @param clazz
     * @param <E>
     * @param <T>
     * @return
     */
    public static <E, T> T objectConvert(E fromClazz, Class<T> clazz) {
        if(fromClazz == null){
            return  null;
        }
        return objectConvert(fromClazz, clazz, new String[]{});
    }


    /**
     * 两个普通Bean之间的转化
     * @param fromClazz
     * @param clazz
     * @param ignoreProperties
     * @param <E>
     * @param <T>
     * @return
     */
    public static <E, T> T objectConvert(E fromClazz, Class<T> clazz, String ...ignoreProperties) {
        try {
            if (fromClazz == null) {
                return null;
            }
            T toClazz = clazz.newInstance();
            FastBeanUtil.copyProperties(fromClazz, toClazz, ignoreProperties);
            return toClazz;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 两个普通Bean之间的转化
     * @param formList
     * @param clazz
     * @param <E>
     * @param <T>
     * @return
     */
    public static <E,T> List<T> listObjectConvert(List<E> formList,Class<T> clazz){
        return listObjectConvert(formList, clazz, new String[]{});
    }


    /**
     * 两个普通Bean之间的转化
     * @param formList
     * @param clazz
     * @param ignoreProperties
     * @param <E>
     * @param <T>
     * @return
     */
    public static <E,T> List<T> listObjectConvert(List<E> formList, Class<T> clazz, String ...ignoreProperties){
        List<T> list = new ArrayList<>();
        if(formList != null) {
            formList.forEach(p -> {
                T n = null;
                try {
                    n = clazz.newInstance();
                    if(ignoreProperties == null || ignoreProperties.length == 0){
                        FastBeanUtil.copyProperties(p, n);
                    } else {
                        FastBeanUtil.copyProperties(p, n, ignoreProperties);
                    }
                    list.add(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return list;
    }


    /**
     * 将 orm 框架的分页类转换为统一分页结构体
     * 在此默认只转换了 mybatisPlus 的分页
     * 如有需要可以在此扩展适配更多的分页框架
     * @param page
     * @param clazz
     * @param <E>
     * @param <T>
     * @return
     */
    public static <E, T> PageDto<T> toPageDto(E page, Class<T> clazz){
        // 目前默认只转换MybatisPlus
        MybatisPlusPage mybatisPlusPage = FastBeanUtil.copyProperties(page, MybatisPlusPage.class);

        return PageDto.<T>builder().pageSize(mybatisPlusPage.getSize())
                .pageNum(mybatisPlusPage.getCurrent())
                .totalCount(mybatisPlusPage.getTotal())
                .totalPage(mybatisPlusPage.getPages())
                .data(listObjectConvert(mybatisPlusPage.getRecords(), clazz, new String[]{}))
                .build();
    }





}
