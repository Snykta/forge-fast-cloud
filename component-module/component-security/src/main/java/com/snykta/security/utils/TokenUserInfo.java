package com.snykta.security.utils;


import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.snykta.security.token.BasicToken;
import com.snykta.tools.constant.AuthConstant;
import com.snykta.tools.utils.CyObjUtil;



public class TokenUserInfo {


    public static Long getUserId() {
        return getUserTokenInfo().getUserId();
    }



    public static BasicToken getUserTokenInfo() {
        SaSession tokenSession = StpUtil.getTokenSession();
        BasicToken basicToken = tokenSession.getModel(AuthConstant.token_user_info, BasicToken.class);
        if (CyObjUtil.isNull(basicToken)) {
            basicToken = new BasicToken();
        }
        return basicToken;
    }


}
