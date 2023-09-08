package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa02.model.Difficulty;
import cs3500.pa02.model.Flashcard;
import cs3500.pa02.model.SessionInfo;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class SessionInfoTest {

  @Test
  public void testChangeDifficulty() {
    ArrayList<Flashcard> hardCards = new ArrayList<>();
    ArrayList<Flashcard> easyCards = new ArrayList<>();
    Flashcard f1 = new Flashcard("- What is the largest river in Africa?",
        "The largest river is the Nile River.", Difficulty.HARD);
    hardCards.add(f1);
    Flashcard f2 = new Flashcard("- What is the capital of Mexico? ",
        " The capital is Mexico City.", Difficulty.EASY);
    easyCards.add(f2);

    SessionInfo testInfo = new SessionInfo(hardCards, easyCards);
    testInfo.changeDiff(f1, Difficulty.EASY);
    testInfo.changeDiff(f2, Difficulty.HARD);
    assertEquals("- What is the largest river in Africa?",
        testInfo.getEasyCards().get(0).question);
    assertEquals("The largest river is the Nile River.",
        testInfo.getEasyCards().get(0).answer);


    assertEquals(testInfo.getHardCards().get(0).question,
        "- What is the capital of Mexico? ");
    assertEquals(testInfo.getHardCards().get(0).answer,
        " The capital is Mexico City.");

    assertEquals(testInfo.getTotalHard(), 1);
    assertEquals(testInfo.getTotalEasy(), 1);
    assertEquals(testInfo.getNewHard(), 1);
    assertEquals(testInfo.getNewEasy(), 1);
  }
}
