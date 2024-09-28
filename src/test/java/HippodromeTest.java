import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class HippodromeTest {
    @Test
    void nullInConstructorTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
    }
    @Test
    void nullInConstructorMessageTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
        assertEquals("Horses cannot be null.",exception.getMessage());
    }
    @Test
    void emptyListConstructorTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            List<Horse> horses = new ArrayList<>();
            new Hippodrome(horses);
        });
    }
    @Test
    void emptyListConstructorMessageTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            List<Horse> horses = new ArrayList<>();
            new Hippodrome(horses);
        });
        assertEquals("Horses cannot be empty.",exception.getMessage());
    }

    @Test
    void getHorsesTest(){

       List<Horse> horses = createListHorses(30);

       assertEquals(horses, new Hippodrome(horses).getHorses() );

    }

    private static List<Horse> createListHorses(int count) {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < count; i++) {
                    horses.add(new Horse("name" + i, 1+i, 2+i));
        }
        return horses;
    }


    @Test

    void moveEachHorseTest(){
        List<Horse> horses = createListHorses(50);

        assertEquals(horses, new Hippodrome(horses).getHorses() );
    }

    @Test
    void getWinnerTest() {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Horse1", 10, 100));
        horses.add(new Horse("Horse2", 10, 200));
        horses.add(new Horse("Horse3", 10, 150));

        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = hippodrome.getWinner();

        assertEquals("Horse2", winner.getName());
    }
}
