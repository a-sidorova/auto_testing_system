package service;
import entity.*;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Random;

@WebService(endpointInterface = "service.AutoTestingSystem")
public class AutoTestingSystemImpl implements AutoTestingSystem {
    public ArrayList<Test> tests = new ArrayList<Test>();
    public ArrayList<Question> questions = new ArrayList<Question>();
    private Random rand = new Random(666);

    @Override
    public int createTest(String name) {
        Test test = new Test(name, rand.nextInt(100000) + 1);
        System.out.println(tests.size());
        tests.add(test);
        return test.ID;
    }

    @Override
    public int addQuestion(String text, int testID) {
        for (Test test : tests) {
            if (test.ID == testID) {
                Question q = new Question(text, rand.nextInt(100000) + 1);
                questions.add(q);
                test.addQuestion(q);
                return  q.ID;
            }
        }
        return -1;
    }

    // Добавить для вопроса варианты ответа (принимает идентификатор вопроса и массив записей, описывающих ответы на него.
    @Override
    public void addResponceOptions(int questionID, ArrayList<Option> options) {
        System.out.println("SIZE");
        System.out.println(options.size());
        for (Question q : questions) {
            if (q.ID == questionID) {
                q.addOptions(options);
            }
        }
    }

    @Override
    public void addResponseOption(int questionID, String text, boolean isCorrect, int trueTarget,
                                  int falseNotTarget, int falseTarget, int trueNotTarget) {
        for (Question q : questions) {
            if (q.ID == questionID) {
                Option option = new Option(text, isCorrect, trueTarget, falseNotTarget, falseTarget, trueNotTarget);
                q.addOption(option);
            }
        }
    }

    @Override
    public String getTestsList() {
        String res = new String();
        for (Test test : tests) {
            res += test.getText();
        }
        return res;
    }

    // Начать тест (принимает идентификатор теста)
    @Override
    public boolean runTest(int id) {
        for (Test test : tests) {
            if (test.ID == id) {
                test.run();
                return true;
            }
        }
        return false;
    }

    // Получить следующий вопрос теста
    // (принимает идентификатор теста, возвращает текст вопроса,идентификатор вопроса и массив записей, содержащих текст варианта ответа и идентификатор варианта ответа)
    @Override
    public String getNextQuestion(int testID) {
        String res = new String();
        for (Test test : tests) {
            if (test.ID == testID) {
                res = test.getNextQuestion();
            }
        }
        return res;
    }

    // Ответить на вопрос (принимает идентификатор вопроса и массив идентификаторов ответа на него)
    @Override
    public void setAnswer(int testID, int questionID, Boolean[] answers) {
        Test test = new Test();
        for (Test t : tests) {
            if (t.ID == testID) {
                test = t;
            }
        }

        for (Question q : questions) {
            if (q.ID == questionID) {
                for (int i = 0; i < answers.length; ++i) {
                    boolean cur = answers[i];
                    boolean expected = q.options.get(i).isCorrect;

                    if (cur == true && expected == true) {
                        test.points += q.options.get(i).trueTarget;
                    } else if (cur == true && expected == false) {
                        test.points -= q.options.get(i).falseNotTarget;
                    } else if (cur == false && expected == false) {
                        test.points += q.options.get(i).trueNotTarget;
                    } else {
                        test.points -= q.options.get(i).falseTarget;
                    }
                }
                test.curQuestion++;
            }
        }
    }

    // Закончить тест (принимает идентификатор теста)
    @Override
    public void completeTest(int testID) {
        for (Test test : tests) {
            if (test.ID == testID) {
                test.isEnd = true;
            }
        }
    }

    // Получить количество полученных баллов (принимает идентификатор теста, возвращает количество полученных баллов)
    @Override
    public int getPoints(int testID) {
        for (Test test : tests) {
            if (test.ID == testID) {
                return test.getPoints();
            }
        }
        return  0;
    }
}