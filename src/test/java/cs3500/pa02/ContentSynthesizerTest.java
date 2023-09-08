package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import cs3500.pa02.controller.FileReader;
import cs3500.pa02.model.ContentSynthesizer;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for ContentSynthesizer class.
 */
public class ContentSynthesizerTest {
  static final String SAMPLE_INPUTS_DIRECTORY = "src/test/resources/inputs";

  /**
   * Represents tests for CondenseLines method
   */
  @Test
  public void testCondenseLines() throws IOException {
    Path dir = Path.of(SAMPLE_INPUTS_DIRECTORY + "/arrays.md");
    String content = ContentSynthesizer.condenseLines(FileReader.readFileLine(dir));

    Path dest = Path.of("src/test/resources/outputs/out2.md");
    FileReader.writeToFile(dest, content);

    String expected = "src/test/resources/outputs/expected2.md";
    ArrayList<String> expectedContent = FileReader.readFileLine(Path.of(expected));

    ArrayList<String> actualContent = FileReader.readFileLine(dest);

    assertArrayEquals(expectedContent.toArray(), actualContent.toArray());
  }

  @Test
  public void testCombineQAndAs() {

  }
}
