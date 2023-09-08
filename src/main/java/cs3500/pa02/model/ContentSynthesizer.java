package cs3500.pa02.model;

import java.util.ArrayList;

/**
 * responsible for synthesizing text according to study guide requirements
 */
public class ContentSynthesizer {
  static final ArrayList<String> qAndAs = new ArrayList<>();
  /**
   * Condenses the given lines according to study guide requirements

   * @param lines a list of lines to condense according to study guide requirements
   * @return the content condensed into a single String
   */
  public static String condenseLines(ArrayList<String> lines) {
    StringBuilder condense = new StringBuilder();
    StringBuilder note = new StringBuilder();
    boolean isNote = false;
    int endIndex;

    for (String line : lines) {
      if (!isNote) {
        if (!line.isEmpty() && line.substring(0, 1).equals("#")) {
          condense.append("\n").append(line).append("\n");
        } else {
          while (line.contains("[[")) {
            note.setLength(0);
            note.append("- ");
            if (line.contains("]]")) {
              endIndex = line.indexOf("]]");
              note.append(line.substring(line.indexOf("[[") + 2, endIndex));
              if (note.toString().contains(":::")) {
                qAndAs.add(note.toString());
              } else {
                condense.append(note).append("\n");
              }
              line = line.substring(endIndex + 2);
            } else {
              note.append(line.substring(line.indexOf("[[") + 2));
              isNote = true;
              break;
            }
          }
        }
      } else {
        if (line.contains("]]")) {
          note.append(" ").append(line.substring(0, line.indexOf("]]")));
          if (note.toString().contains(":::")) {
            qAndAs.add(note.toString());
          } else {
            condense.append(note).append("\n");
          }
          isNote = false;
        } else {
          note.append(line);
        }
      }
    }
    return condense.toString();
  }

  /**
   *
   */
  public static ArrayList<String> getQandAs() {
    return qAndAs;
  }

  /**
   * condenses the content from the given list of FileInfo taking out
   * the escape key from the first heading
   *
   * @param files a list of FileInfo to be condensed
   * @return the condensed content as a String
   */
  public static String combineFiles(ArrayList<FileInfo> files) {
    StringBuilder combined = new StringBuilder();
    for (FileInfo file : files) {
      combined.append(file.content);
    }
    return combined.toString().substring(1);
  }

  /**
   * combines a list of questions and answers into a String formatted for the sr file

   * @param questions the list of questions
   * @param answers the list of answers
   * @return the combined content
   */
  public static String combineQandAs(ArrayList<String> questions, ArrayList<String> answers) {
    StringBuilder content = new StringBuilder();
    if (questions.size() != answers.size()) {
      throw new IllegalArgumentException("questions and answers lists must be the same size");
    }
    for (int index = 0; index < questions.size(); index += 1) {
      content.append(questions.get(index)).append("\n");
      content.append(answers.get(index)).append("\n");
    }

    return content.toString();
  }
}
