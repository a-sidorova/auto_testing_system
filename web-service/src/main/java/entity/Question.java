package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/* Class of question of test */
public class Question implements Serializable, Printable {
    public int ID;
    public String text;
    public ArrayList<Option> options;
    private Random rand = new Random(32);

    public Question(String text, int ID) {
        this.text = text;
        this.ID = ID;
        options = new ArrayList<Option>();
    }

    public void addOption(Option option) {
        if (option.ID == -1)
            option.ID = rand.nextInt(100) + 1;
        options.add(option);
    }

    public void addOptions(ArrayList<Option> options) {
        System.out.println("[ INFO ] Option count: " + options.size());
        for (Option op : options) {
            if (op.ID == -1)
                op.ID = rand.nextInt(100) + 1;
            options.add(op);
        }
    }

    @Override
    public String getText() {
        String res = new String();
        res += "[ INFO ][ QUESTION ] ID: " + ID + "\tText: " + text + "\n";
        System.out.println(options.size());
        for (Option opt : options) {
            res += "\t" + opt.getText() + "\n";
        }
        return res;
    }
}
