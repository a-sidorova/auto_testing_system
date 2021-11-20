package entity;

import java.io.Serializable;

/* Class of answers to question */
public class Option implements Serializable, Printable {
    public int ID = -1;
    public String text;
    public boolean isCorrect;
    public int trueSelected;
    public int falseNotSelected;
    public int falseSelected;
    public int trueNotSelected;

    Option() {}

    public Option(int ID, String text, boolean isCorrect, int trueSelected, int falseNotSelected, int falseSelected, int trueNotSelected) {
        this.ID = ID;
        this.text = text;
        this.isCorrect = isCorrect;
        this.trueSelected = trueSelected;
        this.falseNotSelected = falseNotSelected;
        this.falseSelected = falseSelected;
        this.trueNotSelected = trueNotSelected;
    }

    @Override
    public String getText() {
        return "[ OPTION ] ID: " + ID + " text: " + text;
    }
}