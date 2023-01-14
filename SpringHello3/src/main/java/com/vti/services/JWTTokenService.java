package com.vti.services;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.alibaba.fastjson.JSON;
import com.vti.login.ResponseLogin;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

public class JWTTokenService {
    private static final long EXPIRATION_TIME = 864000000; // 10 days
    private static final String SECRET = "123456";
    private static final String PREFIX_TOKEN = "Bearer";
    private static final String AUTHORIZATION = "Authorization";

    //nhận về 1 response và có username
    public static void addJWTTokenToHeader(HttpServletResponse response, String username) throws IOException {
        // Sinh ra token
        String JWT = Jwts.builder()
                // gán những thông tin cần thiết vào token
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))//set thời gian token
                // khai báo loại mã hóa token:HS512 và chữ ký bí mật:SECRET
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        ResponseLogin responseLogin = new ResponseLogin();
        responseLogin.setToken(JWT);
        responseLogin.setUsername(username);
        // response.addHeader(AUTHORIZATION, PREFIX_TOKEN + " " + JWT);//add vào response chuỗi JWT mới cấu hình vào
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().print(JSON.toJSONString(responseLogin));

    }


    public static Authentication parseTokenToUserInformation(HttpServletRequest request, IAccountService accountService) {
        String token = request.getHeader(AUTHORIZATION);//get ra đoạn mã token

        if (token == null) {
            return null;
        }

        // parse the token phân tích đoạn mã
        String username = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(PREFIX_TOKEN, " "))
                .getBody()
                .getSubject();
        // mình sẽ pha lấy ra thông tin user , cho thằng userDetails hứng kết quả
        UserDetails userDetails = accountService.loadUserByUsername(username);

        return username != null ?
                new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities()) ://lấy  thằng getAuthorities() để so sánh với đối tượng Authority('MANAGER') chứ không phải so sánh đối tượng role
                null;
    }
}
