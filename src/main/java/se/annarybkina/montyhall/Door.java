package se.annarybkina.montyhall;

import lombok.Data;

@Data
public class Door {

    private final int id;

    public Door(int id) {
        this.id = id;
    };

   @Override
   public String toString() {
       return "Door{" +
               "id=" + id +
               '}';
   }

}
