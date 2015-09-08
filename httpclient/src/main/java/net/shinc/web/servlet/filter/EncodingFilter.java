package net.shinc.web.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {
	private FilterConfig filterConfig=null;
    private String toEncoding="UTF-8";
    
    @Override
    public void destroy() {
        this.filterConfig=null;
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        if(null!=toEncoding)
        {
            request.setCharacterEncoding(toEncoding);
        }
        chain.doFilter(request, response);
        
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig=filterConfig;
        this.toEncoding=this.filterConfig.getInitParameter("encoding");
        
    }
}
