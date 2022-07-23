package com.studygram.common;

public class SimplePageRequest {
    private final long offset;

    private final int limit;

    public SimplePageRequest() {
        this(0, 5);
    }

    public SimplePageRequest(long offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public long getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }
}
