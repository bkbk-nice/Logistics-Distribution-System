package com.bkbk.filter;

import com.bkbk.service.impl.SecureServiceImpl;
import com.bkbk.util.JwtUtil;
import com.bkbk.util.JwtUtilForCenter;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class LoginFilter extends OncePerRequestFilter {

    @Autowired
    private SecureServiceImpl secureService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 从请求头中获取token字符串并解析（JwtManager之前文章有详解，这里不多说了）
        Claims claims = JwtUtilForCenter.parse(request.getHeader("Authorization"));
        if (claims != null) {
            // 从`JWT`中提取出之前存储好的用户名
            String username = claims.getSubject();
            // 查询出用户对象

            UserDetails user = secureService.loadUserByUsername(username);
            // 手动组装一个认证对象
            Set<GrantedAuthority> dbAuthsSet = new HashSet<>();
            //增加一个权限
            dbAuthsSet.add(new SimpleGrantedAuthority("ROLE_center"));
            Collection<? extends GrantedAuthority> authorities = dbAuthsSet;
            Authentication authentication = new UsernamePasswordAuthenticationToken(user,
                    user.getPassword(),
                    authorities);
            // 将认证对象放到上下文中
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        chain.doFilter(request, response);

    }
}