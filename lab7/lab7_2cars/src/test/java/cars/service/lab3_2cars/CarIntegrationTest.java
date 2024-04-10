package cars.service.lab3_2cars;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import cars.service.lab3_2cars.boundary.CarRestController;
import cars.service.lab3_2cars.data.Car;
import cars.service.lab3_2cars.service.CarManagerService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest(CarRestController.class)
public class CarIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarManagerService carService;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    @DisplayName("GET /api/cars/1")
    void testGetCarDetails() {
        Car car = new Car("bmw", "car1");
        car.setId(1L);

        Mockito.when(carService.getCarDetails(1L)).thenReturn(Optional.of(car));

        given()

                .when()
                .get("/api/cars/1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Car.class)
                .equals(car);

        verify(carService, Mockito.times(1)).getCarDetails(1L);
    }

    @Test
    @DisplayName("GET /api/cars")
    void testGetAllCars() {
        Car car1 = new Car("bmw", "car1");
        Car car2 = new Car("audi", "car2");

        when(carService.getAllCars()).thenReturn(Arrays.asList(car1, car2));

        given()

                .when()
                .get("/api/cars")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList(".", Car.class)
                .containsAll(Arrays.asList(car1, car2));

        verify(carService, Mockito.times(1)).getAllCars();
    }

    @Test
    @DisplayName("POST /api/cars")
    void testAddCar() {
        Car car = new Car("bmw", "car1");

        when(carService.save(car)).thenReturn(car);

        given()
                .contentType("application/json")
                .body(car)
                .when()
                .post("/api/cars")
                .then()
                .statusCode(201)
                .extract()
                .body()
                .as(Car.class)
                .equals(car);

        verify(carService, times(1)).save(car);
    }
}
