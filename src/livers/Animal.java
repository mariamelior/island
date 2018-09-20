package livers;

import entity.Item;

/**
 * Created by sbt-fedorova-mav on 06.10.2017.
 */
public abstract class Animal implements Item {

    public final int daysOfHungryLive;
    public final int stepsSee;
    public int daysOfHungry;
    public int[] now;

    Animal(int daysOfHungryLive, int stepsSee) {
        this.daysOfHungryLive = daysOfHungryLive;
        this.stepsSee = stepsSee;
        this.daysOfHungry = 0;
    }

    public final void eat() {
        System.out.println("Hrum-hrum");
        daysOfHungry = 0;
    }

    public void setDaysOfHungry() {
        daysOfHungry++;
    }

    public abstract int[] go(int size);

}
