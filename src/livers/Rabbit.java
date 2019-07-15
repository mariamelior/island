package livers;

import java.util.Random;

/**
 * Created by sbt-fedorova-mav on 06.10.2017.
 */
public class Rabbit extends Animal{
    public Rabbit() {
        super(4,2);
    }

    @Override
    public void dead() {
        System.out.println("Rabbit is dead!");
    }

    @Override
    public final int[] go(int size) {
        Random r = new Random();
        int i=0;
        int j=1;
        if (r.nextInt(10) > 5) {
            i = 1;
        }
        if (r.nextInt(10) > 5) {
            j =- 1;
        }

        int[] newNow = new int[] {now[0],now[1]};
        newNow[i] = newNow[i] + j;
        if (newNow[i] == size || newNow[i]<0) {
            newNow[i] = now[i];
        }
        System.out.println("Rabbit go to " + newNow[0] + ", " + newNow[1] + " from " + now[0] + ", " + now[1]);
        return newNow;
    }
}
