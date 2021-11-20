package service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import entity.*;

import java.util.ArrayList;

@WebService
public interface AutoTestingSystem {
    @WebMethod
    public int createTest(String name);

    @WebMethod
    public int addQuestion(String text, int testID);

    @WebMethod
    public void addResponceOptions(int testID, int questionID, ArrayList<Option> options);

    @WebMethod
    public void addResponseOption(int testID, int questionID, String text, boolean isCorrect, int trueSelected,
                                  int falseNotSelected, int falseSelected, int trueNotSelected);

    @WebMethod
    public String getTestsList();

    @WebMethod
    public boolean runTest(int id);

    @WebMethod
    public String getNextQuestion(int testID);

    @WebMethod
    public void setAnswer(int testID, int questionID, ArrayList<Integer> answers);

    @WebMethod
    public void completeTest(int testID);

    @WebMethod
    public int getPoints(int testID);
}
