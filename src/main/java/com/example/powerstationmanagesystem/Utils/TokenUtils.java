package com.example.powerstationmanagesystem.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.powerstationmanagesystem.controller.request.LoginRequest;
import com.example.powerstationmanagesystem.entiy.User;

import java.util.Date;

public class TokenUtils {

    //token到期时间10小时
    private static final long EXPIRE_TIME= 5*60*60*1000;
    //密钥盐
    private static final String TOKEN_SECRET="ytlbyt";

    /**
     * 生成token
     */
    public static String sign(LoginRequest user){

        String token=null;
        try {
            Date expireAt=new Date(System.currentTimeMillis()+EXPIRE_TIME);
            token = JWT.create()
                    //发行人
                    .withIssuer("auth0")
                    //存放数据
                    .withClaim("telephone",user.getTelephone())
                    //过期时间
                    .withExpiresAt(expireAt)
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException| JWTCreationException je) {

        }
        return token;
    }


    /**
     * token验证
     * @param token
     * @return
     */
    public static Boolean verify(String token){
        System.out.println(token);
        try {
            //创建token验证器
            JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT decodedJWT=jwtVerifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("telephone: " + decodedJWT.getClaim("telephone").asString());
            System.out.println("过期时间：" + decodedJWT.getExpiresAt());
        } catch (IllegalArgumentException | JWTVerificationException e) {
            //抛出错误即为验证不通过
            return false;
        }
        return true;
    }

}
