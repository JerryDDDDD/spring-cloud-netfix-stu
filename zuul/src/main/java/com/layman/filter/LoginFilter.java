package com.layman.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName LoginFilter
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/8/23 21:49
 * @Version 3.0
 **/
@Component
public class LoginFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);
    
    /**    
     * @Author 叶泽文
     * @Description 配置过了类型
     * @Date 21:51 2019/8/23
     * @Param []
     * @return java.lang.String
     *
     * 1. pre:路由之前
     * 2. routing:路由之时
     * 3. post:路由之后
     * 4. error:发送错误调用
     **/
    @Override
    public String filterType() {
        return "pre";
    }

    // 配置过滤的顺序
    @Override
    public int filterOrder() {
        return 0;
    }

    // 配置是否需要过滤: true/需要, false/不需要
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**    
     * @Author 叶泽文
     * @Description 过滤器执行的业务代码
     * @Date 21:54 2019/8/23
     * @Param []
     * @return java.lang.Object
     **/
    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        logger.info("{}>>>{}", request.getMethod(), request.getRequestURL().toString());
        String token = request.getParameter("token");
        if (token == null) {
            logger.warn("token is empty");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            try {
                context.getResponse().getWriter().write("Token is empty");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logger.info("OK");
        }
        return null;
    }
}
