package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa02.controller.CardUpdater;
import cs3500.pa02.controller.FileReader;
import cs3500.pa02.controller.StudySession;
import cs3500.pa02.model.Difficulty;
import cs3500.pa02.model.Flashcard;
import cs3500.pa02.model.SessionInfo;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudySessionTest {
  private StudySession session;
  private Path sampleInput;
  private Path out;
  private ArrayList<Flashcard> hardCards;
  private ArrayList<Flashcard> easyCards;

  @BeforeEach
  public void setUp() throws IOException {
    sampleInput = Path.of("src/test/resources/inputs/sampleInput3.txt");
    out = Path.of("src/test/resources/outputs/test3.sr");

    hardCards = new ArrayList<>();
    easyCards = new ArrayList<>();
    hardCards.add(new Flashcard("- What is the largest river in Africa?",
        "The largest river is the Nile River.", Difficulty.HARD));

    easyCards.add(new Flashcard("- What is the capital of Mexico? ",
        " The capital is Mexico City.", Difficulty.EASY));
    easyCards.add(new Flashcard("- What is the largest desert in Asia?",
        "The largest desert is the Gobi Desert.", Difficulty.EASY));

    SessionInfo testInfo = new SessionInfo(hardCards, easyCards);

    CardUpdater.writeMetaData(out, testInfo);
  }

  @Test
  public void testSession() throws IOException {
    session = new StudySession("src/test/resources/outputs/test3.sr", 10);
    Scanner in = new Scanner(sampleInput);
    session.startSession(in);

    assertArrayEquals(FileReader.readFileLine(out).toArray(),
        FileReader.readFileLine(Path.of("src/test/resources/outputs/expected3.sr")).toArray());
  }
}
