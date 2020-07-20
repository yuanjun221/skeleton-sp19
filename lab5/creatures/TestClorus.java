package creatures;

import huglife.*;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestClorus {

    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.move();
        assertEquals(1.94, c.energy(), 0.01);
        c.stay();
        assertEquals(1.93, c.energy(), 0.01);
        c.stay();
        assertEquals(1.92, c.energy(), 0.01);
    }

    @Test
    public void testAttack() {
        Clorus c = new Clorus(2);
        Plip p = new Plip(2);
        c.attack(p);
        assertEquals(4, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(1.85);
        Clorus offspring = c.replicate();
        assertEquals(1.85 / 2, offspring.energy(), 0.01);
        assertEquals(1.85 / 2, c.energy(), 0.01);
    }

    @Test
    public void testChoose() {
        // No empty nor plip adjacent spaces; stay.
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surround = new HashMap<>();
        surround.put(Direction.TOP, new Impassible());
        surround.put(Direction.BOTTOM, new Impassible());
        surround.put(Direction.LEFT, new Impassible());
        surround.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surround);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        // No empty space but with plip adjacent
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> topPlip = new HashMap<>();
        topPlip.put(Direction.TOP, new Plip(1.6));
        topPlip.put(Direction.BOTTOM, new Impassible());
        topPlip.put(Direction.LEFT, new Impassible());
        topPlip.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topPlip);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);

        assertEquals(expected, actual);

        // Energy >= 1, replicate towards an empty space.
        c = new Clorus(1);
        HashMap<Direction, Occupant> allEmpty = new HashMap<>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Empty());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(allEmpty);
        Action unexpected = new Action(Action.ActionType.STAY);

        assertNotEquals(unexpected, actual);

        // Otherwise, the Clorus will MOVE to a random empty square.
        c = new Clorus(0.9);
        actual = c.chooseAction(allEmpty);
        unexpected = new Action(Action.ActionType.STAY);

        assertNotEquals(unexpected, actual);

        c = new Clorus(0.99);
        actual = c.chooseAction(allEmpty);
        unexpected = new Action(Action.ActionType.STAY);
        assertNotEquals(unexpected, actual);
    }
}
