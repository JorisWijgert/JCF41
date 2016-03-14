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

    public static void main(String[] args) {
        String words = "lorem ipsum bla di bla qun joris";
        //Stap 1
        Set<HuffItem> setHufItems = makeHashSetItems(words);
        //Stap 2
        PriorityQueue<HuffItem> phItem = sortHufSet(setHufItems); 
//        for (Character c
//                : words.toCharArray()) {
//            sentence.add(c);
//        }
//
//        stepOneFreq();
        System.out.println("aantal karakters " + words.length());
        // TODO code application logic here
    }

    private static PriorityQueue<HuffItem> sortHufSet(Set set){
       PriorityQueue<HuffItem> pq = new PriorityQueue(set.size(), Comparator.comparing(HuffItem :: getFreq));
       pq.addAll(set);
       int count = 0;           
       
       while(count < pq.size()){
           HuffItem h = pq.poll();
            System.out.println(h.getCharac() + ":" + h.getFreq());
        }
       return pq;
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
            // do what you have to do here
            // In your case, an other loop.
        }
        //map.forEach((character, count) -> hSet.add(new HuffItem(character, count, null, null)));
        return hSet;
    }

//    public static void stepOneFreq() {
//        // Count char and set them in list
//        for (Character c : sentence) {
//            boolean charFound = false;
//            for (HuffItem h : itemsWithFreq) {
//                if (h.getCharac().equals(c)) {
//                    h.addFreq();
//                    charFound = true;
//
//                }
//
//            }
//            if (charFound == false) {
//                itemsWithFreq.add(new HuffItem(c, 1));
//            }
//        }
//        itemsWithFreq.sort(new Comparator<HuffItem>() {
//            public int compare(HuffItem a, HuffItem b) {
//                return Integer.valueOf(b.getFreq()).compareTo(Integer.valueOf(a.getFreq()));
//
//            }
//        });
//        for (HuffItem h : itemsWithFreq) {
//            System.out.println(h.getCharac() + ":" + h.getFreq());
//            
//        }
//        
//    }
}
