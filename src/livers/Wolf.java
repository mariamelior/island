package livers;

import java.util.Random;

/**
 * Created by sbt-fedorova-mav on 06.10.2017.
 */
public class Wolf extends Animal{
    public Wolf() {
        super(3,3);
    }

    @Override
    public void dead() {
        System.out.println("Wolf is dead!");
    }

    @Override
    public final int[] go(int size) {
       // for (int i=now[0] - stepsSee; i < now[0] + stepsSee; i++) {
       //     for (int j=now[1] - stepsSee; j < now[1] + stepsSee; j++) {

       //     }
       // }
        double r = Math.random();

        int i=0;
        int j=1;
        if (r>0.5) {
            i=1;
        }
        r = Math.random();
        if (r>0.5) {
            j=-1;
        }

        int[] newNow = new int[] {now[0],now[1]};
        newNow[i] = newNow[i]+j;
        if (newNow[i] == 5 || newNow[i]<0) {
            newNow[i] = now[i];
        }
        System.out.println("Wolf go to " + newNow[0] + ", " + newNow[1] + " from " + now[0] + ", " + now[1]);
        return newNow;
    }


}
