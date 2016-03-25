package treeViewProject;

import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by jvdwi on 25-3-2016.
 */
public abstract class CommonObject<T> {
    private String name;
    private List<T> children;

    public CommonObject(String name, ObservableList<T> children) {
        this.name = name;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    public abstract void addObject(T object);

    public abstract void editObject(T object);

    @Override
    public String toString(){
        return name;
    }
}
