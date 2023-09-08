package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa02.model.OrderFlag;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for OrderFlagTest class.
 */
public class OrderFlagTest {
  /**
   * Represents tests for OrderFlag enum
   */
  @Test
  public void testFlags() {
    OrderFlag filename = OrderFlag.FILENAME;
    OrderFlag created = OrderFlag.CREATED;
    OrderFlag modified = OrderFlag.MODIFIED;

    String n = "filename";
    String c = "created";
    String m = "modified";

    assertEquals(OrderFlag.valueOf(n.toUpperCase()), filename);
    assertEquals(OrderFlag.valueOf(c.toUpperCase()), created);
    assertEquals(OrderFlag.valueOf(m.toUpperCase()), modified);
  }

}
