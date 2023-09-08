package cs3500.pa02.controller;

import static java.nio.file.FileVisitResult.CONTINUE;

import cs3500.pa02.model.ContentSynthesizer;
import cs3500.pa02.model.FileInfo;
import cs3500.pa02.model.OrderFlag;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * responsible for traversing through a file system and handling the
 * markdown files visited
 */
public class FileTraverser implements FileVisitor<Path> {
  private final ArrayList<FileInfo> files = new ArrayList<>();
  private final ArrayList<String> questions = new ArrayList<>();
  private final ArrayList<String> answers = new ArrayList<>();
  private boolean hasWalked = false;

  /**
   * side effect: sorts this FileTraverser's files
   *
   * @param order the order flag that specifies how the files should be sorted
   * @return the files sorted according to the order flag
   * @throws IllegalStateException if method is called before
   *                               any of the FileVisitor callback methods
   */
  public ArrayList<FileInfo> getSortedFiles(OrderFlag order) throws IllegalStateException {
    if (!hasWalked) {
      throw new IllegalStateException(
          "getSortedFiles cannot be called before any of the FileVisitor callback methods");
    }
    files.sort(order.fileInfoComparator);
    return files;
  }

  public ArrayList<String> getQuestions() {
    return questions;
  }

  public ArrayList<String> getAnswers() {
    return answers;
  }

  /**
   * @param dir   a reference to the directory
   * @param attrs the directory's basic attributes
   * @return CONTINUE continues to next file
   * @throws IOException for case where file path is not found
   */
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
    hasWalked = true;
    return CONTINUE;
  }

  /**
   * @param file  a reference to the file
   * @param attrs the file's basic attributes
   * @return CONTINUE continues to next file
   * @throws IOException for case where file path is not found
   */
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
    hasWalked = true;
    if (attrs.isRegularFile()) {
      String fileName = file.getFileName().toString();
      if (fileName.endsWith(".md")) {
        ArrayList<String> fileLines = FileReader.readFileLine(file);
        String content = ContentSynthesizer.condenseLines(fileLines);
        FileInfo fileInfo = new FileInfo(content, fileName,
            attrs.creationTime(), attrs.lastModifiedTime());
        files.add(fileInfo);
        ArrayList<String> qandAs = ContentSynthesizer.getQandAs();
        for (String s : qandAs) {
          int middle = s.indexOf(":::");
          questions.add(s.substring(0, middle));
          answers.add(s.substring(middle + 3));
        }
      }
    }
    return CONTINUE;
  }

  /**
   * @param file a reference to the file
   * @param exc  the I/O exception that prevented the file from being visited
   * @return CONTINUE continues to next file
   * @throws IOException for case where file path is not found
   */
  public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
    hasWalked = true;
    return CONTINUE;
  }

  /**
   * @param dir a reference to the directory
   * @param exc {@code null} if the iteration of the directory completes without
   *            an error; otherwise the I/O exception that caused the iteration
   *            of the directory to complete prematurely
   * @return CONTINUE continues to next file
   * @throws IOException to cover case where file path is not found
   */
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
    hasWalked = true;
    return CONTINUE;
  }
}
