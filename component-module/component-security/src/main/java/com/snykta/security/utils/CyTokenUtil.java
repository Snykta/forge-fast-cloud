package com.snykta.security.utils;


import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.snykta.security.token.BasicToken;
import com.snykta.tools.constant.AuthConstant;
import com.snykta.tools.exception.ServiceException;
import com.snykta.tools.utils.CyObjUtil;
import com.snykta.tools.utils.CyStrUtil;


public class CyTokenUtil {

    public static String createToken(BasicToken basicToken) {
        StpUtil.login(basicToken.getUserId());
        String tokenValue = StpUtil.getTokenValue();

        SaSession tokenSession = StpUtil.getTokenSessionByToken(tokenValue);
        tokenSession.set(AuthConstant.token_user_info, basicToken);
        tokenSession.updateTimeout(AuthConstant.token_session_Timeout);

        return tokenValue;
    }



    public static String refreshToken(String token) {
        String oldTokenValue = StpUtil.getTokenValue();
        if (CyStrUtil.isEmpty(oldTokenValue)) {
            throw new ServiceException("刷新token不存在");
        }
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        if (tokenInfo.getIsLogin()) {
            throw new ServiceException("当前token在有效期内，无需刷新");
        }
        SaSession tokenSession = StpUtil.stpLogic.getTokenSessionByToken(token, false);
        if (CyObjUtil.isNull(tokenSession)) {
            throw new ServiceException("刷新token已超时，请重新登录");
        }
        BasicToken modelToken = tokenSession.getModel(AuthConstant.token_user_info, BasicToken.class);
        if (CyObjUtil.isNull(modelToken)) {
            throw new ServiceException("刷新token已超时，请重新登录");
        }
        // 退出原有登录
        StpUtil.logoutByTokenValue(oldTokenValue);
        // 生成新token
        return createToken(modelToken);
    }



}
