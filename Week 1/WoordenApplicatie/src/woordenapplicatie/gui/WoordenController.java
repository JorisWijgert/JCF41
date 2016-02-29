package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author frankcoenen
 */
public class WoordenController implements Initializable {

    private static final String DEFAULT_TEXT = "Een, twee, drie, vier\n"
            + "Hoedje van, hoedje van\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "Heb je dan geen hoedje meer\n"
            + "Maak er één van bordpapier\n"
            + "Eén, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van, hoedje van\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "En als het hoedje dan niet past\n"
            + "Zetten we 't in de glazenkas\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier";

    @FXML
    private Button btAantal;
    @FXML
    private TextArea taInput;
    @FXML
    private Button btSorteer;
    @FXML
    private Button btFrequentie;
    @FXML
    private Button btConcordantie;
    @FXML
    private TextArea taOutput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taInput.setText(DEFAULT_TEXT);
    }

    @FXML
    private void aantalAction(ActionEvent event) {
        String[] words = (taInput.getText().replace("\n", " ")).split(" ");
        taOutput.setText("Totaal aantal woorden: " + String.valueOf(words.length));

        Set<String> uniqueWords = new HashSet();

        for (String word : words) {
            uniqueWords.add(word.toLowerCase().replace(",", ""));
        }
        taOutput.appendText("\nAantal verschillende woorden: " + String.valueOf(uniqueWords.size()));
    }

    @FXML
    private void sorteerAction(ActionEvent event) {
        String[] words = (taInput.getText().replace("\n", " ")).split(" ");
        Set<String> uniqueWords = new TreeSet(Collections.reverseOrder());
        for (String word : words) {
            uniqueWords.add(word.toLowerCase().replace(",", ""));
        }
        taOutput.setText("Unieke woorden:");
        for (String w : uniqueWords) {
            taOutput.appendText("\n" + w);
        }
    }

    @FXML
    private void frequentieAction(ActionEvent event) {
        String[] words = (taInput.getText().replace("\n", " ")).split(" ");
        Map<String, Integer> freqWords = new HashMap<String, Integer>();

        for (String word : words) {
            String w = word.toLowerCase().replace(",", "");
            if (freqWords.containsKey(w)) {
                Integer i = freqWords.get(w);
                i++;
                freqWords.put(w, i);
            } else {
                freqWords.put(w, 1);
            }
        }
        taOutput.setText("Frequentie woorden:");
        /*
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(freqWords.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        taOutput.setText("Aantal keer:");
        for (String w : sortedMap.keySet()) {
            taOutput.appendText("\n" + w + ":" + sortedMap.get(w));
        }*/

        TreeMap<String, Integer> freqWordsTreeMap = new TreeMap<>(new valueTreeMapComparator(freqWords));
        freqWordsTreeMap.putAll(freqWords);
        for (Map.Entry<String, Integer> entry : freqWordsTreeMap.entrySet()) {
            taOutput.appendText("\n" + entry.getKey() + "  " + entry.getValue());
        }

    }

    @FXML
    private void concordatieAction(ActionEvent event) {
        Map<String, TreeSet<Integer>> countedMap = new TreeMap();
        int lineNr = 1;
        String[] words = (taInput.getText().replace("\n", " !! ")).split(" ");
        for (String w : words) {
            String word = w.toLowerCase().replace(",", "");
            if (word.equals("!!")) {
                lineNr++;
            } else if (countedMap.containsKey(word)) {
                TreeSet<Integer> lineSet = countedMap.get(word);
                lineSet.add(lineNr);
            } else {
                TreeSet<Integer> lineSet = new TreeSet();
                lineSet.add(lineNr);
                countedMap.put(word, lineSet);
            }
        }
        taOutput.setText("Regels:");
        for (String w : countedMap.keySet()) {
            taOutput.appendText("\n" + w + " : " + countedMap.get(w));
        }

    }
}
