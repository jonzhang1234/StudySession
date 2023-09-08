package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa02.model.FileInfo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for FileInfo class.
 */
public class FileInfoTest {

  /**
   * Represents tests for compareName, compareCreated, and compareModified methods
   */
  @Test
  public void testCompareMethods() throws IOException {
    Path dir1 = Path.of("src/test/resources/inputs/dummyFile.txt");
    BasicFileAttributes attrs1 = Files.readAttributes(dir1, BasicFileAttributes.class);

    Path dir2 = Path.of("src/test/resources/inputs/dummyFile2.txt");
    BasicFileAttributes attrs2 = Files.readAttributes(dir2, BasicFileAttributes.class);

    FileInfo file1 = new FileInfo("Hello", "dummyFile.txt",
        attrs1.creationTime(), attrs1.lastModifiedTime());
    FileInfo file2 = new FileInfo("Hello", "dummyFile2.txt",
        attrs2.creationTime(), attrs2.lastModifiedTime());

    assertEquals(file1.compareName(file2), dir1.getFileName().compareTo(dir2.getFileName()));
    assertEquals(file1.compareCreated(file2),
        attrs1.creationTime().compareTo(attrs2.creationTime()));
    assertEquals(file1.compareModified(file2),
        attrs1.lastModifiedTime().compareTo(attrs2.lastModifiedTime()));
  }

}
