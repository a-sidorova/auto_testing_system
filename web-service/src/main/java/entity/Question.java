package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable, Printable {
    public int ID;
    public String text;
    public ArrayList<Option> options;

    public Question(String text, int ID) {
        this.text = text;
        this.ID = ID;
        options = new ArrayList<Option>();
    }

    public void addOption(Option option) {
        options.add(option);
    }

    public void addOptions(ArrayList<Option> options) {
        System.out.println("SIZE = " + options.size());
        for (Option op : options) {
            options.add(op);
        }
    }

    @Override
    public String getText() {
        String res = new String();
        res += "ID: " + ID + "\tText: " + text + "\n";
        System.out.println(options.size());
        for (Option opt : options) {
            res += "\t" + opt.getText() + "\n";
        }
        return res;
    }
}
