package cars.service.lab3_2cars.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cars.service.lab3_2cars.data.Car;
import cars.service.lab3_2cars.data.CarRepository;

@Service
public class CarManagerServiceImpl implements CarManagerService {
    private CarRepository carRepository;

    public CarManagerServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> getCarDetails(long id) {
        Car car = carRepository.findById(id);
        return Optional.ofNullable(car);
    }
}
