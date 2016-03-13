/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

/**
 *
 * @author jvdwi
 */
public class HuffItem {

    private Character charac;
    private int freq;
    private HuffItem left;
    private HuffItem right;

    public HuffItem(Character charac, int freq, HuffItem left, HuffItem right) {
        this.charac = charac;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public HuffItem getLeft() {
        return left;
    }

    public void setLeft(HuffItem left) {
        this.left = left;
    }

    public HuffItem getRight() {
        return right;
    }

    public void setRight(HuffItem right) {
        this.right = right;
    }

    public Character getCharac() {
        return charac;
    }

    public int getFreq() {
        return freq;
    }

    public void addFreq() {
        freq++;
    }

}
