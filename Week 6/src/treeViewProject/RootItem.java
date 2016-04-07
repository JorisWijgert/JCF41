package treeViewProject;

import javafx.collections.ObservableList;

/**
 * Created by jvdwi on 4-4-2016.
 */
public class RootItem extends CommonObject<Division> {
    public RootItem(String name, ObservableList<Division> divisions) {
        super(name, divisions);
    }

    @Override
    public void addObject(Division object) {

    }

    @Override
    public void editObject(Division object) {

    }
}
