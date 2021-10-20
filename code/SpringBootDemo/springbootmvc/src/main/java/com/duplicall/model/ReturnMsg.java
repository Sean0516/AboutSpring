package com.duplicall.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description ReturnMsg
 * @Author Sean
 * @Date 2021/8/5 11:32
 * @Version 1.0
 */
@NoArgsConstructor
@Data
public class ReturnMsg {

    /**
     * data : {"datalist":"{\"returnCode\":\"0000\",\"returnMsg\":\"Login success!\",\"success\":\"2000\",\"accessToken\":\"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJwYXNzd29yZFwiOlwiRmFud2FuZzcxM1wiLFwiYWxpYXNcIjpcIkFCQ1wiLFwidXNlcmlkXCI6XCI3MDAwMDAwMFwifSIsImlwIjoiMTAuMjMwLjE4NS41IiwiaXNzIjoiaXNzdWVyIiwiZXhwIjoxNjIzODkyMTg3LCJpYXQiOjE2MjM4MDU3ODcsImp0aSI6IjIwMjEtMDYtMTZUMDk6MDk6NDcuMTE3In0.vvgrgQpa-77_4zu60X3Yqq4q8zurx0dcOSV7iO_XJYE\"}"}
     */

    @JsonProperty("data")
    private DataDTO data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        /**
         * datalist : {"returnCode":"0000","returnMsg":"Login success!","success":"2000","accessToken":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJwYXNzd29yZFwiOlwiRmFud2FuZzcxM1wiLFwiYWxpYXNcIjpcIkFCQ1wiLFwidXNlcmlkXCI6XCI3MDAwMDAwMFwifSIsImlwIjoiMTAuMjMwLjE4NS41IiwiaXNzIjoiaXNzdWVyIiwiZXhwIjoxNjIzODkyMTg3LCJpYXQiOjE2MjM4MDU3ODcsImp0aSI6IjIwMjEtMDYtMTZUMDk6MDk6NDcuMTE3In0.vvgrgQpa-77_4zu60X3Yqq4q8zurx0dcOSV7iO_XJYE"}
         */

        @JsonProperty("datalist")
        private String datalist;
    }
}
