package service;
import entity.*;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Random;

@WebService(endpointInterface = "service.AutoTestingSystem")
public class AutoTestingSystemImpl implements AutoTestingSystem {
    public ArrayList<Test> tests = new ArrayList<Test>();
    private Random rand = new Random(23);

    @Override
    public int createTest(String name) {
        Test test = new Test(name, rand.nextInt(1000) + 1);
        System.out.println(tests.size());
        tests.add(test);
        return test.ID;
    }

    @Override
    public int addQuestion(String text, int testID) {
        for (Test test : tests) {
            if (test.ID == testID) {
                Question q = new Question(text, rand.nextInt(100000) + 1);
                test.addQuestion(q);
                return  q.ID;
            }
        }
        return -1;
    }

    @Override
    public void addResponceOptions(int testID, int questionID, ArrayList<Option> options) {
        System.out.println("[ INFO ] Option count: " + options.size());

        for (Test test : tests) {
            if (test.ID == testID) {
                for (Question q : test.questions) {
                    if (q.ID == questionID) {
                        q.addOptions(options);
                    }
                }

                break;
            }
        }
    }

    @Override
    public void addResponseOption(int testID, int questionID, String text, boolean isCorrect, int trueSelected,
                                  int falseNotSelected, int falseSelected, int trueNotSelected) {
        for (Test test : tests) {
            if (test.ID == testID) {
                for (Question q : test.questions) {
                    if (q.ID == questionID) {
                        Option option = new Option(rand.nextInt(100) + 1, text, isCorrect, trueSelected, falseNotSelected, falseSelected, trueNotSelected);
                        q.addOption(option);
                    }
                }

                break;
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

    @Override
    public String getNextQuestion(int testID) {
        for (Test test : tests) {
            if (test.ID == testID) {
                return test.getNextQuestion();
            }
        }
        return new String("[ ERROR ] Test wasn't found!");
    }

    @Override
    public void setAnswer(int testID, int questionID, ArrayList<Integer> answersID) {
        for (Test t : tests) {
            if (t.ID == testID) {
                for (Question q : t.questions) {
                    if (q.ID == questionID) {
                        for (Option answer : q.options) {
                            boolean expected = answer.isCorrect;

                            int idx;
                            for (idx = 0; idx < answersID.size(); idx++) {
                                if (answer.ID == answersID.get(idx).intValue()) {

                                    if (expected) {
                                        t.points += answer.trueSelected;
                                    } else {
                                        t.points -= answer.falseSelected;
                                    }

                                    break;
                                }
                            }

                            if (idx == answersID.size()) {
                                if (expected) {
                                    t.points += answer.trueNotSelected;
                                } else {
                                    t.points -= answer.falseNotSelected;
                                }
                            }
                        }

                        t.curQuestion++;
                        return;
                    }
                }

                return;
            }
        }
    }

    @Override
    public void completeTest(int testID) {
        for (Test test : tests) {
            if (test.ID == testID) {
                test.isEnd = true;
                break;
            }
        }
    }

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