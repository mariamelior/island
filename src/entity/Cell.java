package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbt-fedorova-mav on 06.10.2017.
 */
class Cell {
    List<Item> items = new ArrayList<>();

    int getCount() {
        return items.size();
    }

    void add(final Item i){
        items.add(i);
    }
}
