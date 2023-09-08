package cs3500.pa02.controller;

import cs3500.pa02.model.Difficulty;
import cs3500.pa02.model.Flashcard;
import cs3500.pa02.model.SessionInfo;
import cs3500.pa02.view.SessionViewer;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Responsible for controlling the study session
 */
public class StudySession {
  public Path file;
  public int questions;
  SessionInfo info;
  SessionViewer view;

  /**
   * Constructor for StudySession

   * @param file the sr file
   * @param questions the number of questions
   */
  public StudySession(String file, int questions) {
    this.file = Path.of(file);
    this.questions = questions;
    this.view = new SessionViewer();
    this.info = CardUpdater.readCardData(this.file);
  }

  /**
   * displays session and responds according to inputs
   *
   * @param in the scanner for the input stream
   * @throws IOException if the file from the constructor doesn't exist
   */
  public void startSession(Scanner in) throws IOException {
    int option = 0;

    ArrayList<Flashcard> shuffled = info.getSessionCards(questions);

    for (Flashcard fc : shuffled) {
      option = view.printQuestion(fc.question, in);
      switch (option) {
        case 1:
          info.changeDiff(fc, Difficulty.EASY);
          break;
        case 2:
          info.changeDiff(fc, Difficulty.HARD);
          break;
        case 3:
          System.out.println(fc.answer);
          info.addNumAnswered();
          break;
        case 4:
          endSession();
          return;
        default:
          System.err.println("Invalid input");
          break;
      }
    }

    endSession();
  }

  /**
   * displays the end screen and writes metadata to the source file

   * @throws IOException if the file doesn't exist
   */
  public void endSession() throws IOException {
    view.printStats(info);
    CardUpdater.writeMetaData(file, info);
  }
}
