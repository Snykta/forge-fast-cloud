package com.snykta.starter.security.utils;


import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.snykta.starter.security.token.BasicAuthToken;
import com.snykta.starter.tools.constant.AuthConstant;
import com.snykta.starter.tools.constant.ExceptionMessageConstant;
import com.snykta.starter.tools.exception.ServiceException;
import com.snykta.starter.tools.utils.CyObjUtil;
import com.snykta.starter.tools.utils.CyStrUtil;
import com.snykta.starter.tools.web.result.ResultCode;

public class CyTokenUtil {

    /**
     * 生成token
     * @param basicAuthToken
     * @return
     */
    public static String createToken(BasicAuthToken basicAuthToken) {
        if (StpUtil.isLogin(basicAuthToken.getUserId())) {
            StpUtil.logout(basicAuthToken.getUserId());
        }
        StpUtil.login(basicAuthToken.getUserId());

        SaSession accountSession = StpUtil.getSessionByLoginId(basicAuthToken.getUserId());
        accountSession.set(AuthConstant.token_user_info, basicAuthToken);

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
        BasicAuthToken modelToken = accountSession.getModel(AuthConstant.token_user_info, BasicAuthToken.class);
        if (CyObjUtil.isNull(modelToken)) {
            throw new ServiceException("token已超时，请重新登录", ResultCode.UN_AUTHORIZED);
        }
        // 生成新token
        return createToken(modelToken);
    }


    /**
     *
     * 校验Token
     * @return
     */
    public static BasicAuthToken validateToken() {
        String oldTokenValue = StpUtil.getTokenValue();
        if (CyStrUtil.isEmpty(oldTokenValue)) {
            throw new ServiceException(ExceptionMessageConstant.ERROR_UN_AUTHORIZED, ResultCode.UN_AUTHORIZED);
        }
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        if (!tokenInfo.getIsLogin()) {
            throw new ServiceException(ExceptionMessageConstant.ERROR_UN_AUTHORIZED, ResultCode.UN_AUTHORIZED);
        }
        SaSession accountSession = StpUtil.getSessionByLoginId(tokenInfo.getLoginId(), false);
        BasicAuthToken modelToken = accountSession.getModel(AuthConstant.token_user_info, BasicAuthToken.class);
        if (CyObjUtil.isNull(modelToken)) {
            throw new ServiceException(ExceptionMessageConstant.ERROR_UN_AUTHORIZED, ResultCode.UN_AUTHORIZED);
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
    public static BasicAuthToken getBasicToken(Object loginId) {
        BasicAuthToken basicAuthToken = new BasicAuthToken();
        SaSession accountSession = StpUtil.getSessionByLoginId(loginId, false);
        if (CyObjUtil.isNotNull(accountSession)) {
            basicAuthToken = accountSession.getModel(AuthConstant.token_user_info, BasicAuthToken.class);
        }
        return basicAuthToken;
    }

}
