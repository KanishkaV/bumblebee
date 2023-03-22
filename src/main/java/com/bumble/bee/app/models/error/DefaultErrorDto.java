package com.bumble.bee.app.models.error;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class DefaultErrorDto extends ErrorReportDto {

    private String description;

    @Builder
    public DefaultErrorDto(String code, String description) {
        super(code);
        this.description = description;
    }

    public static DefaultErrorDto of(String code, String description) {
        return new DefaultErrorDto(code, description);
    }

    public static DefaultErrorDto of(String description) {
        return new DefaultErrorDto(ErrorCodes.INTERNAL_SERVER_ERROR, description);
    }

}
