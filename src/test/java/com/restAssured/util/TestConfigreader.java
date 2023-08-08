package com.restAssured.util;

import java.util.ResourceBundle;

public class TestConfigreader {
        private static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

    public static String getBaseUrl() {
        return resourceBundle.getString("baseURL");
    }

//     public String getBearerToken(){
//        return resourceBundle.getString("bearerToken");
//    }

}
