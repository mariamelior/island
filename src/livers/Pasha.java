package livers;


import java.util.Random;

/**
 * Created by sbt-fedorova-mav on 27.11.2017.
 */
public class Pasha extends Animal {
    static int accuracy = 6;
    public void dead() {
        System.out.println("Pasha is dead");
    }

    public Pasha() {
        super(2,3);
    }

    public int[] go(int size) {
        Random r = new Random();
        int i=0;
        int j=1;
        if (r.nextInt(10) > 5) {
            i=1;
        }
        if (r.nextInt(10) > 5) {
            j=-1;
        }

        int[] newNow = new int[] {now[0],now[1]};
        newNow[i] = newNow[i]+j;
        if (newNow[i] == 5 || newNow[i]<0) {
            newNow[i]=now[i];
        }
        System.out.println("Pasha is going to " + newNow[0] + ", " + newNow[1] + " from " + now[0] + ", " + now[1]);
        return newNow;
    }

    public boolean isShoot() {
        Random r = new Random();
        return (r.nextInt(10) <= accuracy);
    }
}
