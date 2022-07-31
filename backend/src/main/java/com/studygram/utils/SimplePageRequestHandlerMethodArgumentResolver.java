package com.studygram.utils;

import com.studygram.common.SimplePageRequest;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class SimplePageRequestHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private static final String DEFAULT_OFFSET_PARAMETER = "offset";

    private static final String DEFAULT_LIMIT_PARAMETER = "limit";

    private static final int DEFAULT_OFFSET = 0;

    private static final int DEFAULT_LIMIT = 5;

    private String offsetParameterName = DEFAULT_OFFSET_PARAMETER;

    private String limitParameterName = DEFAULT_LIMIT_PARAMETER;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return SimplePageRequest.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String offsetString = webRequest.getParameter(offsetParameterName);
        String limitString = webRequest.getParameter(limitParameterName);


        int offset = DEFAULT_OFFSET;
        int limit = DEFAULT_LIMIT;

        if(StringUtils.hasText(offsetString)) {
            offset = Integer.valueOf(offsetString);
            if(offset < 0 || offset > Long.MAX_VALUE) {
                offset = DEFAULT_OFFSET;
            }
        }

        if(StringUtils.hasText(limitString)) {
            limit = Integer.valueOf(limitString);
            if(limit < 1 || limit > 15) {
                limit = DEFAULT_LIMIT;
            }
        }

        return new SimplePageRequest(limit, offset);
    }
}
