package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa02.controller.FileReader;
import cs3500.pa02.controller.FileTraverser;
import cs3500.pa02.model.ContentSynthesizer;
import cs3500.pa02.model.FileInfo;
import cs3500.pa02.model.OrderFlag;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for FileTraverser class.
 */
public class FileTraverserTest {
  static final String SAMPLE_INPUTS_DIRECTORY = "src/test/resources/inputs";

  // tests building a list of all valid Markdown paths in a directory
  @Test
  public void testGetSortedFiles() throws IOException {
    FileTraverser ft = new FileTraverser();

    assertThrows(IllegalStateException.class, () -> ft.getSortedFiles(OrderFlag.FILENAME));

    Files.walkFileTree(Path.of(SAMPLE_INPUTS_DIRECTORY), ft);
    ArrayList<FileInfo> actualFiles = ft.getSortedFiles(OrderFlag.FILENAME);

    // build list of expected FileInfo
    ArrayList<FileInfo> expectedFiles = new ArrayList<>();
    Path dir1 = Path.of(SAMPLE_INPUTS_DIRECTORY + "/arrays.md");
    BasicFileAttributes attrs1 = Files.readAttributes(dir1, BasicFileAttributes.class);
    String content1 = ContentSynthesizer.condenseLines(FileReader.readFileLine(dir1));

    Path dir2 = Path.of(SAMPLE_INPUTS_DIRECTORY + "/vectors.md");
    BasicFileAttributes attrs2 = Files.readAttributes(dir2, BasicFileAttributes.class);
    String content2 = ContentSynthesizer.condenseLines(FileReader.readFileLine(dir2));

    FileInfo file1 = new FileInfo(content1, "arrays.md",
        attrs1.creationTime(), attrs1.lastModifiedTime());
    FileInfo file2 = new FileInfo(content2, "vectors.md",
        attrs2.creationTime(), attrs2.lastModifiedTime());
    expectedFiles.add(file1);
    expectedFiles.add(file2);

    assertEquals(3, actualFiles.size());
    assertEquals(expectedFiles.get(0).content, actualFiles.get(0).content);
    assertEquals(expectedFiles.get(0).compareName(actualFiles.get(0)), 0);
    assertEquals(expectedFiles.get(0).compareCreated(actualFiles.get(0)), 0);
    assertEquals(expectedFiles.get(0).compareModified(actualFiles.get(0)), 0);

  }

}
