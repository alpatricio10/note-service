package org.ota.noteservice.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String code;
    private String message;
}
