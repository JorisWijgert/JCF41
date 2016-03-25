package treeViewProject;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TreeView<Object> trvDivisions;

    @FXML
    TextField tfTeamAdd;
    @FXML
    TextField tfPlayerAdd;
    @FXML
    ComboBox cbDivisions;
    @FXML
    ComboBox cbTeams;

    public ObservableList<Team> teamObservableList;
    public ObservableList<Division> divisionObservableList;
    public ObservableList<Player> playerObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<Player> players = new ArrayList();
        players.add(new Player("Qunfong Wang"));
        players.add(new Player("Joris van de Wijgert"));
        players.add(new Player("Henk"));
        players.add(new Player("Kees"));
        playerObservableList = FXCollections.observableList(players);

        List<Team> teams = new ArrayList();
        teams.add(new Team("Feyenoord", playerObservableList));
        teams.add(new Team("Ajax", FXCollections.observableArrayList(new ArrayList<Player>())));
        teamObservableList = FXCollections.observableList(teams);

        List<Division> divisions = new ArrayList();
        divisions.add(new Division("Eredivisie", teamObservableList));
        divisions.add(new Division("Eerste divisie", FXCollections.observableArrayList(new ArrayList<Team>())));
        divisionObservableList = FXCollections.observableList(divisions);

        fillComboBoxes();
        /*
        teamObservableList.addListener(new ListChangeListener<Team>() {
            @Override
            public void onChanged(Change<? extends Team> c) {
                renewTreeView(divisionObservableList);
            }
        });

        playerObservableList.addListener(new ListChangeListener<Player>(){
           @Override
            public void onChanged(Change<? extends Player>c){
               renewTreeView(divisionObservableList);
           }
        });*/
        renewTreeView(divisionObservableList);
    }

    private void renewTreeView(List<Division> divisions) {
        TreeItem<Object> divisionTree = new TreeItem("Divisies");
        for (Division division : divisions) {
            TreeItem<Object> divisionTreeItem = new TreeItem(division);
            for (Team team : division.getChildren()){
                TreeItem<Object> teamTreeItem = new TreeItem(team);
                for (Player player : team.getChildren()){
                    teamTreeItem.getChildren().add(new TreeItem(player));
                }
                divisionTreeItem.getChildren().add(teamTreeItem);
            }
            divisionTree.getChildren().add(divisionTreeItem);
        }
        trvDivisions.setRoot(divisionTree);
    }

    private void fillComboBoxes() {
        cbDivisions.setItems(divisionObservableList);
        cbTeams.setItems(teamObservableList);

    }

    public void addTeam(){
        String teamName = tfTeamAdd.getText();
        Division toaddDivision = (Division)cbDivisions.getSelectionModel().getSelectedItem();
        toaddDivision.addObject(new Team(teamName,FXCollections.observableArrayList(new ArrayList<Player>())));
        renewTreeView(divisionObservableList);
        /*
        for(Division division : divisionObservableList){
            if(division.getName().equals(toaddDivision.getName())){

            }
        }*/
    }

    public void addPlayer(){
        String playerName = tfPlayerAdd.getText();
        Team toAddTeam = (Team) cbTeams.getSelectionModel().getSelectedItem();
        toAddTeam.addObject(new Player(playerName));
        renewTreeView(divisionObservableList);
    }
}
