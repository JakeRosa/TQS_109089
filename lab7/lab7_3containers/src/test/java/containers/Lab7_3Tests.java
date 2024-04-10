package containers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import containers.entities.Student;
import containers.repositories.StudentRepository;

@Testcontainers
@SpringBootTest
public class Lab7_3Tests {
    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private StudentRepository studentRepository;

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Test
    void testCreate() {
        Student student = new Student();
        student.setName("John Smith Carabina");
        student.setAge(20);
        student.setEmail("john@gmail.com");
        student.setAddress("123 Main St");
        student.setPhone("123456789");
        studentRepository.save(student);

        Student found = studentRepository.findById(student.getId()).orElse(null);

        assertThat(found).isNotNull();
    }

    @Test
    void testUpdate() {
        Student student = new Student();
        student.setName("John Smith");
        student.setAge(18);
        student.setEmail("john@gmail.com");
        student.setAddress("123 Main St");
        student.setPhone("123456789");
        studentRepository.save(student);

        student.setName("John Smith Carabina");
        studentRepository.save(student);

        assertThat(studentRepository.findById(student.getId()).get().getName()).isEqualTo("John Smith Carabina");
    }

    @Test
    void testDelete() {
        Student student = new Student();
        student.setName("John Smith");
        student.setAge(18);
        student.setEmail("john@gmail.com");
        student.setAddress("123 Main St");
        student.setPhone("123456789");
        studentRepository.save(student);

        studentRepository.delete(student);

        assertThat(studentRepository.findById(student.getId())).isEmpty();
    }

    @Test
    void testFindAll() {
        Student student = new Student();
        student.setName("John Smith");
        student.setAge(18);
        student.setEmail("john@gmail.com");
        student.setAddress("123 Main St");
        student.setPhone("123456789");
        studentRepository.save(student);

        assertThat(studentRepository.findAll()).isNotEmpty();
    }

    @Test
    void testFindById() {
        Student student = new Student();
        student.setName("John Smith");
        student.setAge(18);
        student.setEmail("john@gmail.com");
        student.setAddress("123 Main St");
        student.setPhone("123456789");
        studentRepository.save(student);

        Student found = studentRepository.findById(student.getId()).orElse(null);

        assertThat(found.getId()).isEqualTo(student.getId());
        assertThat(found.getName()).isEqualTo(student.getName());
    }

    @Test
    void testFindByName() {
        Student student = new Student();
        student.setName("John Smith");
        student.setAge(18);
        student.setEmail("john@gmail.com");
        student.setAddress("123 Main St");
        student.setPhone("123456789");
        studentRepository.save(student);

        Student found = studentRepository.findByName(student.getName()).orElse(null);

        assertThat(found.getName()).isEqualTo(student.getName());
        assertThat(found.getId()).isEqualTo(student.getId());
    }
}
