package client;

import service.AutoTestingSystem;
import service.AutoTestingSystemImplService;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/* Class of professor */
public class professor {
    public static AutoTestingSystem system;
    public static void main(String[] args) throws Exception{
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
                        System.out.print("[ INFO ] Enter test name: ");
                        String name = scanner.nextLine();
                        int id = system.createTest(name);
                        System.out.println("[ INFO ] Test was successfully created. ID: " + id);
                        break;
                    }
                    case 2: {
                        System.out.print("[ INFO ] Enter test ID: ");
                        int testID = Integer.parseInt(scanner.nextLine());
                        System.out.print("[ INFO ] Enter question text: ");
                        String text = scanner.nextLine();

                        int questionID = system.addQuestion(text, testID);
                        System.out.println("[ INFO ] Question was successfully created. ID: " + questionID);
                        break;
                    }
                    case 3: {
                        System.out.print("[ INFO ] Enter test ID: ");
                        int testID = Integer.parseInt(scanner.nextLine());

                        System.out.print("[ INFO ] Enter question ID: ");
                        int questionID = Integer.parseInt(scanner.nextLine());

                        System.out.print("[ INFO ] Enter count of answers: ");
                        int size = Integer.parseInt(scanner.nextLine());

                        for (int i = 0; i < size; ++i) {
                            System.out.print("[ INFO ] Enter answer text: ");
                            String text = scanner.nextLine();

                            System.out.print("[ INFO ] Is it right? ");
                            boolean isCorrect = Boolean.parseBoolean(scanner.nextLine());
                            int count1, count2, count3, count4;

                            if (isCorrect) {
                                System.out.print("[ INFO ] Add count of points if answer is right and student chose it: ");
                                count1 = Integer.parseInt(scanner.nextLine());
                                System.out.print("[ INFO ] Remove count of points if answer is right and student didn't choose it: ");
                                count2 = Integer.parseInt(scanner.nextLine());
                                count3 = count4 = 0;
                            } else {
                                System.out.print("[ INFO ] Remove count of points if answer isn't right but student chose it: ");
                                count3 = Integer.parseInt(scanner.nextLine());
                                System.out.print("[ INFO ] Add count of points if answer isn't right and student didn't choose it: ");
                                count4 = Integer.parseInt(scanner.nextLine());
                                count1 = count2 = 0;
                            }
                            system.addResponseOption(testID, questionID, text, isCorrect, count1, count2, count3, count4);
                            System.out.println("[ INFO ] Answer was successfully added");
                        }
                        System.out.println("[ INFO ] Options were successfully added");
                        break;
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
                }
            }
        } catch (javax.xml.ws.WebServiceException ex) {
            System.out.println("[ ERROR ] WebServiceException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("[ ERROR ] Unhandled exception: " + ex.getMessage());
            ex.getMessage();
        }
    }

    public static int menu(Scanner scanner) {
        System.out.println("\n------ Menu ------");
        System.out.println("-- '1' - Create test.");
        System.out.println("-- '2' - Add question.");
        System.out.println("-- '3' - Add answers for question.");
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
