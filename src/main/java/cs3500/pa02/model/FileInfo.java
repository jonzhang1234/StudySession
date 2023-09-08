package cs3500.pa02.model;

import java.nio.file.attribute.FileTime;

/**
 * Responsible for storing and comparing relevant file information
 * and attributes
 */
public class FileInfo {
  public final String content;
  private final String fileName;
  private final FileTime dateCreated;
  private final FileTime dateModified;

  public FileInfo(String content, String fileName,
                  FileTime dateCreated, FileTime dateModified) {
    this.content = content;
    this.fileName = fileName;
    this.dateCreated = dateCreated;
    this.dateModified = dateModified;
  }

  /**
   * compares this FileInfo's name to the given FileInfo's name
   *
   * @param other the other file with name to be compared
   * @return 0 if this FileInfo's name is alphabetically equal to other's name, a value less
   *        than 0 if this FileInfo's name represents a name alphabetically that is before other's
   *        name, and a value greater than 0 if this FileInfo's name represents a name
   *        alphabetically after other's name
   */
  public int compareName(FileInfo other) {
    return this.fileName.compareTo(other.fileName);
  }

  /**
   * compares this FileInfo's dateCreated to the given FileInfo's dateCreated
   *
   * @param other the other file with date created to be compared
   * @return 0 if this FileInfo's created date is equal to other's date, a value less
   *        than 0 if this FileInfo's created date represents a time that is before other's date,
   *        and a value greater than 0 if this FileInfo's created date represents a time
   *        that is after other's date
   */
  public int compareCreated(FileInfo other) {
    return this.dateCreated.compareTo(other.dateCreated);
  }

  /**
   * compares this FileInfo's dateModified to the given FileInfo's dateModified
   *
   * @param other the other file with date modified to be compared
   * @return 0 if this FileInfo's modified date is equal to other's date, a value less
   *        than 0 if this FileInfo's modified date represents a time that is before other's date,
   *        and a value greater than 0 if this FileInfo's modified date represents a time
   *        that is after other's date
   */
  public int compareModified(FileInfo other) {
    return this.dateModified.compareTo(other.dateModified);
  }
}
