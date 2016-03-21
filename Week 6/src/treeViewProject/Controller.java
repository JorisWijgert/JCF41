package treeViewProject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TreeView<Division> trvDivisions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Player> players = new ArrayList();
        players.add(new Player("Qunfong Wang"));
        players.add(new Player("Joris van de Wijgert"));
        players.add(new Player("Henk"));
        players.add(new Player("Kees"));

        List<Team> teams = new ArrayList();
        teams.add(new Team("Feyenoord", players));
        teams.add(new Team("Ajax", new ArrayList()));

        List<Division> divisions = new ArrayList();
        divisions.add(new Division("Eredivisie", teams));
        divisions.add(new Division("Eerste divisie", new ArrayList()));
        TreeItem<Division> divisionTree = new TreeItem("Divisies");
        for (Division d : divisions) {
            TreeItem<Division> dvTree = new TreeItem();
            for (Team t : d.getTeams()){
                dvTree.getChildren().add(new TreeItem<>(t));
            }
            divisionTree.getChildren().add(dvTree);
        }
        trvDivisions.setRoot(divisionTree);
    }
}
