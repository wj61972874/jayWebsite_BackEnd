package com.jayblog.weisite.config;

import com.jayblog.weisite.service.impl.ArticleServiceImpl;
import com.jayblog.weisite.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            logger.debug("jwt ==111 : {}", jwt);
            username = jwtUtil.extractUsername(jwt);
            logger.debug("jwt ==222: {}", username);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            logger.debug("jwt ==333: {}", username);
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            logger.debug("jwt == 444: {}", userDetails);
            if (jwtUtil.validateToken(jwt)) {
                logger.debug("jwt == 555: {}", userDetails);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                logger.debug("jwt == 666:");
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                logger.debug("jwt == 777:");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        logger.debug("jwt == 888:");
        chain.doFilter(request, response);
    }
}