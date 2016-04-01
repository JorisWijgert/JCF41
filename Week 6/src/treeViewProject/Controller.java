package treeViewProject;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;

import java.awt.*;
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
    TextField tfPlayerAge;
    @FXML
    TextField tfPlayerPosition;
    @FXML
    TextField tfPlayerNation;
    @FXML
    ComboBox cbDivisions;
    @FXML
    ComboBox cbTeams;
    @FXML
    TextField tfNaamEdit;
    @FXML
    TextField tfAgeEdit;
    @FXML
    TextField tfPositionEdit;
    @FXML
    TextField tfNationEdit;
    @FXML
    TextField tfTeamEdit;

    @FXML
    TableView<Player> tvPlayers;

    public ObservableList<Team> teamObservableList;
    public ObservableList<Division> divisionObservableList;
    public ObservableList<Player> playerObservableList;
    public Player oldPlayer;
    public Team oldTeam;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<Player> players = new ArrayList();
        players.add(new Player("Qunfong Wang", 20, "Spits", "Nederland"));
        players.add(new Player("Joris van de Wijgert", 19, "Keeper", "Nederland"));
        players.add(new Player("Henk Janssen", 28, "Verdediging", "België"));
        players.add(new Player("Kees Hendriks", 14, "Mid", "Frankrijk"));
        playerObservableList = FXCollections.observableList(players);

        List<Team> teams = new ArrayList();
        teams.add(new Team("Feyenoord", playerObservableList));
        teams.add(new Team("Ajax", FXCollections.observableArrayList(new ArrayList<Player>())));
        teamObservableList = FXCollections.observableList(teams);

        List<Division> divisions = new ArrayList();
        divisions.add(new Division("Eredivisie", teamObservableList));
        divisions.add(new Division("Eerste divisie", FXCollections.observableArrayList(new ArrayList<Team>())));
        divisionObservableList = FXCollections.observableList(divisions);
        trvDivisions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Object>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<Object>> observable, TreeItem<Object> oldValue, TreeItem<Object> newValue) {
                Platform.runLater(() -> {
                    if (newValue != null && newValue.getValue() instanceof Team) {
                        oldTeam = (Team) newValue.getValue();
                        tfTeamEdit.setText(oldTeam.getName());
                        tvPlayers.setItems((ObservableList<Player>) oldTeam.getChildren());
                    } else {
                        tfTeamEdit.clear();
                    }
                });
            }
        });

        tvPlayers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Player>() {
            @Override
            public void changed(ObservableValue<? extends Player> observable, Player oldValue, Player newValue) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if(tvPlayers.getSelectionModel().getSelectedItem() !=null) {
                            oldPlayer = tvPlayers.getSelectionModel().getSelectedItem();
                            tfNaamEdit.setText(oldPlayer.getName());
                            tfAgeEdit.setText(String.valueOf(oldPlayer.getAge()));
                            tfPositionEdit.setText(oldPlayer.getPosition());
                            tfNationEdit.setText(oldPlayer.getNation());
                        }
                    }
                });
            }
        });
        fillComboBoxes();
        renewTreeView(divisionObservableList);
    }

    @FXML
    private void editTableView(){
        for(Player player : playerObservableList){
            if(player.equals(oldPlayer)){
                player.setName(tfNaamEdit.getText());
                player.setAge(Integer.parseInt(tfAgeEdit.getText()));
                player.setPosition(tfPositionEdit.getText());
                player.setNation(tfNationEdit.getText());
            }
        }
        Platform.runLater(() -> renewTreeView(divisionObservableList));
    }

    @FXML
    private void editTeam(){
        for (Team team : teamObservableList){
            if(team.equals(oldTeam)){
                team.setName(tfTeamEdit.getText());
            }
        }
        Platform.runLater(() -> renewTreeView(divisionObservableList));
    }

    private void renewTreeView(List<Division> divisions) {
        tvPlayers.setItems(null);
        TreeItem<Object> divisionTree = new TreeItem("Divisies");
        for (Division division : divisions) {
            TreeItem<Object> divisionTreeItem = new TreeItem(division);
            for (Team team : division.getChildren()) {
                TreeItem<Object> teamTreeItem = new TreeItem(team);
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

    public void addTeam() {
        String teamName = tfTeamAdd.getText();
        Division toaddDivision = (Division) cbDivisions.getSelectionModel().getSelectedItem();
        toaddDivision.addObject(new Team(teamName, FXCollections.observableArrayList(new ArrayList<Player>())));
        renewTreeView(divisionObservableList);

    }

    public void addPlayer() {
        String playerName = tfPlayerAdd.getText();
        int playerAge = Integer.parseInt(tfPlayerAge.getText());
        String playerPostion = tfPlayerPosition.getText();
        String playerNation = tfPlayerNation.getText();
        Team toAddTeam = (Team) cbTeams.getSelectionModel().getSelectedItem();
        toAddTeam.addObject(new Player(playerName, playerAge, playerPostion, playerNation));
        clearPlayerFields();
    }

    private void clearPlayerFields() {
        tfPlayerAdd.clear();
        tfPlayerAge.clear();
        tfPlayerNation.clear();
        tfPlayerPosition.clear();
    }
}
