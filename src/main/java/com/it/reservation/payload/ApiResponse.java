package com.it.reservation.payload;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
    "code",
    "message",
    "data"
})
public class ApiResponse {

	private String message;
    private String messageTh;
    private Boolean success;
    private String code;
    private Object data;

    public ApiResponse(Object data) {
        this.data = data;
    }

    public ApiResponse(String message, String messageTh, Boolean success, String code) {
        this.message = message;
        this.messageTh = messageTh;
        this.success = success;
        this.code = code;
    }
}
