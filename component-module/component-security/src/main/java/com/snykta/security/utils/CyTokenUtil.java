package com.snykta.security.utils;


import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.snykta.security.token.BasicToken;
import com.snykta.tools.constant.AuthConstant;
import com.snykta.tools.exception.ServiceException;
import com.snykta.tools.utils.CyObjUtil;
import com.snykta.tools.utils.CyStrUtil;
import com.snykta.tools.web.result.ResultCode;

public class CyTokenUtil {

    /**
     * 生成token
     * @param basicToken
     * @return
     */
    public static String createToken(BasicToken basicToken) {
        if (StpUtil.isLogin(basicToken.getUserId())) {
            StpUtil.logout(basicToken.getUserId());
        }
        StpUtil.login(basicToken.getUserId());

        SaSession accountSession = StpUtil.getSessionByLoginId(basicToken.getUserId());
        accountSession.set(AuthConstant.token_user_info, basicToken);

        return StpUtil.getTokenValue();
    }


    /**
     * 刷新Token
     *
     * @return
     */
    public static String refreshToken() {
        String oldTokenValue = StpUtil.getTokenValue();
        if (CyStrUtil.isEmpty(oldTokenValue)) {
            throw new ServiceException("token不存在");
        }
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        if (tokenInfo.getIsLogin()) {
            throw new ServiceException("token在有效期内，无需刷新");
        }
        if (tokenInfo.getTokenTimeout() <= 0) {
            throw new ServiceException("token已超时，请重新登录", ResultCode.UN_AUTHORIZED);
        }
        StpUtil.updateLastActiveToNow();
        SaSession accountSession = StpUtil.getSessionByLoginId(StpUtil.getLoginId(), false);
        if (CyObjUtil.isNull(accountSession)) {
            throw new ServiceException("token已超时，请重新登录", ResultCode.UN_AUTHORIZED);
        }
        BasicToken modelToken = accountSession.getModel(AuthConstant.token_user_info, BasicToken.class);
        if (CyObjUtil.isNull(modelToken)) {
            throw new ServiceException("token已超时，请重新登录", ResultCode.UN_AUTHORIZED);
        }
        // 生成新token
        return createToken(modelToken);
    }


    /**
     * 校验Token
     * @return
     */
    public static BasicToken validateToken() {
        String oldTokenValue = StpUtil.getTokenValue();
        if (CyStrUtil.isEmpty(oldTokenValue)) {
            throw new ServiceException("未认证，请登录", ResultCode.UN_AUTHORIZED);
        }
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        if (!tokenInfo.getIsLogin()) {
            throw new ServiceException("未认证，请登录", ResultCode.UN_AUTHORIZED);
        }
        SaSession accountSession = StpUtil.getSessionByLoginId(tokenInfo.getLoginId(), false);
        BasicToken modelToken = accountSession.getModel(AuthConstant.token_user_info, BasicToken.class);
        if (CyObjUtil.isNull(modelToken)) {
            throw new ServiceException("未认证，请登录", ResultCode.UN_AUTHORIZED);
        }
        return modelToken;
    }


    /**
     * 退出登录
     */
    public static void doLogout() {
        String oldTokenValue = StpUtil.getTokenValue();
        if (CyStrUtil.isEmpty(oldTokenValue)) {
            return;
        }
        StpUtil.logoutByTokenValue(oldTokenValue);
    }


    /**
     * 获取用户实体信息
     * @param loginId
     * @return
     */
    public static BasicToken getBasicToken(Object loginId) {
        BasicToken basicToken = new BasicToken();
        SaSession accountSession = StpUtil.getSessionByLoginId(loginId, false);
        if (CyObjUtil.isNotNull(accountSession)) {
            basicToken = accountSession.getModel(AuthConstant.token_user_info, BasicToken.class);
        }
        return basicToken;
    }

}
