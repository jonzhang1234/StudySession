package cs3500.pa02.controller;

import cs3500.pa02.model.Difficulty;
import cs3500.pa02.model.Flashcard;
import cs3500.pa02.model.SessionInfo;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Responsible for reading updating card data
 */
public class CardUpdater {

  /**
   * reads data from given file into hardCards and easyCards

   * @param file the sr file to read from
   * @return the SessionInfo made from the hard and easy cards
   */
  public static SessionInfo readCardData(Path file) {
    ArrayList<String> lines = FileReader.readFileLine(file);
    ArrayList<Flashcard> hardCards = new ArrayList<>();
    ArrayList<Flashcard> easyCards = new ArrayList<>();

    String q = lines.get(0);
    String a;
    int i = 0;
    while (i < lines.size() && !lines.get(i).equals("")) {
      if (i % 2 == 0) {
        q = lines.get(i);
      } else {
        a = lines.get(i);
        Flashcard fc = new Flashcard(q, a, Difficulty.HARD);
        hardCards.add(fc);
      }
      i += 1;
    }

    for (int index = i + 1; index < lines.size(); index += 1) {
      if (index % 2 == 1) {
        q = lines.get(index);
      } else {
        a = lines.get(index);
        Flashcard fc = new Flashcard(q, a, Difficulty.EASY);
        easyCards.add(fc);
      }
    }

    return new SessionInfo(hardCards, easyCards);
  }

  /**
   * writes updated data to the sr file

   * @param file the file to write metadata to
   * @param info the SessionInfo from the current session
   * @throws IOException if file doesn't exist
   */
  public static void writeMetaData(Path file, SessionInfo info) throws IOException {
    StringBuilder content = new StringBuilder();
    for (Flashcard fc : info.getHardCards()) {
      content.append(fc.question).append("\n");
      content.append(fc.answer).append("\n");
    }
    content.append("\n");
    for (Flashcard fc : info.getEasyCards()) {
      content.append(fc.question).append("\n");
      content.append(fc.answer).append("\n");
    }
    FileReader.writeToFile(file, content.toString());
  }
}
