package edu.learn.rest.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This edu.learn.rest.filter is use to enable cors only for allowed domains which are listed
 * in environment.properties file
 *
 * Note: only relevant information is being logged because edu.learn.rest.filter invoke on every request
 * @author softpak
 */
@Component
@javax.servlet.annotation.WebFilter(filterName = "CORSFilter",urlPatterns = {"*"}, asyncSupported = true)
public class CORSFilter implements Filter {

    private static String allowedDomains;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String[] domains = CORSFilter.allowedDomains.split(",");

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        for(String domain : domains){
            domain = domain.trim();
            if(domain.equalsIgnoreCase(httpServletRequest.getHeader("origin"))){
                httpServletResponse.setHeader("Access-Control-Allow-Origin", domain);
            }
        }

        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, PATCH, DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, Cache-Control, accept, Content-Type, X-Auth-Token, X-Requested-With");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //edu.learn.rest.filter class is required to implement destroy method
    }

    public CORSFilter(){

    }

    @Autowired
    public CORSFilter(Environment env) {
        CORSFilter.allowedDomains = env.getRequiredProperty("allowed.domains");
    }
}
