package entity;

import livers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
/**
 * Created by sbt-fedorova-mav on 06.10.2017.
 */
public class Island {
    private final int size;
    private ArrayList<Rabbit> rabbits = new ArrayList<>();
    private ArrayList<Wolf> wolfs = new ArrayList<>();
    private ArrayList<Carrot> carrots = new ArrayList<>();
    private ArrayList<Pasha> pasha = new ArrayList<>();
    private Cell[][] grid;
    private HashMap livers = new HashMap();


    private Island() {
        size =  5;//(byte) Integer.getInteger(args[0]);
        int count = 5;//(byte) Integer.getInteger(args[1]);
        grid = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Cell();
            }
        }
        Random r = new Random();

        for (int i=0; i < count; i++) { // TODO: universal
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

        Pasha p = new Pasha();
        p.now = new int[] {r.nextInt(size),r.nextInt(size)};
        grid[p.now[0]][p.now[1]].add(p);
        pasha.add(p);

        livers.put("rabbits", rabbits);
        livers.put("wolfs", wolfs);
        livers.put("carrots", carrots);
        livers.put("pasha", pasha);

    }

  //  private <Type extends Item> void createWho(Class<Type>  t) { //TODO: alive
   //     Type item = new Class<Type>();
   //     item.now = new int[] {r.nextInt(size),r.nextInt(size)};
  //      rabbits.add(item);
   //     grid[item.now[0]][item.now[1]].add(item);
   // }
//
    public static void main(String[] args) {
        Island island = new Island();
        island.startLive();
    }

    private void startLive() {
        int days = 0;

        ArrayList<HashMap> statistic = new ArrayList<>();
        while (rabbits.size() != 0 && wolfs.size() != 0) {
            System.out.println("---------Day №" + (days+1) + "--------");
            Day day = new Day(size, livers);
            int rL = rabbits.size();
            int wL = wolfs.size();
            int cL = carrots.size();
            int pL = pasha.size();
            this.grid = day.begin();
            System.out.println("day passed");
            HashMap dayS = new HashMap();

            dayS.put("rabbitsLive", rL - rabbits.size());
            dayS.put("wolfsLive", wL - wolfs.size());
            dayS.put("carrotsLive", cL - carrots.size());
            dayS.put("pashaLive", pL - pasha.size() );
            statistic.add( dayS );
            days++;
        }
        System.out.println("---------------------Statistic--------------------------");
        System.out.println("Days passed " + days);
        System.out.println("Live rabbits " + rabbits.size());
        System.out.println("Live wolfs " + wolfs.size());
        System.out.println("Carrots " + carrots.size());
        System.out.println("Live Pasha " + pasha.size());
        System.out.println("---------------------Statistic--------------------------");
        for (int i = 0; i < days; i++){
            System.out.println("-----------Day №" + (i + 1) + "---------------------");
            System.out.println("Dead rabbits " + statistic.get(i).get("rabbitsLive"));
            System.out.println("Dead wolfs " + statistic.get(i).get("wolfsLive"));
            System.out.println("Dead carrots " + statistic.get(i).get("carrotsLive"));
            System.out.println("Dead pasha " + statistic.get(i).get("pashaLive"));
        }
        System.out.println("---------------------Statistic--------------------------");
    }

}
