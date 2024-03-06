package cars.service.lab3_2cars;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import cars.service.lab3_2cars.data.Car;
import cars.service.lab3_2cars.data.CarRepository;

@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    void findByCarIdTest() {
        Car car = new Car("Fiat", "Punto");
        entityManager.persistAndFlush(car);

        Car found = carRepository.findById(car.getId());

        assertThat(found.getMaker()).isEqualTo(car.getMaker());
    }

    @Test
    void findByCarId_whenInvalidTest() {
        Car invalid = carRepository.findById(-1);

        assertThat(invalid).isNull();
    }

    @Test
    void findAllTest() {
        Car myCar = new Car("Fiat", "Punto");
        Car yourCar = new Car("Renault", "Clio");

        entityManager.persist(myCar);
        entityManager.persist(yourCar);
        entityManager.flush();

        List<Car> allCars = carRepository.findAll();

        assertThat(allCars).hasSize(2).contains(myCar, yourCar);

    }
}
