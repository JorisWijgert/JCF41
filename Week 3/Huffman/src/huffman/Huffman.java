/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private static HashMap<Character, String> trialMap = new HashMap<>();
    private static HuffItem trees;
    private static HashMap<Character, String> makeMapCodesMap = new HashMap();

    public static void main(String[] args) {
        //String words = "bananen";
        String words = "lorem ipsum bla di bla qun joris";
        //String words = "abcdefghijdasklvmfldhbdwasndwwohiewldffughpjooaaqaekrstuvwxyz";
        //String words = "aaaaaabiiii                   iii";
        //String words = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";

        //Stap 1
        Set<HuffItem> setHufItems = makeHashSetItems(words);
        //Stap 2
        PriorityQueue<HuffItem> phItem = sortHuffSet(setHufItems);
        //Stap 3
        trees = makeHuffTree(phItem);
        drawHuffTree(trees);
        //Aflezen code
        //Stap 4
        makeMapCodes(trees, "");
        System.out.println(makeMapCodesMap);
        System.out.println("aantal karakters " + words.length());
        //Stap 5
        ArrayList<Boolean> bools = makeBooleanCode(words, makeMapCodesMap);
        makeBinaryFile(bools, trees, words);
        //Stap 6
        try {
            readFile();
        } catch (IOException ex) {
            Logger.getLogger(Huffman.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            hSet.add(new HuffItem(key, value, null, null, ""));
        }
        return hSet;
    }

    private static PriorityQueue<HuffItem> sortHuffSet(Set set) {
        PriorityQueue<HuffItem> pq = new PriorityQueue(set.size(), Comparator.comparing(HuffItem::getFreq));
        pq.addAll(set);
        return pq;
    }

    private static HuffItem makeHuffTree(PriorityQueue<HuffItem> insertPq) {
        while (insertPq.size() > 1) {
            HuffItem a = insertPq.poll();
            HuffItem b = insertPq.poll();
            int itemSum = a.getFreq() + b.getFreq();
            HuffItem c = new HuffItem(null, itemSum, a, b, "");
            insertPq.add(c);
        }
        return insertPq.poll();
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

    private static void makeMapCodes(HuffItem huffItem, String code) {

        if (huffItem != null) {
            makeMapCodes(huffItem.getLeft(), code + '0');
            makeMapCodes(huffItem.getRight(), code + '1');
            if (huffItem.getRight() == null && huffItem.getLeft() == null) {
                makeMapCodesMap.put(huffItem.getCharac(), code);
            }
        }
    }

    private static ArrayList<Boolean> makeBooleanCode(String words, HashMap<Character, String> hm) {
        String returnString = "";
        ArrayList<Boolean> chars = new ArrayList();
        int count = 0;
        for (char c : words.toCharArray()) {
            returnString += hm.get(c);
        }
        for (char c : returnString.toCharArray()) {
            if (c == '0') {
                chars.add(false);
            } else if (c == '1') {
                chars.add(true);
            }
            //chars.add( c== '1')
        }
        return chars;
    }

    private static void makeBinaryFile(ArrayList<Boolean> compressedValues, HuffItem tree, String words) {
        FileOutputStream fosC = null;
        FileWriter fw = null;

        try {
            File compressed = new File("D:\\compressed.bin");
            File notCompressed = new File("D:\\notCompressed.txt");
            fosC = new FileOutputStream(compressed);
            ObjectOutputStream oosC = new ObjectOutputStream(fosC);
            oosC.writeObject(tree);
            for (boolean bool : compressedValues) {
                oosC.writeObject(bool);
            }
            fw = new FileWriter(notCompressed);
            fw.write(words);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Huffman.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Huffman.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fosC.close();
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(Huffman.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void readFile() throws IOException {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        File compressed = new File("D:\\compressed.bin");
        HuffItem tree = null;
        BitSet bits = null;
        ArrayList<Boolean> boolValues = null;
        try {
            fis = new FileInputStream(compressed);
            ois = new ObjectInputStream(fis);
            tree = (HuffItem) ois.readObject();
            boolValues = new ArrayList();
            Object b = null;
            while ((b = ois.readObject()) != null) {
                boolValues.add((Boolean) b);
            }

        } catch (EOFException e) {
            System.out.println("Done reading");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Huffman.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fis.close();
            ois.close();
        }
        readCodes(tree, boolValues, "");
    }

    private static void readCodes(HuffItem tree, ArrayList<Boolean> bools, String words) {
        StringBuilder sb = new StringBuilder();
        HuffItem c = tree;
        for (int i = 0; i < bools.size(); i++) {
            c = bools.get(i) == true ? c.getRight() : c.getLeft();
            if (c.getLeft() == null && c.getRight() == null) {
                sb.append(c.getCharac());
                c = tree;
            }
        }
        System.out.println("Result: " + sb);
    }
}
