package cs3500.pa02.controller;

import static java.nio.file.StandardOpenOption.CREATE;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * responsible for reading and writing to files
 */
public class FileReader {
  /**
   * reads content from the given file into a list of its lines

   * @param file the file to read
   * @return the list of lines as an ArrayList of String
   */
  public static ArrayList<String> readFileLine(Path file) {
    Scanner input = null;
    ArrayList<String> lines = new ArrayList<>();

    try {
      input = new Scanner(file);
    } catch (IOException e) {
      e.printStackTrace();
    }

    while (input.hasNext()) {
      lines.add(input.nextLine());
    }

    return lines;
  }

  /**
   * Writes the given content into the given file

   * @param file the file to write to
   * @param content the content to write into the file
   * @throws IOException if the file path is invalid
   */
  public static void writeToFile(Path file, String content) throws IOException {
    Files.writeString(file, content, StandardCharsets.UTF_8, CREATE);
  }
}
