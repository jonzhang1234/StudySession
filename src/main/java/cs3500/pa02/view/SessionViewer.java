package cs3500.pa02.view;

import cs3500.pa02.controller.StudySession;
import cs3500.pa02.model.SessionInfo;
import java.util.Scanner;

/**
 * Responsible for displaying the review session
 */
public class SessionViewer {
  /**
   * Displays a question and prompts the user to respond

   * @param q the question to be printed
   * @param in the Scanner for the input source
   * @return the user's input
   */
  public int printQuestion(String q, Scanner in) {
    System.out.println(q);
    System.out.println("Enter: 1 to mark easy, 2 to mark hard, 3 to see answer, or 4 to exit");
    return in.nextInt();
  }

  /**
   * Displays the welcome screen and prompts for a path and number of questions

   * @param in the Scanner for the input source
   * @return the StudySession object made with the user's input
   */
  public static StudySession welcomeUser(Scanner in) {
    System.out.println("Welcome to your study session");
    System.out.println("Enter the path to your .sr file:");
    String file = in.next();
    System.out.println("Enter number of questions:");
    int questions = in.nextInt();
    return new StudySession(file, questions);
  }

  /**
   * Displays the final stats of the review session

   * @param info the SessionInfo from the current session
   */
  public void printStats(SessionInfo info) {
    System.out.println("questions answered:" + info.getNumAnswered());
    System.out.println("questions changed from easy to hard:" + info.getNewEasy());
    System.out.println("questions changed from hard to easy:" + info.getNewHard());
    System.out.println("updated total hard questions:" + info.getTotalHard());
    System.out.println("updated total easy questions:" + info.getTotalEasy());
  }
}
