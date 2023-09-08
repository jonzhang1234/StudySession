package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa02.controller.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for FileReader class.
 */
public class FileReaderTest {

  /**
   * Represents tests for readFileLines method
   */
  @Test
  public void testReadFileLines() {
    Path dir1 = Path.of("src/test/resources/inputs/dummyFile.txt");
    ArrayList<String> actualContent1 = FileReader.readFileLine(dir1);

    Path dir2 = Path.of("src/test/resources/inputs/dummyFile2.txt");
    ArrayList<String> actualContent2 = FileReader.readFileLine(dir2);

    ArrayList<String> expectedContent1 = new ArrayList<>();
    expectedContent1.add("modified");
    ArrayList<String> expectedContent2 = new ArrayList<>();

    assertEquals(expectedContent1, actualContent1);
    assertEquals(expectedContent2, actualContent2);
  }

  /**
   * Represents tests for writeToFile method
   */
  @Test
  public void testWriteToFile() throws IOException {
    Path dest1 = Path.of("src/test/resources/outputs/dummyFile.txt");
    Path dest2 = Path.of("src/test/resources/outputs/dummyFile2.txt");

    FileReader.writeToFile(dest1, "out1");
    FileReader.writeToFile(dest2, "out2");

    ArrayList<String> expectedContent1 = new ArrayList<>();
    expectedContent1.add("out1");
    ArrayList<String> expectedContent2 = new ArrayList<>();
    expectedContent2.add("out2");

    ArrayList<String> actualContent1 = FileReader.readFileLine(dest1);
    ArrayList<String> actualContent2 = FileReader.readFileLine(dest2);

    assertEquals(expectedContent1, actualContent1);
    assertEquals(expectedContent2, actualContent2);
  }
}
