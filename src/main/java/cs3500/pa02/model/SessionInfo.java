package cs3500.pa02.model;

import cs3500.pa02.model.Difficulty;
import cs3500.pa02.model.Flashcard;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Responsible for storing and changing info from each study session
 */
public class SessionInfo {
  private final ArrayList<Flashcard> hardCards;
  private final ArrayList<Flashcard> easyCards;
  int numAnswered;
  int newHard;
  int newEasy;
  int totalHard;
  int totalEasy;

  /**
   * Constructor for SessionInfo made for each session

   * @param hardCards the list of cards labeled as hard
   * @param easyCards the list of cards labeled as easy
   */
  public SessionInfo(ArrayList<Flashcard> hardCards, ArrayList<Flashcard> easyCards) {
    this.hardCards = hardCards;
    this.easyCards = easyCards;
    this.totalHard = this.hardCards.size();
    this.totalEasy = this.easyCards.size();
    this.newHard = 0;
    this.newEasy = 0;
    this.numAnswered = 0;
  }

  /**
   * Organizes and shuffles required number of cards into a list with hard
   * cards coming before easy

   * @param questions number of questions entered for the session
   * @return the list of cards needed for the current session
   */
  public ArrayList<Flashcard> getSessionCards(int questions) {
    ArrayList<Flashcard> sessionCards = new ArrayList<>();
    ArrayList<Flashcard> shuffledHard = hardCards;
    Collections.shuffle(shuffledHard);
    ArrayList<Flashcard> shuffledEasy = easyCards;
    Collections.shuffle(shuffledEasy);
    if (questions > totalHard + totalEasy) {
      questions = totalHard + totalEasy;
    }
    for (int i = 0; i < questions; i += 1) {
      if (i < totalHard) {
        sessionCards.add(shuffledHard.get(i));
      } else {
        sessionCards.add(shuffledEasy.get(i - totalHard));
      }
    }
    return sessionCards;
  }

  /**
   * changes the difficulty of the given FlashCard

   * @param f the flashcard to change
   * @param diff the difficulty to change to
   */
  public void changeDiff(Flashcard f, Difficulty diff) {
    if (f.difficulty != diff) {
      f.difficulty = diff;
      if (diff.equals(Difficulty.HARD))  {
        easyCards.remove(f);
        hardCards.add(f);
        newHard += 1;
        totalEasy -= 1;
        totalHard += 1;
      } else {
        hardCards.remove(f);
        easyCards.add(f);
        newEasy += 1;
        totalHard -= 1;
        totalEasy += 1;
      }

    }
  }

  public void addNumAnswered() {
    this.numAnswered += 1;
  }

  public int getNumAnswered() {
    return numAnswered;
  }

  public int getNewHard() {
    return newHard;
  }

  public int getNewEasy() {
    return newEasy;
  }

  public int getTotalHard() {
    return totalHard;
  }

  public int getTotalEasy() {
    return totalEasy;
  }

  public ArrayList<Flashcard> getHardCards() {
    return hardCards;
  }

  public ArrayList<Flashcard> getEasyCards() {
    return easyCards;
  }
}
