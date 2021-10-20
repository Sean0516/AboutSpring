package com.duplicall.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description User
 * @Author Sean
 * @Date 2021/8/5 12:34
 * @Version 1.0
 */
@NoArgsConstructor
@Data
public class User {

    /**
     * data : {"menuNo":"308","method":"getFuncList","shortName":"CCMS","optionalParams":"{'userid':'70000000','alias':'ABC','hierarchy':'1','centerCode':'1','userToSkillInfo':'1','stationIp':'10.22.123.2','userVdnInfo':'','scheduleInfo':'1'}"}
     */

    @JsonProperty("data")
    private DataDTO data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        /**
         * menuNo : 308
         * method : getFuncList
         * shortName : CCMS
         * optionalParams : {'userid':'70000000','alias':'ABC','hierarchy':'1','centerCode':'1','userToSkillInfo':'1','stationIp':'10.22.123.2','userVdnInfo':'','scheduleInfo':'1'}
         */

        @JsonProperty("menuNo")
        private String menuNo;
        @JsonProperty("method")
        private String method;
        @JsonProperty("shortName")
        private String shortName;
        @JsonProperty("optionalParams")
        private String optionalParams;
    }
}
