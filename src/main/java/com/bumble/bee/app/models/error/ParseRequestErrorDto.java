package com.bumble.bee.app.models.error;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class ParseRequestErrorDto extends DefaultErrorDto {

    private static final String PARAMETERS_VALUE_PATTERN = "Unable to parse '%s' parameter value";

    private static final String MESSAGE_NOT_READABLE = "Unable to parse request";

    @Builder(builderMethodName = "parseRequestErrorDtoBuilder")
    public ParseRequestErrorDto(String description) {
        super(ErrorCodes.PARSE_ERROR, description);
    }

    public static ParseRequestErrorDto withParameter(String paramName) {
        return new ParseRequestErrorDto(String.format(PARAMETERS_VALUE_PATTERN, paramName));
    }

    public static ParseRequestErrorDto withMessage(String description) {
        return new ParseRequestErrorDto(description);
    }

    public static ParseRequestErrorDto notReadableRequest() {
        return new ParseRequestErrorDto(MESSAGE_NOT_READABLE);
    }

}
