## Lab 3_1 Answers

### a) Identify a couple of examples that use AssertJ expressive methods chaining.

### A_EmployeeRepositoryTest:

```java
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
```

### B_EmployeeService_UnitTest:

```java
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName());

assertThat(found.getName()).isEqualTo(name);
```
### D_EmployeeRestControllerIT:

```java
assertThat(found).extracting(Employee::getName).containsOnly("bob");
```

### E_EmployeeRestControllerTemplateIT:

```java
assertThat(found).extracting(Employee::getName).containsOnly("bob");

assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");
```

### b) Identify an example in which you mock the behavior of the repository (and avoid involving a database).

```java
@Mock( lenient = true)
private EmployeeRepository employeeRepository;

(...)

@BeforeEach
    public void setUp() {

        //these expectations provide an alternative to the use of the repository
        Employee john = new Employee("john", "john@deti.com");
        john.setId(111L);

        Employee bob = new Employee("bob", "bob@deti.com");
        Employee alex = new Employee("alex", "alex@deti.com");

        List<Employee> allEmployees = Arrays.asList(john, bob, alex);

        Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
        Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
        Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
        Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
        Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
        Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
    }
```

### c) What is the difference between standard @Mock and @MockBean?

@Mock is a Mockito annotation used to create and inject mocked instances of a declared type. This annotation is typically used in unit tests where Spring context is not involved. The mock objects created with this annotation are local to the test class and will not be reused across different test classes.

@MockBean is a Spring annotation used in a Spring context for creating and injecting mocked instances. @MockBean replaces any existing bean of the same type in the Spring context with a Mockito mock. This is useful in integration tests where certain beans, like external services or repositories, need to be mocked. The mock objects created with this annotation are part of the Spring context and can be reused across different test classes.

### d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

The application-integrationtest.properties file is a specific configuration file used during integration testing in a Spring Boot application. This file is used when the integrationtest profile is active. The properties in this file will override the default ones in application.properties for this specific profile, allowing to have a different configuration for integration testing, separate from your main application or other testing configurations.

### e) the sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences? 

### C_EmployeeController_WithMockServiceTest:

This strategy uses @WebMvcTest, which loads only the web layer of the application. It does not load the full context. The EmployeeService is mocked, so the tests are not interacting with the database. This is a unit test strategy, focusing on the web layer only.

### D_EmployeeRestControllerIT:

This strategy uses @SpringBootTest, which loads the full application context. It uses @AutoConfigureMockMvc to inject a MockMvc instance for sending requests to the application. It also uses @AutoConfigureTestDatabase to replace the application's DataSource with an embedded database for testing. This is an integration test strategy, testing the interaction of multiple layers (web, service, and data).

### E_EmployeeRestControllerTemplateIT:

This strategy also uses @SpringBootTest, loading the full application context. However, it uses TestRestTemplate for sending requests, which makes actual HTTP requests, as opposed to MockMvc which does not. This is also an integration test strategy, but it tests the application in a more realistic way by making actual HTTP requests to other APIs and external systems.
