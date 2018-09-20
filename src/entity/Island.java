package entity;

import livers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
/**
 * Created by sbt-fedorova-mav on 06.10.2017.
 */
public class Island {
    final int size;
    final int count;
    private ArrayList<Rabbit> rabbits = new ArrayList<>();
    private ArrayList<Wolf> wolfs = new ArrayList<>();
    private ArrayList<Carrot> carrots = new ArrayList<>();
    private Cell[][] grid;
    HashMap livers = new HashMap();


    private Island() {
        size =  5;//(byte) Integer.getInteger(args[0]);
        count = 5;//(byte) Integer.getInteger(args[1]);
        grid = new Cell[size][size];
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                grid[i][j] = new Cell();
            }
        }
        Random r = new Random();

        for (int i=0; i < count; i++) {
            Rabbit rabbit = new Rabbit();
            rabbit.now = new int[] {r.nextInt(size),r.nextInt(size)};
            rabbits.add(rabbit);
            Wolf wolf = new Wolf();
            wolf.now = new int[] {r.nextInt(size),r.nextInt(size)};
            wolfs.add(wolf);
            Carrot carrot = new Carrot();
            carrot.now = new int[] {r.nextInt(size),r.nextInt(size)};
            carrots.add(carrot);
            grid[rabbit.now[0]][rabbit.now[1]].add(rabbit);
            grid[wolf.now[0]][wolf.now[1]].add(wolf);
            grid[carrot.now[0]][carrot.now[1]].add(carrot);
        }

        Pasha pasha = new Pasha();
        pasha.now = new int[] {r.nextInt(size),r.nextInt(size)};
        grid[pasha.now[0]][pasha.now[1]].add(pasha);

        livers.put("rabbits", rabbits);
        livers.put("wolfs", wolfs);
        livers.put("carrots", carrots);
        livers.put("pasha", pasha);

    }

  //  private <Type extends Item> void createWho(Class<Type>  t) {
   //     Type item = new Class<Type>();
   //     item.now = new int[] {r.nextInt(size),r.nextInt(size)};
  //      rabbits.add(item);
   //     grid[item.now[0]][item.now[1]].add(item);
   // }
//
    public static void main(String[] args) {
        Island island = new Island();
        island.lastDay();
    }

    private void lastDay() {
        int days = 0;

        ArrayList<HashMap> statictic = new ArrayList<>();
        while (rabbits.size() != 0 && wolfs.size() != 0) {
            Day day = new Day(grid, livers);
            int rL = rabbits.size();
            int wL = wolfs.size();
            int cL = carrots.size();
            this.grid = day.last();
            System.out.println("day last");
            HashMap dayS = new HashMap();

            dayS.put("rabbitsLive", rL - rabbits.size());
            dayS.put("wolfsLive", wL - wolfs.size());
            dayS.put("carrotsLive", cL - carrots.size());
            statictic.add( dayS );
            days++;
        }
        System.out.println("---------------------Statistic--------------------------");
        System.out.println("Last days " + days);
        System.out.println("Live rabbits " + rabbits.size());
        System.out.println("Live wolfs " + wolfs.size());
        System.out.println("Carrots " + carrots.size());
        System.out.println("---------------------Statistic--------------------------");
        for (int i=0; i<days; i++){
            System.out.println("-----------Day №" + (i + 1) + "---------------------");
            System.out.println("Dead rabbits " + statictic.get(i).get("rabbitsLive"));
            System.out.println("Dead wolfs " + statictic.get(i).get("wolfsLive"));
            System.out.println("Dead carrots " + statictic.get(i).get("carrotsLive"));
        }
        System.out.println("---------------------Statistic--------------------------");
    }

}
