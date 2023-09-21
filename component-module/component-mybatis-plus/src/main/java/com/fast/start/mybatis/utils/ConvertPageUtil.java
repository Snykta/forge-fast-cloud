package com.fast.start.mybatis.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fast.start.mybatis.page.PageDto;
import java.util.ArrayList;
import java.util.List;

public class ConvertPageUtil {

    /**
     * 分页实体类转换
     * @return
     */
    public static <E,T> PageDto<T> toPageDto(IPage<E> page, Class<T> clazz){
        return   PageDto.<T>builder().pageSize(page.getSize())
                .pageNum(page.getCurrent())
                .totalCount(page.getTotal())
                .totalPage(page.getPages())
                .data(convertToDto(page.getRecords(), clazz))
                .build();
    }

    private static <E,T> List<T> convertToDto(List<E> fromList, Class<T> toClass) {
        List<T> list = new ArrayList<>();
        if (CollUtil.isNotEmpty(fromList)) {
            fromList.forEach(fromClass -> {
                try {
                    T toClazz =  toClass.newInstance();
                    BeanUtil.copyProperties(fromClass, toClazz);
                    list.add(toClazz);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return list;
    }

}
