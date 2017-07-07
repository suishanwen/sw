package com.sw.base.filter;

import com.sw.base.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;


@Singleton
@WebFilter("/api/*")
public class SecurityFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);
    private boolean restSecurity = false;

    /**
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        try {
//            String restSecurityStr = SystemProperty.getHerenProperty("REST_SECURITY");
//            if (!Strings.isNullOrEmpty(restSecurityStr)) {
//                restSecurity = Boolean.valueOf(restSecurityStr);
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        boolean accessFlag = false;//是否存在访问权限

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
//        String pathInfo = httpServletRequest.getPathInfo();
//        if (pathInfo.contains("security")) {
//            accessFlag = true;
//        } else {
//            String token = httpServletRequest.getHeader("Authorization");
//            if (!Strings.isNullOrEmpty(token)) {
//                try {
//                    String desDecrypt = SymmetricEncryptionUtil.symmetricDecrypt(token);
//                    if (!Strings.isNullOrEmpty(desDecrypt)) {
//                        String[] split = desDecrypt.split(";");
//                        if (split.length == 3 && !Strings.isNullOrEmpty(split[0]) && !Strings.isNullOrEmpty(split[1]) && !Strings.isNullOrEmpty(split[2]) ) {
//                            httpServletRequest.setAttribute("empId", split[0]);
//                            httpServletRequest.setAttribute("appId", split[1]);
//                            accessFlag = true;
//                        }
//                    }
//                } catch (Exception e) {
//                    logger.error("解析token失败，此token无效！");
//                }
//            }
//        }
//        if (restSecurity && !accessFlag) {
//            logger.error("无权限访问此api");
//            httpServletResponse.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
//        } else {
            filterChainDoFilter(servletRequest, servletResponse, filterChain);
//        }
    }

    private void filterChainDoFilter (ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (IOException | ServletException | RuntimeException e) {
            logger.warn(e.getMessage(), e);
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON);
            try {
                httpServletResponse.getWriter().print(JsonUtils.toJson(e.getMessage()));
            } catch (IOException e1) {
                logger.warn(e1.getMessage(), e1);
                httpServletResponse.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            }
        }
    }

    @Override
    public void destroy() {

    }
}
