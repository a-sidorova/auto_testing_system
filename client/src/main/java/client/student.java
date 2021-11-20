package client;

import service.AutoTestingSystem;
import service.AutoTestingSystemImplService;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/* Class of student */
public class student {
    public static AutoTestingSystem system;
    public static void main(String[] args) throws Exception {
        try {
            AutoTestingSystemImplService tsis = new AutoTestingSystemImplService(new URL("http://localhost:8888/AutoTestingSystem?wsdl"));
            system = tsis.getAutoTestingSystemImplPort();

            InputStream inputStream = System.in;
            Scanner scanner = new Scanner(inputStream);

            while (true) {
                int code = menu(scanner);
                if (code == 0) {
                    System.out.println("[ INFO ] Exit...");
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
                        System.out.print("[ INFO ] Enter test ID: ");
                        int testID = Integer.parseInt(scanner.nextLine());
                        system.getPoints(testID);
                    }
                    case -13211: {
                        System.out.println("[ ERROR ] Input is not valid. Try again.");
                        break;
                    }
                    default:
                    {
                        System.out.println("[ ERROR ] Unknown menu item. Try again.");
                        break;
                    }
                } // end Switch
            } // end While (true)
        } catch (javax.xml.ws.WebServiceException ex) {
            System.out.println("[ ERROR ] WebServiceException!");
        } catch (Exception ex) {
            System.out.println("[ ERROR ] Unhandled exception!");
            ex.getMessage();
        }
    }

    public static int menu(Scanner scanner) {
        System.out.println("\n------ Menu ------");
        System.out.println("-- '1' - Get test list.");
        System.out.println("-- '2' - Start test.");
        System.out.println("-- '3' - Check points.");
        System.out.println("-- '0' - Exit.");
        int code = -1;

        try {
            System.out.print("[ INFO ] Enter number: ");
            code = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            code = -13211;
        }

        return code;
    }

    public static void startTest(Scanner scanner) {
        System.out.print("[ INFO ] Enter test ID: ");
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
                        System.out.print("[ INFO ] Enter question ID: ");
                        int questionID = Integer.parseInt(scanner.nextLine());

                        System.out.print("[ INFO ] Enter answer count: ");
                        int size = Integer.parseInt(scanner.nextLine());
                        ArrayList<Integer> res = new ArrayList<Integer>();

                        for (int i = 0; i < size; ++i) {
                            System.out.print("[ INFO ] Enter answer ID: ");
                            res.add(Integer.parseInt(scanner.nextLine()));
                        }

                        system.setAnswer(testID, questionID, res);
                        break;
                    }
                    case 3: {
                        system.completeTest(testID);
                        break;
                    }
                    case 4: {
                        int points = system.getPoints(testID);
                        System.out.println("[ INFO ] Result: " + points);
                        break;
                    }
                    case 0: {
                        System.out.println("[ INFO ] Exit...");
                        return;
                    }
                    case -13211: {
                        System.out.println("[ ERROR ] Input is not valid. Try again.");
                        break;
                    }
                }
            }
        } else {
            System.out.println("[ ERROR ] Test wasn't find!");
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
            System.out.print("[ INFO ] Enter number: ");
            code = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            code = -13211;
        }

        return code;
    }
}
