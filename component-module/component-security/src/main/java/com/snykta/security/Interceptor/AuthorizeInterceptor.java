package com.snykta.security.Interceptor;

import cn.dev33.satoken.stp.StpInterface;
import com.snykta.security.utils.CyTokenUtil;
import org.springframework.stereotype.Component;
import java.util.List;


/**
 * 授权全局拦截器
 */
@Component
public class AuthorizeInterceptor implements StpInterface {

    /**
     * 权限
     * @param loginId
     * @param loginType
     * @return
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return CyTokenUtil.getBasicToken(loginId).getRightCodeList();
    }


    /**
     * 角色
     * @param loginId
     * @param loginType
     * @return
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return CyTokenUtil.getBasicToken(loginId).getRoleCodeList();
    }


}
