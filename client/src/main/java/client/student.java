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

public class student {
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
                        System.out.println(system.getTestsList());
                        break;
                    }
                    case 2: {
                        startTest(scanner);
                        break;
                    }
                    case 3: {
                        System.out.println("Enter test IDа:");
                        int testID = Integer.parseInt(scanner.nextLine());
                        system.getPoints(testID);
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
        System.out.println("-- '1' - Get test list.");
        System.out.println("-- '2' - Start test.");
        System.out.println("-- '3' - Check points.");
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

    public static void startTest(Scanner scanner) {
        System.out.println("Enter test ID:");
        int testID = Integer.parseInt(scanner.nextLine());
        if (system.runTest(testID)) {
            while (true) {
                int code_StartTest = menu_StartTest(scanner);

                switch (code_StartTest) {
                    case 1: {
                        System.out.println(system.getNextQuestion(testID));
                        break;
                    }
                    case 2: {
                        System.out.println("Enter question ID:");
                        int questionID = Integer.parseInt(scanner.nextLine());

                        System.out.println("Enter answer count:");
                        int size = Integer.parseInt(scanner.nextLine());
                        Boolean[] res = new Boolean[size];

                        for (int i = 0; i < size; ++i) {
                            System.out.println("Enter answer:");
                            res[i] = Boolean.parseBoolean(scanner.nextLine());
                        }
                        system.setAnswer(testID, questionID, res);
                    }
                    case 3: {
                        system.completeTest(testID);
                    }
                    case 4: {
                        int points = system.getPoints(testID);
                        System.out.println("Result: " + points);
                    }
                    case 0: {
                        break;
                    }
                    case -13211: {
                        System.out.println("Input is not valid. Try again.");
                        break;
                    }
                } // end Switch
            } // end While (true)
        } else {
            System.out.println("Test wasn't find!");
        }
    }

    public static int menu_StartTest(Scanner scanner) {
        System.out.println("\n------ Menu ------");
        System.out.println("-- '1' - Get next question.");
        System.out.println("-- '2' - Answer the question.");
        System.out.println("-- '3' - Finish test.");
        System.out.println("-- '4' - Get result.");
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
