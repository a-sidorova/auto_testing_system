package entity;

import java.io.Serializable;

// Каждая запись имеет следующую структуру – текст ответа, указание на то, правильный это ответ или нет,
// количество баллов, которое нужно начислить студенту в случае, если ответ правильный и студент его выбрал,
// количество баллов, которое нужно снять со студента, в случае, если ответ правильный, а студент его при ответе не выбрал,
// количество баллов, которое нужно снять со студента, если ответ не правильный , а студент его выбрал,
// количество баллов, которое нужно начислить студенту, если ответ не правильный и студент его не выбрал).
public class Option implements Serializable, Printable {
    public String text;
    public boolean isCorrect;
    public int trueTarget;
    public int falseNotTarget;
    public int falseTarget;
    public int trueNotTarget;

    Option() {}

    public Option(String text, boolean isCorrect, int trueTarget, int falseNotTarget, int falseTarget, int trueNotTarget) {
        this.text = text;
        this.isCorrect = isCorrect;
        this.trueTarget = trueTarget;
        this.falseNotTarget = falseNotTarget;
        this.falseTarget = falseTarget;
        this.trueNotTarget = trueNotTarget;
    }

    @Override
    public String getText() {
        return "text: " + text;
    }
}