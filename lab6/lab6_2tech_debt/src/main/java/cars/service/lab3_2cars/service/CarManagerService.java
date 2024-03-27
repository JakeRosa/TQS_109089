package cars.service.lab3_2cars.service;

import java.util.List;
import java.util.Optional;

import cars.service.lab3_2cars.data.Car;

public interface CarManagerService {

    Car save(Car any);

    List<Car> getAllCars();

    Optional<Car> getCarDetails(long id);

}
