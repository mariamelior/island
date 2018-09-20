package livers;

import entity.Item;

/**
 * Created by sbt-fedorova-mav on 06.10.2017.
 */
public class Carrot implements Item {
    public void dead() {
        System.out.println("Carrot's eaten!");
    }
    public int[] now;
}
