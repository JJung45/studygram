package com.studygram.common;

public class SimplePageRequest {
    private final int offset;

    private final int limit;

    public SimplePageRequest() {
        this(0, 5);
    }

    public SimplePageRequest(int limit, int offset) {
        this.offset = offset;
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }
}
