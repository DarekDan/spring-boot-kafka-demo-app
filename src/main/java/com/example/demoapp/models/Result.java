package com.example.demoapp.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Result<T> {

    @JsonInclude(value = Include.NON_EMPTY, content = Include.NON_NULL)
    private final T value;

    @JsonInclude(value = Include.NON_EMPTY, content = Include.NON_NULL)
    private final Throwable error;

    @JsonCreator
    private Result(T value, Throwable error) {
        this.value = value;
        this.error = error;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(value, null);
    }

    public static <T> Result<T> error(Throwable error) {
        return new Result<>(null, error);
    }

    public boolean isSuccess() {
        return value != null;
    }

    public boolean isError() {
        return error != null;
    }

    public T getValue() {
        return value;
    }

    public Throwable getError() {
        return error;
    }
}
