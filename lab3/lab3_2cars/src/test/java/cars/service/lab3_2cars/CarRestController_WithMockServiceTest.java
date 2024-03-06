package cars.service.lab3_2cars;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import cars.service.lab3_2cars.boundary.CarRestController;
import cars.service.lab3_2cars.data.Car;
import cars.service.lab3_2cars.service.CarManagerServiceImpl;

@WebMvcTest(CarRestController.class)
class CarRestController_WithMockServiceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerServiceImpl service;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void createCarTest() throws Exception {
        Car myCar = new Car("Fiat", "Punto");

        when(service.save(Mockito.any())).thenReturn(myCar);

        mvc.perform(
                post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(myCar)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is((int) myCar.getId())));

        verify(service, times(1)).save(Mockito.any());
    }

    @Test
    void getAllCarsTest() throws Exception {
        Car myCar = new Car("Fiat", "Punto");
        Car yourCar = new Car("Renault", "Clio");

        when(service.getAllCars()).thenReturn(List.of(myCar, yourCar));

        mvc.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].model", is("Punto")))
                .andExpect(jsonPath("$[1].model", is("Clio")));

        verify(service, times(1)).getAllCars();
    }

    @Test
    void getCarByIdTest() throws Exception {
        Car myCar = new Car("Fiat", "Punto");

        when(service.getCarDetails(myCar.getId())).thenReturn(Optional.of(myCar));

        mvc.perform(get("/api/cars/" + myCar.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model", is("Punto")));

        verify(service, times(1)).getCarDetails(myCar.getId());
    }
}