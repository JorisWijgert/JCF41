/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author jvdwi
 */
public class Huffman {

    /**
     * @param args the command line arguments
     */
    private static ArrayList<Character> sentence = new ArrayList();
    private static ArrayList<HuffItem> itemsWithFreq = new ArrayList();
    public static HashMap<Character, String> trialMap = new HashMap<>();

    public static void main(String[] args) {
        String words = "bananen";
        //String words = "lorem ipsum bla di bla qun joris";
        //Stap 1
        Set<HuffItem> setHufItems = makeHashSetItems(words);
        //Stap 2
        PriorityQueue<HuffItem> phItem = sortHuffSet(setHufItems);
        //Stap 3
        phItem = makeHuffTree(phItem);
        //teken tree
        HuffItem huffItem = phItem.peek();
        drawHuffTree(huffItem);
        //Aflezen code 
        Map<Character, String> mapje = makeMapCodes(huffItem, "");
        System.out.println("aantal karakters " + words.length());
    }

    private static HashSet<HuffItem> makeHashSetItems(String words) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length(); i++) {
            char letter = words.charAt(i);
            map.put(letter, map.containsKey(letter) ? map.get(letter) + 1 : 1);
        }

        HashSet<HuffItem> hSet = new HashSet<>();
        for (Entry<Character, Integer> entry : map.entrySet()) {
            char key = entry.getKey();
            Integer value = entry.getValue();
            hSet.add(new HuffItem(key, value, null, null));
        }
        return hSet;
    }

    private static PriorityQueue<HuffItem> sortHuffSet(Set set) {
        PriorityQueue<HuffItem> pq = new PriorityQueue(set.size(), Comparator.comparing(HuffItem::getFreq));
        pq.addAll(set);
        int count = 0;

//        while (count < pq.size()) {
//            HuffItem h = pq.poll();
//            System.out.println(h.getCharac() + ":" + h.getFreq());
//        }
        return pq;
    }

    private static PriorityQueue<HuffItem> makeHuffTree(PriorityQueue<HuffItem> insertPq) {
        HuffItem a = insertPq.poll();
        HuffItem b = insertPq.poll();
        HuffItem c = new HuffItem(null, a.getFreq() + b.getFreq(), a, b);
        insertPq.add(c);
        if (insertPq.size() > 1) {
            insertPq = makeHuffTree(insertPq);
        }
        return insertPq;
    }

    private static void drawHuffTree(HuffItem phItem) {
        System.out.println(phItem.getCharac() + " : " + phItem.getFreq());
        if (phItem.getLeft() != null) {
            drawHuffTree(phItem.getLeft());
        }
        if (phItem.getRight() != null) {
            drawHuffTree(phItem.getRight());
        }
    }

    private static HashMap<Character, String> makeMapCodes(HuffItem huffItem, String code) {
        if (huffItem.getCharac() != null) {
            trialMap.put(huffItem.getCharac(), code);
        }

        if (huffItem.getLeft() != null) {
            code += "0";
            makeMapCodes(huffItem.getLeft(), code);
        }
        if (huffItem.getRight() != null) {
            code += "1";
            makeMapCodes(huffItem.getRight(), code);
        }
        return trialMap;
    }

}
