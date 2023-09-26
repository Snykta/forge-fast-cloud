package com.snykta.tools.constant;

import com.snykta.tools.utils.CyStrUtil;

import java.util.ArrayList;
import java.util.List;

public class SaTokenConstant {

    private static List<String> un_Authorized_ExceptionList;
    static {
        un_Authorized_ExceptionList = new ArrayList<>();
        un_Authorized_ExceptionList.add("cn.dev33.satoken.exception.NotLoginException");
    }
    private static List<String> un_Permissions_ExceptionList;
    static {
        un_Permissions_ExceptionList = new ArrayList<>();
        un_Permissions_ExceptionList.add("cn.dev33.satoken.exception.NotPermissionException");
        un_Permissions_ExceptionList.add("cn.dev33.satoken.exception.NotRoleException");
    }

    public static boolean isSaAuthorizeException(String exceptionName) {
        return un_Authorized_ExceptionList.stream().anyMatch(ex -> CyStrUtil.startWith(exceptionName, ex, true));
    }

    public static boolean isSaPermissionsException(String exceptionName) {
        return un_Permissions_ExceptionList.stream().anyMatch(ex -> CyStrUtil.startWith(exceptionName, ex, true));
    }




}
