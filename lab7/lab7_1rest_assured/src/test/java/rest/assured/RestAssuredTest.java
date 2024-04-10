package rest.assured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.lessThan;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RestAssuredTest {
    @Test
    @DisplayName("Test if the endpoint to list all ToDos is available (status code 200)")
    public void testListAllToDos() {
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/todos")
                .then().assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("Test if when querying for ToDo #4, the API returns an object with title “et porro tempora")
    public void testToDfor4Title() {
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/todos/4")
                .then().assertThat()
                .body("title", equalTo("et porro tempora"));
    }

    @Test
    @DisplayName("Test if when listing all “todos”, you get id #198 and #199 in the JSON results.")
    public void testToDo198And199() {
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/todos")
                .then().assertThat()
                .body("id", hasItems(198, 199));
    }

    @Test
    @DisplayName("Test if when listing all “todos”, you the results in less than 2secs.")
    public void testToDoListTime() {
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/todos")
                .then().assertThat()
                .time(lessThan(2000L));
    }
}
