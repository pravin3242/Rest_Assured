package com.restAssured.Pages;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static io.restassured.RestAssured.*;

public class ApiTest {
        public String bearerToken ="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJkOTUzZjNmMi02ODEwLTRjZDAtYjI1ZS1lZDRlMDA5MDRmNWYiLCJzdWIiOiJzdXBlcl9hZG1pbiIsInJvbGVzIjoiUk9MRV9BRE1JTl9DUkVBVEUsUk9MRV9BRE1JTl9ERUxFVEUsUk9MRV9BRE1JTl9ERUxFVEVfU0VMRixST0xFX0FETUlOX1JFQUQsUk9MRV9BRE1JTl9SRUFEX1NFTEYsUk9MRV9BRE1JTl9VUERBVEUsUk9MRV9BRE1JTl9VUERBVEVfU0VMRixST0xFX0FOU1dFUl9RVUVTVElPTl9SRUFELFJPTEVfQkFTRUxJTkVfUVVFU1RJT05fQ1JFQVRFLFJPTEVfQkFTRUxJTkVfUVVFU1RJT05fREVMRVRFLFJPTEVfQkFTRUxJTkVfUVVFU1RJT05fUkVBRCxST0xFX0JBU0VMSU5FX1FVRVNUSU9OX1VQREFURSxST0xFX0NBUkVfUFJPR1JBTV9DUkVBVEUsUk9MRV9DQVJFX1BST0dSQU1fREVMRVRFLFJPTEVfQ0FSRV9QUk9HUkFNX1JFQUQsUk9MRV9DQVJFX1BST0dSQU1fUkVBRF9TRUxGLFJPTEVfQ0FSRV9QUk9HUkFNX1VQREFURSxST0xFX0RFVklDRV9DUkVBVEUsUk9MRV9ERVZJQ0VfREVMRVRFLFJPTEVfREVWSUNFX1JFQUQsUk9MRV9ERVZJQ0VfVVBEQVRFLFJPTEVfRURVQ0FUSU9OX0NSRUFURSxST0xFX0VEVUNBVElPTl9ERUxFVEUsUk9MRV9FRFVDQVRJT05fUkVBRCxST0xFX0VEVUNBVElPTl9SRUFEX1NFTEYsUk9MRV9FRFVDQVRJT05fVVBEQVRFLFJPTEVfSEFCSVRfQ0FSRF9RVUVTVElPTl9DUkVBVEUsUk9MRV9IQUJJVF9DQVJEX1FVRVNUSU9OX0RFTEVURSxST0xFX0hBQklUX0NBUkRfUVVFU1RJT05fUkVBRCxST0xFX0hBQklUX0NBUkRfUVVFU1RJT05fVVBEQVRFLFJPTEVfT1JERVJfQ1JFQVRFLFJPTEVfT1JERVJfREVMRVRFLFJPTEVfT1JERVJfUkVBRCxST0xFX09SREVSX1VQREFURSxST0xFX1BBVElFTlRfQ1JFQVRFLFJPTEVfUEFUSUVOVF9ERUxFVEUsUk9MRV9QQVRJRU5UX0RFTEVURV9TRUxGLFJPTEVfUEFUSUVOVF9SRUFELFJPTEVfUEFUSUVOVF9VUERBVEUsUk9MRV9QQVRJRU5UX1VQREFURV9TRUxGLFJPTEVfUk9MRV9DUkVBVEUsUk9MRV9ST0xFX0RFTEVURSxST0xFX1JPTEVfUkVBRCxST0xFX1JPTEVfUkVBRF9TRUxGLFJPTEVfUk9MRV9VUERBVEUsUk9MRV9TUEVDSUFMSVNUX0NSRUFURSxST0xFX1NQRUNJQUxJU1RfREVMRVRFLFJPTEVfU1BFQ0lBTElTVF9ERUxFVEVfU0VMRixST0xFX1NQRUNJQUxJU1RfUkVBRCxST0xFX1NQRUNJQUxJU1RfUkVBRF9TRUxGLFJPTEVfU1BFQ0lBTElTVF9VUERBVEUsUk9MRV9TUEVDSUFMSVNUX1VQREFURV9TRUxGLFJPTEVfU1VCU0NSSVBUSU9OX0RFTEVURSxST0xFX1NVQlNDUklQVElPTl9SRUFELFJPTEVfU1VCU0NSSVBUSU9OX1JFQURfU0VMRixST0xFX1NVQlNDUklQVElPTl9VUERBVEUsUk9MRV9TVUJTQ1JJUFRJT05fVVBEQVRFX1NFTEYsUk9MRV9UUlNUX0RFRkFVTFQsUk9MRV9XRUVLTFlfUExBTl9DUkVBVEUsUk9MRV9XRUVLTFlfUExBTl9ERUxFVEUsUk9MRV9XRUVLTFlfUExBTl9SRUFELFJPTEVfV0VFS0xZX1BMQU5fVVBEQVRFLFJPTEVfV0VMTF9CRUlOR19RVUVTVElPTl9DUkVBVEUsUk9MRV9XRUxMX0JFSU5HX1FVRVNUSU9OX0RFTEVURSxST0xFX1dFTExfQkVJTkdfUVVFU1RJT05fUkVBRCxST0xFX1dFTExfQkVJTkdfUVVFU1RJT05fVVBEQVRFIiwidXNlcnR5cGUiOiJBRE1JTiIsImlhdCI6MTY5MDk3MTg1OCwiZXhwIjoxNjkwOTczNjU4fQ.piDnAQyrxO4uwfeIvv5gV9C9k4UHyI8062Qwjno6I9g";
        public String baseUrl = "https://qa.api.trsthealth.com";

    @Test()
        public void postRequest(String firstName, String lastName, String countryCode, String phoneNumber, String email, String dob, String timeZone) throws IOException {

            String requestBody = String.format( firstName, lastName,countryCode, phoneNumber, email, dob, timeZone);

            given()
                    .header("Authorization", "Bearer" + bearerToken)
                    .contentType("application/json")
                    .body(requestBody)
                    .when()
                    .post(baseUrl + "/api/v1/users")
                    .then()
                    .statusCode(201);
        }
 }

