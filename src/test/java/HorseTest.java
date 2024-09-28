import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    void stringParameterIsNullTest() {
       assertThrows(IllegalArgumentException.class, () ->
            new Horse(null,1,1)
        );

    }

    @Test
    void stringParameterIsNullTextTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null,1,1);
        });
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("invalidHorseNames")
    void emptyAndTabulationTest(String name) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name, 1,5);
        });
    }
    private static Stream<String> invalidHorseNames() {
        return Stream.of("", " ", "\t", "\n");
    }

    @ParameterizedTest
    @MethodSource("invalidHorseNames")
    void emptyAndTabulationTextTest(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name, 1,5);
        });
        assertEquals("Name cannot be blank.",exception.getMessage());
    }
    @Test
    void minusSpeedTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse("name", -1,5);
        });
    }
    @Test
    void minusSpeedTextTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("name", -1,5);
        });
       assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void minusDistanceTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse("name", 1,-5);
        });
    }
    @Test
    void minusDistanceTextTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("name", 1,-5);
        });
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }
    @Test
    void getNameTest() {
        String testName = "TestName";
        Horse horse = new Horse(testName, 10, 20);
        assertEquals(testName, horse.getName());
    }
    @Test
    void getSpeedTest() {
        int testSpeed = 5;
        Horse horse = new Horse("testName", testSpeed, 20);
        assertEquals(testSpeed, horse.getSpeed());
    }
    @Test
    void getDistanceTest() {
        int tesDistance = 10;
        Horse horse = new Horse("testName", 10, tesDistance);
        assertEquals(tesDistance, horse.getDistance());
    }

    @Test
    void testMoveCallsGetRandomDouble(){
        {
            try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
                Horse horse = new Horse("Test", 10, 1);
                horse.move();

                mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            }
        }
    }
    @ParameterizedTest
    @MethodSource("provideRandomValues")
    void testMove(double randomValue, double expectedDistance) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);

            Horse horse = new Horse("Test", 10, 0);
            horse.move();

            assertEquals(expectedDistance, horse.getDistance());
        }
    }

    private static Stream<Arguments> provideRandomValues() {
        return Stream.of(
                Arguments.of(0.2, 2.0),  // 0 + 10 * 0.2
                Arguments.of(0.5, 5.0),  // 0 + 10 * 0.5
                Arguments.of(0.9, 9.0)   // 0 + 10 * 0.9
        );
    }


}
