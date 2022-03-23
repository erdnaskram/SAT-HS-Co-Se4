package U1_Refactoring.VierGewinnt.src;

import java.io.*;
import org.junit.jupiter.api.Test;


public class VierGewinntTest {

    @Test
	public void testIsOverRow() throws Exception {
        assertOutcome("Spieler mit ohat gewonnen", 8,7,1, 1, 2, 2, 3, 3, 4);
	}

    @Test
    public void testIsOverStraight2() throws Exception {
        assertOutcome("Spieler mit ohat gewonnen", 8,7,2,3,3,5,4,4,5,5,5,6,4);
    }

    @Test
	public void testIsOverStraight1() throws Exception {
        assertOutcome("Spieler mit ohat gewonnen", 8,7,5,4,3,3,4,2,2,2,2,1,3);
	}

    @Test
	public void testUnentschieden() throws Exception {
		assertOutcome("Unentschieden!", 8,7,1,2,1,2,1,2,2,1,2,1,2,1,1,2,3,4,3,4,3,4,4,3,4,3,4,3,3,4,5,6,5,
                6,5,6,6,5,6,5,6,5,5,6,7,8,7,8,7,8,8,7,8,7,8,7,7,8);
	}

    @Test
    public void testIsOverColum() throws Exception {
        assertOutcome("Spieler mit ohat gewonnen", 8,7,6,5,5,6,5,6,5,6,5);
    }

    private void assertOutcome(String expectedOutcome, int width, int height, int... choices) {
        final StringBuilder sb = new StringBuilder("x\ny\n" + width + "\n" + height + "\n");
        for (int choice: choices) {
            sb.append(choice);
            sb.append("\n");
        }
        final InputStream in = new ByteArrayInputStream(sb.toString().getBytes());
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final ByteArrayOutputStream err = new ByteArrayOutputStream();
        final VierGewinnt game = new VierGewinnt(in, new PrintStream(out), new PrintStream(err));
        game.play();
        final String output = out.toString();
        assertTrue("Incorrect output: " + output,  output.contains(expectedOutcome));
    }

}
