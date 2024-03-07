package hello.world.lab4_1WebDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.lang.invoke.MethodHandles;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
class HelloWorldFirefoxJupiterTest {

    static final Logger log = getLogger(MethodHandles.lookup().lookupClass());

    // @BeforeAll
    // static void setupClass() {
    // WebDriverManager.firefoxdriver().setup();
    // }

    // @BeforeEach
    // void setup() {
    // driver = new FirefoxDriver();
    // }

    @Test
    void test(FirefoxDriver driver) {
        // Exercise
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(sutUrl);
        String title = driver.getTitle();
        log.debug("The title of {} is {}", sutUrl, title);

        // Verify
        assertThat(title).isEqualTo("Hands-On Selenium WebDriver with Java");
    }

    // @AfterEach
    // void teardown() {
    // driver.quit();
    // }

}