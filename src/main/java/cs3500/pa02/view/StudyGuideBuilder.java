package cs3500.pa02.view;

import cs3500.pa02.model.OrderFlag;
import cs3500.pa02.controller.FileReader;
import cs3500.pa02.controller.FileTraverser;
import cs3500.pa02.model.ContentSynthesizer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Responsible for generating a study guide from the required inputs
 */
public class StudyGuideBuilder {
  private final String source;
  private final OrderFlag order;
  private final String dest;

  public StudyGuideBuilder(String source, String order, String dest) {
    this.source = source;
    this.order = OrderFlag.valueOf(order.toUpperCase());
    this.dest = dest;
  }

  /**
   * Generates a study guide to this object's destination file according
   * to project requirements

   * @throws IOException if the file paths are invalid
   */
  public void generateStudyGuide() throws IOException {
    FileTraverser pf = new FileTraverser();
    Files.walkFileTree(Path.of(source), pf);

    String studyGuide = ContentSynthesizer.combineFiles(pf.getSortedFiles(order));
    System.out.println(studyGuide);
    FileReader.writeToFile(Path.of(dest + ".md"), studyGuide);

    String qAndAs = ContentSynthesizer.combineQandAs(pf.getQuestions(), pf.getAnswers());
    FileReader.writeToFile(Path.of(dest + ".sr"), qAndAs);
  }
}
