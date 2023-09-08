package cs3500.pa02.model;

import cs3500.pa02.model.FileInfo;
import java.util.Comparator;

/**
 * represents the possible order flags for sorting files
 * each with its corresponding comparator as a field
 */
public enum OrderFlag {
  FILENAME((f1, f2) -> f1.compareName(f2)),
  CREATED((f1, f2) -> f1.compareCreated(f2)),
  MODIFIED((f1, f2) -> f1.compareModified(f2));

  public final Comparator<FileInfo> fileInfoComparator;

  OrderFlag(Comparator<FileInfo> fileInfoComparator) {
    this.fileInfoComparator = fileInfoComparator;
  }
}
