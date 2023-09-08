package cs3500.pa02;

import cs3500.pa02.controller.StudySession;
import cs3500.pa02.view.SessionViewer;
import cs3500.pa02.view.StudyGuideBuilder;
import java.io.IOException;
import java.util.Scanner;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) throws IOException {
    if (args.length == 3) {
      String readDir = args[0];
      String order = args[1];
      String writeDir = args[2];
      StudyGuideBuilder sgb = new StudyGuideBuilder(readDir, order, writeDir);
      sgb.generateStudyGuide();
    } else if (args.length == 0) {
      Scanner in = new Scanner(System.in);
      StudySession session = SessionViewer.welcomeUser(in);
      session.startSession(in);
    }
  }
}