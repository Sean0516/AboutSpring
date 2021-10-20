package com.duplicall.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Description Login
 * @Author Sean
 * @Date 2021/8/5 11:10
 * @Version 1.0
 */
@lombok.NoArgsConstructor
@lombok.Data
public class Login {
    private DataDTO data;
    @lombok.NoArgsConstructor
    @lombok.Data
    public static class DataDTO {
        private String optionalParams;
    }
}
