package com.brzn.mtgboard.filter;

import com.brzn.mtgboard.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(1)
public class CustomAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if(req.getHeader("authToken")==null){
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "No token");
        } else {
            chain.doFilter(request, response);
        }

    }
}