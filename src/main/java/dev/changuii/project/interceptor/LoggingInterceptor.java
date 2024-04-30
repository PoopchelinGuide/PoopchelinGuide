package dev.changuii.project.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.charset.StandardCharsets;

public class LoggingInterceptor implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        logger.info("data : "  + StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8));
        logger.info("URL :" + request.getRequestURL());
        logger.info("METHOD : "+ request.getMethod());
        logger.info("Client : " + request.getRemoteAddr());

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("URL :" + request.getRequestURL());
        logger.info("METHOD : "+ request.getMethod());
        logger.info("Client : " + request.getRemoteAddr());
        logger.info("Process Result : "+response.getStatus());
    }
}
