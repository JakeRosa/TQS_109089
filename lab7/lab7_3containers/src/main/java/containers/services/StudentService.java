package containers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import containers.entities.Student;
import containers.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public Student update(Long id, Student Student) {
        Student studentToUpdate = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student with id " + id + " not found"));
        studentToUpdate.setName(Student.getName());
        studentToUpdate.setAge(Student.getAge());
        studentToUpdate.setEmail(Student.getEmail());
        studentToUpdate.setAddress(Student.getAddress());
        studentToUpdate.setPhone(Student.getPhone());

        return studentRepository.save(studentToUpdate);
    }
}
