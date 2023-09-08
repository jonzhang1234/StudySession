package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa02.controller.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for Driver class.
 */
class DriverTest {

  /**
   * Represents tests for main method
   */
  @Test
  public void testMain() throws IOException {
    String dest = "src/test/resources/outputs/out1.md";
    String expected = "src/test/resources/outputs/expected1.md";
    String[] args1 = {"src/test/resources/inputs", "filename", dest};

    ArrayList<String> expectedContent = FileReader.readFileLine(Path.of(expected));
    Driver.main(args1);
    ArrayList<String> actualContent = FileReader.readFileLine(Path.of(dest));

    assertArrayEquals(expectedContent.toArray(), actualContent.toArray());
  }

  /**
   * represents test for invalid input
   */
  @Test
  public void testInput() {
    String dest = "src/test/resources/outputs/out1.md";
    String[] args2 = {"src/test/resources/inputs", "birthday", dest};
    assertThrows(IllegalArgumentException.class, () -> Driver.main(args2));
  }

  @Test
  public void testSession() {
    String[] empty = {};
    assertThrows(NoSuchElementException.class, () -> Driver.main(empty));
  }
}