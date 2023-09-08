package cs3500.pa02.model;

import cs3500.pa02.model.Difficulty;

public class Flashcard {
  public final String question;
  public final String answer;
  Difficulty difficulty;

  public Flashcard(String q, String a, Difficulty d) {
    question = q;
    answer = a;
    difficulty = d;
  }


}
