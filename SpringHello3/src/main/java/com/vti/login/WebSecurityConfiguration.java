package com.vti.login;

import com.vti.entites.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.vti.services.IAccountService;

@Component
@EnableWebSecurity // để kích hoạt tính năng Spring Security trên ứng dụng Web
//thông báo cho spring biết web này config thế nào
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    JWTAuthorizationFilter jwtAuthorizationFilter;

    @Autowired
    //từ userName client nhập sẽ nhảy vào interface này,và nó thấy interface đã extends thằng UserDetailsService và nó thấy có hàm loadUserByUsername
    private IAccountService accountService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //ở data đang config pass dưới dạng bcryp cho nên spring nó sẽ encode pass client gửi thành dạng bcryp xong nó sẽ so sánh với nhau,nếu giống nó sẽ tính pass đúng
        //->chỉ cần nhớ cấu hình 3 chỗ,đầu tiên method loadUserByUsername dùng load thông tin user dựa trên userName và cung cấp cho nó 1 cái mã hóa new BCryptPasswordEncoder mà database đang mã hóa kiểu gì
        auth.userDetailsService(accountService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.csrf().disable();
        http
                .addFilterBefore( //add lại filter của thằng servlet  vào trước khi vào thằng UsernamePasswordAuthenticationFilter
                        //"/v1/api/login" là đường dẫn để vào api login khi vào thằng JWTAuthenticationFilter này nó sẽ xuống để check
                        new JWTAuthenticationFilter("/v1/api/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)//giải mã ra trước khi nhảy vào thằng này,authenticate user bằng các giá trị username và pass
                .addFilterBefore(//add trước khi validate username và pass
                        jwtAuthorizationFilter,// add trước khi mà validate thông tin vào,khi đã parse ra thì có được thông tin thằng này
                        UsernamePasswordAuthenticationFilter.class
                );


    }
}
