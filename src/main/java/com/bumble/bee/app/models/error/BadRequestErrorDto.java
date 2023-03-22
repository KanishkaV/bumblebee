package com.bumble.bee.app.models.error;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class BadRequestErrorDto extends DefaultErrorDto {

    private static final String MESSAGE = "Bad request";

    @Builder(builderMethodName = "badRequestErrorDtoBuilder")
    public BadRequestErrorDto(String description) {
        super(ErrorCodes.PARSE_ERROR, description);
    }

    public static BadRequestErrorDto withDefaultMessage() {
        return new BadRequestErrorDto(MESSAGE);
    }

    public static BadRequestErrorDto of(String description) {
        return new BadRequestErrorDto(description);
    }

}
