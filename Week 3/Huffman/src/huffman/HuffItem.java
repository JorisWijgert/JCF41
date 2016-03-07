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

    public HuffItem(Character charac, int freq) {
        this.charac = charac;
        this.freq = freq;
    }

    public Character getCharac() {
        return charac;
    }

    public int getFreq() {
        return freq;
    }
    
    public void addFreq(){
        freq++;
    }

}
