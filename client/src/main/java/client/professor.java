package client;

import service.AutoTestingSystem;
import service.AutoTestingSystemImplService;
import service.Option;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class professor {
    public static AutoTestingSystem system;
    public static void main(String[] args) throws Exception{
        try {
            AutoTestingSystemImplService tsis = new AutoTestingSystemImplService(new URL("http://localhost:8888/TaskService?wsdl"));
            system = tsis.getAutoTestingSystemImplPort();

            InputStream inputStream = System.in;
            Scanner scanner = new Scanner(inputStream);

            while (true) {
                int code = menu(scanner);
                if (code == 0) {
                    System.out.println("Exit...");
                    break;
                }
                switch (code) {
                    case 1: {
                        System.out.println("Enter test name:");
                        String name = scanner.nextLine();
                        int id = system.createTest(name);
                        System.out.println("Test was successfully created. ID: " + id);
                        break;
                    }
                    case 2: {
                        System.out.println("Enter test ID:");
                        int testID = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter question text:");
                        String text = scanner.nextLine();

                        int questionID = system.addQuestion(text, testID);
                        System.out.println("Question was successfully created. ID: " + questionID);
                        break;
                    }
                    case 3: {
                        System.out.println("Enter question ID:");
                        int questionID = Integer.parseInt(scanner.nextLine());

                        System.out.println("Enter count of answers:");
                        int size = Integer.parseInt(scanner.nextLine());

                        ArrayList<Option> options = new ArrayList<Option>();
                        for (int i = 0; i < size; ++i) {
                            System.out.println("Enter answer text:");
                            String text = scanner.nextLine();

                            System.out.println("Is it right?");
                            boolean isCorrect = Boolean.parseBoolean(scanner.nextLine());
                            int count1, count2, count3, count4;

                            if (isCorrect) {
                                System.out.println("Add count of points if answer is right and student chose it:");
                                count1 = Integer.parseInt(scanner.nextLine());
                                System.out.println("Remove count of points if answer is right and student didn't choose it:");
                                count2 = Integer.parseInt(scanner.nextLine());
                                count3 = count4 = 0;
                            } else {
                                System.out.println("Remove count of points if answer isn't right but student chose it:");
                                count3 = Integer.parseInt(scanner.nextLine());
                                System.out.println("Add count of points if answer isn't right and student didn't choose it:");
                                count4 = Integer.parseInt(scanner.nextLine());
                                count1 = count2 = 0;
                            }
                            system.addResponseOption(questionID, text, isCorrect, count1, count2, count3, count4);
                            options.add(new Option(text, isCorrect, count1, count2, count3, count4));
                            System.out.println("Answer was successfully added");
                        }
                        System.out.println("Options were successfully added");
                    }
                    case -13211: {
                        System.out.println("Input is not valid. Try again.");
                        break;
                    }
                    default:
                    {
                        System.out.println("Unknown menu item. Try again.");
                        break;
                    }
                } // end Switch
            } // end While (true)
        } catch (javax.xml.ws.WebServiceException ex) {
            System.out.println("WebServiceException!");
        } catch (Exception ex) {
            System.out.println("Unhandled exception!");
            ex.getMessage();
        }
    }

    //-------------------------------------------------------------------------------------------------------
    // Меню пользователя
    public static int menu(Scanner scanner) {
        System.out.println("\n------ Menu ------");
        System.out.println("-- '1' - Create test.");
        System.out.println("-- '2' - Add question.");
        System.out.println("-- '3' - Add answers for question.");
        System.out.println("-- '0' - Exit.");
        int code = -1;

        try {
            System.out.print("Enter number: ");
            code = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            code = -13211;
        }

        return code;
    }
}
