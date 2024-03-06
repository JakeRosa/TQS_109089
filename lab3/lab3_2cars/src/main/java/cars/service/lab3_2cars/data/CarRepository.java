package cars.service.lab3_2cars.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    public Car findById(long id);

    public List<Car> findAll();
}
