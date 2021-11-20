package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/* Class of test */
public class Test implements Serializable, Printable {
    public ArrayList<Question> questions;
    public String text;
    public int ID;
    public int points;
    public int curQuestion;
    public boolean isEnd;

    public Test() {}

    public Test(String text, int ID) {
        this.questions = new ArrayList<Question>();
        this.text = text;
        this.ID = ID;
        this.points = 0;
        this.curQuestion = -1;
        this.isEnd = false;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void run() {
        this.curQuestion = 0;
    }

    public String getNextQuestion() {
        if (curQuestion == questions.size()) {
            return "No questions!";
        }
        return questions.get(curQuestion).getText();
    }

    @Override
    public String toString() {
        return "[ INFO ][ TEST ] ID: " + ID + "\tName: " + text + "\n";
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String getText() {
        return toString();
    }
}
