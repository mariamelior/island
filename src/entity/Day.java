package entity;

import livers.*;

import java.util.*;

/**
 * Created by sbt-fedorova-mav on 06.10.2017.
 */
class Day {
    private Cell[][] grid;
    private List<Rabbit> rabbits;
    private List<Wolf> wolfs;
    private List<Carrot> carrots;
    private Pasha pasha;
    private int size;
    private Cell[][] gridGuess;

    Day(Cell[][] grid, HashMap livers) {
        this.grid = grid;
        this.rabbits = (List<Rabbit>) livers.get("rabbits");
        this.wolfs = (List<Wolf>) livers.get("wolfs");
        this.carrots = (List<Carrot>) livers.get("carrots");
        this.pasha = (Pasha) livers.get("pasha");
        this.size = grid[0].length;
        gridGuess = new Cell[size][size];
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                gridGuess[i][j] = new Cell();
            }
        }
    }

    private void stepAnimals(List<? extends Animal> animals) {
        List<Animal> animalsIter = new ArrayList<>(animals);
        Collections.copy(animalsIter, animals);
        for (Animal animal: animalsIter) {
            if (animal.daysOfHungry == animal.daysOfHungryLive) {
                animal.dead();
                animals.remove(animal);
                continue;
            }

            int[] step = animal.go(size); //TODO Animals goes
            gridGuess[step[0]][step[1]].add(animal);
        }
    }

    private void stepPasha(Pasha pasha) {
        if (pasha.daysOfHungry == pasha.daysOfHungryLive) {
            pasha.dead();
        }

        int[] step = pasha.go(size);
        gridGuess[step[0]][step[1]].add(pasha);
    }


    private void step(List<Carrot> carrots) {
        for (Carrot carrot: carrots) {
            int[] step = carrot.now;
            gridGuess[step[0]][step[1]].add(carrot);
        }
    }

    private static <Type extends Item> List<Type> getWho(List<Item> items, Class<Type> t) {
        List<Type> result= new ArrayList<>();
        for (Item item: items) {
            if (t.isAssignableFrom(item.getClass())) {
                result.add(((Type) item));
            }
        }
        return result;
    }


    private void eat(List<? extends Animal> predators, List<? extends Item> meals) {
        int iterator = Math.min(meals.size(), predators.size());
        List<Item> mealsInter = new ArrayList<>();
        for (int i=0; i<iterator; i++) {
            Item meal = meals.get(i);
            meal.dead();
            mealsInter.add(meal);
            predators.get(i).eat();
        }

        if (meals.get(0) instanceof Rabbit) {
            for (Item meal: mealsInter) {
                rabbits.remove(meal);
            }
        } else {
            for (Item meal: mealsInter) {
                carrots.remove(meal);
            }
        }
    }


    private List<Item> processItem(List<Item> items) {
        List<Rabbit> rabbits = getWho(items, Rabbit.class);
        List<Wolf> wolfs = getWho(items, Wolf.class);
        List<Carrot> carrots = getWho(items, Carrot.class);
        List<Pasha> pashas = getWho(items, Pasha.class);
        boolean areRabbits = rabbits.size() != 0;
        if (pashas.size() == 0) {
            if (wolfs.size() != 0) {
                if (areRabbits) {
                    eat(wolfs, rabbits);
                } else {
                    for (Wolf wolf: wolfs) {
                        wolf.setDaysOfHungry();
                    }
                }
            } else if (areRabbits) {
                if (carrots.size() != 0) {
                    eat(rabbits, carrots);
                } else {
                    for (Rabbit rabbit: rabbits) {
                        rabbit.setDaysOfHungry();
                    }
                }
            }
        } else {
            if (wolfs.size() != 0) {
                if (pashas.get(0).isShoot()) {
                    System.out.println("Pasha is shooting to wolf");
                    wolfs.get(0).dead();
                    wolfs.remove(wolfs.get(0));
                } else  {
                    System.out.println("Pasha isn't shooting to wolf");
                }
            } else  if (rabbits.size() != 0) {
                if (pashas.get(0).isShoot()) {
                    System.out.println("Pasha is shooting to rabbit");
                    rabbits.get(0).dead();
                    rabbits.remove(rabbits.get(0));
                } else  {
                    System.out.println("Pasha isn't shooting to rabbit");
                }
            }
        }

        List<Item> itemsNew = new ArrayList<>();
        itemsNew.addAll(rabbits);
        itemsNew.addAll(wolfs);
        itemsNew.addAll(carrots);
        return itemsNew;
    }

    Cell[][] last() {

        stepAnimals(rabbits);
        stepAnimals(wolfs);
        step(carrots);
        stepPasha(pasha);


        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                if (gridGuess[i][j].getCount() > 0) {
                    List<Item> items = gridGuess[i][j].items;
                    if ((items.size() == 1) &&  !(items.get(0) instanceof Carrot))  {
                        Animal item = (Animal)items.get(0);
                        item.setDaysOfHungry();
                    } else if (items.size() > 1) {
                        items = processItem(items);
                    }
                    gridGuess[i][j].items = items;
                }
            }
        }
        if (rabbits.size() == 0) {
            System.out.println("All rabbits are dead!");
        }
        if (wolfs.size() == 0) {
            System.out.println("All wolfs are dead!");
        }

        return gridGuess;
    }
}
