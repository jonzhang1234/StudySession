package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa02.controller.CardUpdater;
import cs3500.pa02.controller.FileReader;
import cs3500.pa02.model.ContentSynthesizer;
import cs3500.pa02.model.Difficulty;
import cs3500.pa02.model.Flashcard;
import cs3500.pa02.model.SessionInfo;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CardUpdaterTest {
  private Path testFile;
  private ArrayList<Flashcard> hardCards;
  private ArrayList<Flashcard> easyCards;

  @BeforeEach
  public void setUp() {
    hardCards = new ArrayList<>();
    easyCards = new ArrayList<>();
    testFile = Path.of("src/test/resources/outputs/test1.sr");
    hardCards.add(new Flashcard("- What is the largest river in Africa?",
        "The largest river is the Nile River.", Difficulty.HARD));
    hardCards.add(new Flashcard("- What is the capital of Argentina?",
        " The capital is Buenos Aires.", Difficulty.HARD));

    easyCards.add(new Flashcard("- What is the capital of Mexico? ",
        " The capital is Mexico City.", Difficulty.EASY));
    easyCards.add(new Flashcard("- What is the largest desert in Asia?",
        "The largest desert is the Gobi Desert.", Difficulty.EASY));
    easyCards.add(new Flashcard("- Which continent is known as the \"Roof of the World\"?",
        "Asia.", Difficulty.EASY));

  }

  @Test
  public void testReadCardData() {
    SessionInfo info = CardUpdater.readCardData(testFile);
    ArrayList<Flashcard> actualHard = info.getHardCards();
    ArrayList<Flashcard> actualEasy = info.getEasyCards();
    for (int i = 0; i < 2; i += 1) {
      assertEquals(actualHard.get(i).question, hardCards.get(i).question);
    }

    for (int i = 0; i < 3; i += 1) {
      assertEquals(actualEasy.get(i).question, easyCards.get(i).question);
    }
  }

  @Test
  public void testWriteMetaData() throws IOException {
    SessionInfo testInfo = new SessionInfo(hardCards, easyCards);
    Path test2File = Path.of("src/test/resources/outputs/test2.sr");
    CardUpdater.writeMetaData(test2File, testInfo);
    assertArrayEquals(FileReader.readFileLine(test2File).toArray(),
        FileReader.readFileLine(testFile).toArray());
  }
}
