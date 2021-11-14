package service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import entity.*;

import java.util.ArrayList;

@WebService
public interface AutoTestingSystem {
    // Создать новый тест (принимает наименование теста, возвращает его идентификатор)
    @WebMethod
    public int createTest(String name);

    // Добавить новый вопрос (принимает текст вопроса, и идентификатор теста, к которому относится вопрос, возвращает его идентификатор)
    @WebMethod
    public int addQuestion(String text, int testID);

    // Добавить для вопроса варианты ответа (принимает идентификатор вопроса и массив записей, описывающих ответы на него.
    @WebMethod
    public void addResponceOptions(int questionID, ArrayList<Option> options);

    @WebMethod
    public void addResponseOption(int questionID, String text, boolean isCorrect, int trueTarget,
                                  int falseNotTarget, int falseTarget, int trueNotTarget);

    // Получить список всех тестов (возвращается массив наименований тестов и их идентификаторов)
    public String getTestsList();

    // Начать тест (принимает идентификатор теста)
    public boolean runTest(int id);

    // Получить следующий вопрос теста
    // (принимает идентификатор теста, возвращает текст вопроса,идентификатор вопроса и массив записей, содержащих текст варианта ответа и идентификатор варианта ответа)
    public String getNextQuestion(int testID);

    // Ответить на вопрос (принимает идентификатор вопроса и массив идентификаторов ответа на него)
    public void setAnswer(int testID, int questionID, Boolean[] answers);

    // Закончить тест (принимает идентификатор теста)
    public void completeTest(int testID);

    // Получить количество полученных баллов (принимает идентификатор теста, возвращает количество полученных баллов)
    public int getPoints(int testID);
}
