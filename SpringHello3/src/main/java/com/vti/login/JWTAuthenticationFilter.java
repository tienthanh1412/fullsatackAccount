package com.vti.login;

import com.vti.services.JWTTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
//cứ khi vào thằng này thì nó
public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
//khi client nhập vào đường dẫn url(xem thằng nào login,lấy token):String url:/v1/api/login,khi vào thằng này nó thực hiện check

    public  JWTAuthenticationFilter(String url , AuthenticationManager authManager){
        super (new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }


//sau đó sẽ vào filter này dùng để lấy param client nhập rồi  authen(xác thực xem username và pass đúng k)check ở WebSecurityConfiguration(method configure)
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException, IOException, ServletException
    {
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getParameter("username"),//các key này để là gì thì phần body form data phải để giống
                        request.getParameter("password"),
                        Collections.emptyList()//login k qtam đến phân quyền
                )
        );
    }

//sau khi check thành công username và pass hoặc phân quyền xong sẽ trả về hàm này
    protected void successfulAuthentication(
            HttpServletRequest requets,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        //add 1 cái JWT token vào header  do mình quy định
        JWTTokenService.addJWTTokenToHeader(response,authResult.getName());//Sau khi thành công nó sẽ gán thông tin vào hàm addJWTTokenToHeader
    }

}
