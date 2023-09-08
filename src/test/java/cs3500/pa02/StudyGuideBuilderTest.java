package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import cs3500.pa02.controller.FileReader;
import cs3500.pa02.view.StudyGuideBuilder;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for StudyGuideBuilder class.
 */
public class StudyGuideBuilderTest {
  static final String SAMPLE_INPUTS_DIRECTORY = "src/test/resources/inputs";

  /**
   * Represents tests for GenerateStudyGuide method
   */
  @Test
  public void testGenerateStudyGuide() throws IOException {
    String dest = "src/test/resources/outputs/out1.md";

    StudyGuideBuilder sgb = new StudyGuideBuilder(SAMPLE_INPUTS_DIRECTORY,
        "filename", dest);
    sgb.generateStudyGuide();

    String expected = "src/test/resources/outputs/expected1.md";
    ArrayList<String> expectedContent = FileReader.readFileLine(Path.of(expected));

    ArrayList<String> actualContent = FileReader.readFileLine(Path.of(dest));


    assertArrayEquals(expectedContent.toArray(), actualContent.toArray());
  }
}
