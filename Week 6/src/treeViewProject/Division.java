package treeViewProject;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvdwi on 21-3-2016.
 */
public class Division extends CommonObject<Team> {

    public Division(String name, ObservableList<Team> teams) {
        super(name, teams);
    }

    @Override
    public List<Team> getChildren(){
        return super.getChildren();
    }

    @Override
    public void addObject(Team toAddTeam) {
        super.getChildren().add(toAddTeam);
    }

    @Override
    public void editObject(Team object) {

    }

}
