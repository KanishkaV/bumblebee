package com.bumble.bee.app.models.error;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public abstract class ErrorReportDto {

    private String code;
}
