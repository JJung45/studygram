package com.studygram.common.oauth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class OAuthApiResponse<T> {

    private final static int SUCCESS = 200;
    private final static int NOT_FOUND = 400;
    private final static int FAILED = 500;
    private final static String SUCCESS_MESSAGE = "SUCCESS";
    private final static String NOT_FOUND_MESSAGE = "NOT FOUND";
    private final static String FAILED_MESSAGE = "서버에서 오류가 발생하였습니다.";
    private final static String INVALID_ACCESS_TOKEN = "Invalid access token.";
    private final static String INVALID_REFRESH_TOKEN = "Invalid refresh token.";
    private final static String NOT_EXPIRED_TOKEN_YET = "Not expired token yet.";

    private final OAtuhApiResponseHeader header;
    private final Map<String, T> body;

    public static <T> OAuthApiResponse<T> success(String name, T body) {
        Map<String, T> map = new HashMap<>();
        map.put(name, body);

        return new OAuthApiResponse(new OAtuhApiResponseHeader(SUCCESS, SUCCESS_MESSAGE), map);
    }

    public static <T> OAuthApiResponse<T> fail() {
        return new OAuthApiResponse(new OAtuhApiResponseHeader(FAILED, FAILED_MESSAGE), null);
    }

    public static <T> OAuthApiResponse<T> invalidAccessToken() {
        return new OAuthApiResponse(new OAtuhApiResponseHeader(FAILED, INVALID_ACCESS_TOKEN), null);
    }

    public static <T> OAuthApiResponse<T> invalidRefreshToken() {
        return new OAuthApiResponse(new OAtuhApiResponseHeader(FAILED, INVALID_REFRESH_TOKEN), null);
    }

    public static <T> OAuthApiResponse<T> notExpiredTokenYet() {
        return new OAuthApiResponse(new OAtuhApiResponseHeader(FAILED, NOT_EXPIRED_TOKEN_YET), null);
    }
}
