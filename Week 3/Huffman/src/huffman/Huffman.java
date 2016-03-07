/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

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
    private static PriorityQueue<HuffItem> sortedItems;

    public static void main(String[] args) {

        sortedItems = new PriorityQueue<HuffItem>(10, new Comparator<HuffItem>() {
            public int compare(HuffItem a, HuffItem b) {
                int sort1 = Integer.valueOf(a.getFreq()).compareTo(Integer.valueOf(b.getFreq()));
                //System.out.println("a" + Integer.valueOf(a.getFreq()));
                //System.out.println("b" + Integer.valueOf(b.getFreq()));
                if (sort1 != 0) {
                    return sort1;
                } else {
                    return a.getCharac().toString().toLowerCase().compareTo(b.getCharac().toString().toLowerCase());
                }
            }
        });

        String words = "lorem ipsum";
        for (Character c : words.toCharArray()) {
            sentence.add(c);
        }
        stepOneFreq();
        // TODO code application logic here
    }

    public static void stepOneFreq() {
        for (Character c : sentence) {
            boolean charFound = false;
            for (HuffItem h : itemsWithFreq) {
                if (h.getCharac().equals(c)) {
                    h.addFreq();
                    charFound = true;

                }

            }
            if (charFound == false) {
                itemsWithFreq.add(new HuffItem(c, 1));
            }
        }
        for (HuffItem h : itemsWithFreq) {
           sortedItems.add(h);
        }
        for (HuffItem h : sortedItems) {
            System.out.println(h.getCharac() + ":" + h.getFreq());
        }
    }

}
