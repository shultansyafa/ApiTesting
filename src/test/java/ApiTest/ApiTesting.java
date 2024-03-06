package ApiTest;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;


public class ApiTesting {

    @Test
    public void testGetPositif() {
        RestAssured.when()
                .get("https://reqres.in/api/unknown/2")
                .then().log().all()
                .assertThat().statusCode(200);



    }

    @Test
    public void testGetNegatif() {

            RestAssured.when()
                    .get("https://reqres.in/api/unknown/23")
                    .then().log().all()
                    .assertThat().statusCode(404);





        }

        @Test
        public void testGetEdge(){

        RestAssured.when()
                .get("https://reqres.in/api/users/2")
                .then().log().all()
                .assertThat().statusCode(200);

        }

        @Test
    public void testPostCreateUser(){

        String valueName = "shultan";
        String valueJob = "QA";

            JSONObject bodyObject = new JSONObject();

            bodyObject.put("name", valueName);
            bodyObject.put("job", valueJob);

            RestAssured.given()
                    .header("Content-type", "application/json")
                    .header("Accept", "application/json")
                    .body(bodyObject.toString())
                    .when()
                    .post("https://reqres.in/api/users")
                    .then().log().all()
                    .assertThat().statusCode(201)
                    .assertThat().body("name", Matchers.equalTo(valueName));

        }

        @Test
    public void testPutUser(){

        RestAssured.baseURI = "https://reqres.in/";


       int userId = 2;
        String newName = "dani";

        String fname = given().when().get("api/users/"+userId).getBody().jsonPath().get("data.first_name");
        String lname = given().when().get("api/users/"+userId).getBody().jsonPath().get("data.last_name");
        String avatar = given().when().get("api/users/"+userId).getBody().jsonPath().get("data.avatar");
        String email = given().when().get("api/users/"+userId).getBody().jsonPath().get("data.email");
            System.out.println("name before = "+fname);


            HashMap<String, Object> bodyMap = new HashMap<>();
            bodyMap.put("id", userId);
            bodyMap.put("email", email);
            bodyMap.put("first_name", newName);
            bodyMap.put("last_name", lname);
            bodyMap.put("avatar", avatar);
            JSONObject jsonObject = new JSONObject(bodyMap);

            given().log().all()
                    .header("Content_type", "application/json")
                    .body(jsonObject.toString())
                    .put("api/users/"+userId)
                    .then().log().all()
                    .assertThat().statusCode(200);

        }

        @Test
    public void testDeleteUsers(){

        RestAssured.baseURI = "https://reqres.in/";

        int userToDelete = 4;

        given().log().all()
                .when().delete("api/users/" + userToDelete)
                .then().log().all()
                .assertThat().statusCode(204);
        }




    }

