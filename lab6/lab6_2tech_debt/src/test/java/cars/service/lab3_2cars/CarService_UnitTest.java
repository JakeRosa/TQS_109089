package cars.service.lab3_2cars;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import cars.service.lab3_2cars.data.Car;
import cars.service.lab3_2cars.data.CarRepository;
import cars.service.lab3_2cars.service.CarManagerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CarService_UnitTest {

    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerServiceImpl carManagerService;

    @BeforeEach
    public void setUp() {

        // these expectations provide an alternative to the use of the repository
        Car myCar = new Car("Fiat", "Punto");
        Car yourCar = new Car("Renault", "Clio");
        Car hisCar = new Car("Ford", "Fiesta");

        List<Car> allCars = Arrays.asList(myCar, yourCar, hisCar);

        Mockito.when(carRepository.findById(myCar.getId())).thenReturn(myCar);
        Mockito.when(carRepository.findById(yourCar.getId())).thenReturn(yourCar);
        Mockito.when(carRepository.findById(hisCar.getId())).thenReturn(hisCar);
        Mockito.when(carRepository.findAll()).thenReturn(allCars);
    }

    @Test
    public void saveCarTest() {
        Car myCar = new Car("Fiat", "Punto");

        when(carRepository.save(myCar)).thenReturn(myCar);

        assertThat(carManagerService.save(myCar), is(myCar));
    }

    @Test
    public void getAllCarsTest() {
        List<Car> allCars = carManagerService.getAllCars();
        assertThat(allCars, hasSize(3));

        verify(carRepository, times(1)).findAll();
    }

    @Test
    public void getCarDetailsTest() {
        Car myCar = new Car("Honda", "Civic");

        when(carRepository.findById(myCar.getId())).thenReturn(myCar);

        assertThat(carManagerService.getCarDetails(myCar.getId()).get(), is(myCar));

        verify(carRepository, times(1)).findById(myCar.getId());
    }

    @Test
    public void getCarDetailsNotFoundTest() {
        when(carRepository.findById(0)).thenReturn(null);

        assertThat(carManagerService.getCarDetails(0).isPresent(), is(false));

        verify(carRepository, times(1)).findById(0);
    }
}