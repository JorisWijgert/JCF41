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

    public static void main(String[] args) {
        String words = "lorem ipsum bla di bla qun joris";
        for (Character c
                : words.toCharArray()) {
            sentence.add(c);
        }

        stepOneFreq();
        // TODO code application logic here
    }

    public static void stepOneFreq() {
        // Count char and set them in list
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
        itemsWithFreq.sort(new Comparator<HuffItem>() {
            public int compare(HuffItem a, HuffItem b) {
                return Integer.valueOf(b.getFreq()).compareTo(Integer.valueOf(a.getFreq()));

            }
        });
        for (HuffItem h : itemsWithFreq) {
            System.out.println(h.getCharac() + ":" + h.getFreq());
        }
    }
}
