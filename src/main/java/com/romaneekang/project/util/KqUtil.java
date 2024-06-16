package com.romaneekang.project.util;

import java.util.Objects;

public class KqUtil {
    public static String appendParam(String returns, String paramId, String paramValue) {
        if (!Objects.equals(returns, "")) {
            if (!Objects.equals(paramValue, "") && paramValue != null) {
                returns += "&" + paramId + "=" + paramValue;
            }
        } else {
            if (!Objects.equals(paramValue, "")) {
                returns = paramId + "=" + paramValue;
            }
        }
        return returns;
    }
}
