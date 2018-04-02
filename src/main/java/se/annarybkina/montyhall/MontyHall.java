package se.annarybkina.montyhall;

import java.util.List;

import lombok.Data;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.Collections;

@Data
public class MontyHall {

    private final List<Door> doors;
    private final int NUMBER_OF_DOORS = 3;
    private int selectedDoor;
    private int winningDoor; 
    private int wins;


    public MontyHall() {
        Game game = new Game();
        this.doors = game.getDoorList();
        this.winningDoor = game.getWinningDoor();
        this.wins = 0;
        this.selectedDoor = -1;
    }

    private int selectSecretDoor() {
        Door pickedDoor = this.doors.stream()
        .collect(Collectors.collectingAndThen(Collectors.toList(), collected -> {
            Collections.shuffle(collected);
            return collected.stream();
        }))
        .filter(Objects::nonNull)
        .filter(door -> door.getId() != this.selectedDoor)
        .findAny().get();
        return pickedDoor.getId();
    }

    private int selectGoatDoor() {
        return this.doors.stream()
        .filter(d -> d.getId() != this.winningDoor && d.getId() != this.selectedDoor)
        .findAny().get().getId();
    }

    public void play(boolean switchDoor) {
        int playersDoor = this.selectedDoor = selectSecretDoor();

        int hostsDoor = selectGoatDoor();
        
        playersDoor = switchDoor ? NUMBER_OF_DOORS - playersDoor - hostsDoor: playersDoor;
    
        if (playersDoor == winningDoor) {
            wins++;
        }

    }

}