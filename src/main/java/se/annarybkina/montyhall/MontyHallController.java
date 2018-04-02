package se.annarybkina.montyhall;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;;

@RestController
public class MontyHallController {

    @RequestMapping(
        value = "/data", 
        method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public int processData(@RequestBody GameData payload) 
        throws Exception {
      return runSimulations(Integer.parseInt(payload.getIterations()), payload.isAlwaysSwitch());
    }
    
    private static int runSimulations(int iterations, boolean switchDoor)
    {
        MontyHall montyHall = new MontyHall();
        IntStream.range(0, iterations)
        .forEach(n -> {
            montyHall.play(switchDoor);
        });
        return montyHall.getWins();
    }
    
}