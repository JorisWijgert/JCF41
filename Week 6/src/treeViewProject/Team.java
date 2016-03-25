package treeViewProject;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvdwi on 21-3-2016.
 */
public class Team extends CommonObject<Player> {

    public Team(String name, ObservableList<Player> players) {
        super(name, players);
    }

    @Override
    public List<Player> getChildren(){
        return super.getChildren();
    }

    @Override
    public void addObject(Player object) {
        super.getChildren().add(object);
    }

    @Override
    public void editObject(Player object) {

    }
}
