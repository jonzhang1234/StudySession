package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa02.controller.StudySession;
import cs3500.pa02.model.Difficulty;
import cs3500.pa02.model.Flashcard;
import cs3500.pa02.view.SessionViewer;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SessionViewerTest {
  private SessionViewer view;

  @BeforeEach
  public void setUp() {
    view = new SessionViewer();
  }

  @Test
  public void testWelcomeUser() throws IOException {
    Path sampleInput = Path.of("src/test/resources/inputs/sampleInput1.txt");
    Scanner in = new Scanner(sampleInput);
    StudySession session = SessionViewer.welcomeUser(in);
    assertEquals(session.file, Path.of("src/test/resources/outputs/test3.sr"));
    assertEquals(session.questions, 10);
  }

  @Test
  public void testPrintQuestion() throws IOException {
    Path sampleInput = Path.of("src/test/resources/inputs/sampleInput2.txt");
    Scanner in = new Scanner(sampleInput);
    assertEquals(view.printQuestion("- What is the largest river in Africa?", in),
        3);
  }
}
