package com.studygram;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Before
    public void setup() {
        RestAssured.port = 9000;
    }

    @Test
    public void 기본path로_접근하면_index_html_호출된다 () throws Exception {
        given()
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType("text/html")
                .body(containsString("권한 관리"));
    }
}