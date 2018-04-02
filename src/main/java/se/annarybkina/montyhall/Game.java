package se.annarybkina.montyhall;

import lombok.Data;

import java.util.List;
import java.util.stream.IntStream;

import java.util.ArrayList;

import java.util.Random;
import java.util.stream.Collectors;

@Data
public class Game {

    private final List<Door> doorList;
    private final int NUMBER_OF_DOORS = 3;
    private final int winningDoor; 
 
    public Game() {
        Random random = new Random();
        winningDoor = random.nextInt(NUMBER_OF_DOORS);
        
        doorList = IntStream.range(0, NUMBER_OF_DOORS)
            .mapToObj(id -> new Door(id))
            .collect(Collectors.toList());
    }

    public List<Door> getDoors() {
        return new ArrayList<Door>(this.doorList);
    }

    public int getWinningDoor() {
        return this.winningDoor;
    }

}