package se.annarybkina.montyhall;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameData {

    private String iterations;
    private boolean alwaysSwitch;

   @Override
   public String toString() {
       return "GameData{" +
               "iterations=" + iterations +
               "alwaysSwitch=" + alwaysSwitch +
               '}';
   }

}
