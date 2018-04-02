package se.annarybkina.montyhall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
public class MontyHallTest {
 
	@Test
	public void testSelectDoor() throws Exception {
        MontyHall testMontyHall = new MontyHall();
        boolean alwaysSwitch = true;
        int iterations = 100;
        IntStream.range(0, iterations)
        .forEach(n -> {
            testMontyHall.play(alwaysSwitch);
        });
        assertTrue(testMontyHall.getWins() > 0);
	}
}
