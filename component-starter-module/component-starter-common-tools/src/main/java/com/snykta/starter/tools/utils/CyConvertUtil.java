package com.snykta.starter.tools.utils;

import cn.hutool.core.convert.Convert;
import com.snykta.starter.tools.web.entity.BaseEntity;
import com.snykta.starter.tools.web.page.PageDto;
import com.snykta.starter.tools.web.page.mybatis.MybatisPlusPage;

import java.util.ArrayList;
import java.util.List;

public class CyConvertUtil extends Convert {


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
        if (CyCollectionUtil.isNotEmpty(fromList)) {
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
        if (CyObjUtil.isNull(fromClass)) {
            return null;
        }
        try {
            T toClazz =  toClass.newInstance();
            CyBeanUtil.copyProperties(fromClass, toClazz, ignoreProperties);
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
        if(CyCollectionUtil.isNotEmpty(fromList)) {
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
        if (CyObjUtil.isNull(fromClass)) {
            return null;
        }
        try {
            T toClazz =  toClass.newInstance();
            CyBeanUtil.copyProperties(fromClass, toClazz, ignoreProperties);
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
            CyBeanUtil.copyProperties(fromClazz, toClazz, ignoreProperties);
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
                        CyBeanUtil.copyProperties(p, n);
                    } else {
                        CyBeanUtil.copyProperties(p, n, ignoreProperties);
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
    public static <E, T> PageDto<T> toPageDto(E page, Class<T> clazz) {
        if (CyObjUtil.isNull(page)) {
            return null;
        }
        // 目前默认只转换 MybatisPlus
        MybatisPlusPage mybatisPlusPage = CyBeanUtil.copyProperties(page, MybatisPlusPage.class);
        // 未来如果使用其他分页，比如jpa，则使用 SpringDataJpaPage 或者别的适配类
        return PageDto.<T>builder().pageSize(mybatisPlusPage.getSize())
                .pageNum(mybatisPlusPage.getCurrent())
                .totalCount(mybatisPlusPage.getTotal())
                .totalPage(mybatisPlusPage.getPages())
                .data(listObjectConvert(mybatisPlusPage.getRecords(), clazz, new String[]{}))
                .build();
    }




}
