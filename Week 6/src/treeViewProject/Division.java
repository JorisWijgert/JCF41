package treeViewProject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvdwi on 21-3-2016.
 */
public class Division {
    private String name;
    private List<Team> teams;

    public Division(String name, List<Team> teams) {
        this.name = name;
        teams = teams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString(){
        return name;
    }
}
