package com.vti.login;

import com.vti.services.AccountService;
import com.vti.services.IAccountService;
import com.vti.services.JWTTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JWTAuthorizationFilter extends GenericFilterBean {
    @Autowired
    private IAccountService accountService;

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws ServletException, IOException {
        //sau khi parse ra xong,trả về đối tượng Authentication
        Authentication authenticator = JWTTokenService.parseTokenToUserInformation((HttpServletRequest) servletRequest, accountService);
        //sau SecurityContextHolder(là interface của Spring Security lưu trữ tất cả các chi tiết liên quan đến bảo mật trong ứng dụng) khi parst
        SecurityContextHolder.getContext().setAuthentication(authenticator);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
