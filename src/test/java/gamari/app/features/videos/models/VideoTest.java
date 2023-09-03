package gamari.app.features.videos.models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VideoTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // @Test
    // public void testTitleNotNull() {
    // Video video = new Video();
    // video.setTitle(null);

    // Set<ConstraintViolation<Video>> violations = validator.validate(video);
    // assertFalse(violations.isEmpty());

    // for (ConstraintViolation<Video> violation : violations) {
    // assertTrue(violation.getPropertyPath().toString().equals("title"));
    // }
    // }

    // @Test
    // public void testTitleNotNullPositiveCase() {
    // Video video = new Video();video.setTitle("Sample Video Title");

    // Set<ConstraintViolation<Video>> violations = validator.validate(video);

    // assertTrue(violations.isEmpty());
    // }
}
