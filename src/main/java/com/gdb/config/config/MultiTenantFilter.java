package com.gdb.config.config;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class MultiTenantFilter implements Filter {

    @Value("${multitenant.tenantKey}")
    String tenantKey;

    @Value("${multitenant.defaultTenant}")
    String defaultTenant;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String tenant = req.getHeader(tenantKey) != null ? req.getHeader(tenantKey) : req.getParameter("cmpCode");
        
        ReadableRequestWrapper wrapper = new ReadableRequestWrapper((HttpServletRequest)request);
        String body = wrapper.getBody();
        JSONParser parser = new JSONParser();
        Object obj = null;
		try {
			obj = parser.parse( body );
		} catch (ParseException e) {
			e.printStackTrace();
		}
        JSONObject jsonObj = (JSONObject) obj;
        tenant = (String) jsonObj.get("cmpCode") != null ? (String) jsonObj.get("cmpCode") : tenant;
		if (tenant != null) {
            req.setAttribute(tenantKey, tenant);
        } else {
            req.setAttribute(tenantKey, defaultTenant);
        }

		chain.doFilter(wrapper, response);
//        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
