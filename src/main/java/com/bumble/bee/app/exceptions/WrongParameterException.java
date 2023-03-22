package com.bumble.bee.app.exceptions;

import lombok.Getter;

@Getter
public class WrongParameterException extends RuntimeException {

    private final String parameterName;

    private final String parameterValue;

    public WrongParameterException(String parameterName, String parameterValue) {
        this.parameterName = parameterName;
        this.parameterValue = parameterValue;
    }

    @Override
    public String getMessage() {
        return String.format("Unable to parse '%s' parameter value: '%s'", this.getParameterName(), this.getParameterValue());
    }
}
