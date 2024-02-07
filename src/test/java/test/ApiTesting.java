package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ApiTesting {

    @Test
    public void testPositif() {
        RestAssured.when()
                .get("https://reqres.in/api/unknown/2")
                .then().log().all()
                .assertThat().statusCode(200);



    }

    @Test
    public void testNegatif() {

            String requestBody = "{ \"parameter\": \"string_value\" }";
            RestAssured.when()
                    .get("https://reqres.in/api/unknown/23")
                    .then().log().all()
                    .assertThat().statusCode(404);





        }

        @Test
        public void testEdge(){

        String requestBody = "{ \"parameter\": " + Integer.MAX_VALUE + " }";

        RestAssured.when()
                .get("https://reqres.in/api/users/2")
                .then().log().all()
                .assertThat().statusCode(200);

        }


    }

