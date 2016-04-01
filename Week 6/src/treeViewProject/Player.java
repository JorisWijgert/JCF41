package treeViewProject;

/**
 * Created by jvdwi on 21-3-2016.
 */
public class Player {
    private String name;
    private int age;
    private String position;
    private String nation;

    public Player(String name, int age, String position, String nation) {
        this.name = name;
        this.age = age;
        this.position = position;
        this.nation = nation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    @Override
    public String toString(){
        return name;
    }
}
