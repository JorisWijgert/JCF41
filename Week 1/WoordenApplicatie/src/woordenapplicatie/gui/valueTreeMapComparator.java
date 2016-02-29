package woordenapplicatie.gui;


import java.util.Comparator;
import java.util.Map;

/**
 * Created by Qunfo on 29-2-2016.
 */
public class valueTreeMapComparator implements Comparator<String>{

    Map<String, Integer> map;

    public valueTreeMapComparator(Map<String, Integer> map) {
        this.map = map;
    }


    @Override
    public int compare(String o1, String o2) {
        if(map.get(o1) >= map.get(o2)){
            return 1;
        }else{
            return -1;
        }
    }
}
