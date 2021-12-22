package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {
    @Test
    public void TestBasic() {
        Clorus c = new Clorus(1);
        assertEquals(1, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(0.97, c.energy(), 0.01);
        c.move();
        assertEquals(0.94, c.energy(), 0.01);
        c.stay();
        assertEquals(0.95, c.energy(), 0.01);
        c.stay();
        assertEquals(0.96, c.energy(), 0.01);
        Plip p = new Plip(1);
        c.attack(p);
        assertEquals(1.96, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(1);
        Clorus babyC = c.replicate();
        assertEquals(0.5, c.energy(), 0.01);
        assertEquals(0.5, babyC.energy(), 0.01);
        Clorus c1 = new Clorus(1.2);
        assertNotEquals(c1, c1.replicate());
    }

    @Test
    public void testChoose() {
        //If there are no empty squares, the Clorus will STAY (even if there are Plips
        //nearby they could attack since plip squares do not count as empty squares).
        Clorus c = new Clorus(1);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());
        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);

        //if any Plips are seen, the Clorus will ATTACK one of them randomly.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> TopPilps = new HashMap<Direction, Occupant>();
        TopPilps.put(Direction.TOP, new Plip());
        TopPilps.put(Direction.BOTTOM, new Impassible());
        TopPilps.put(Direction.LEFT, new Impassible());
        TopPilps.put(Direction.RIGHT, new Empty());
        actual = c.chooseAction(TopPilps);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);
        assertEquals(expected, actual);

        HashMap<Direction, Occupant> allPilps = new HashMap<Direction, Occupant>();
        allPilps.put(Direction.TOP, new Plip());
        allPilps.put(Direction.BOTTOM, new Plip());
        allPilps.put(Direction.LEFT, new Plip());
        allPilps.put(Direction.RIGHT, new Plip());
        actual = c.chooseAction(allPilps);
        expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);

//        Otherwise, if the Clorus has energy greater than or equal to one, it will
//        REPLICATE to a random empty square.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> RightEmpty = new HashMap<Direction, Occupant>();
        RightEmpty.put(Direction.TOP, new Impassible());
        RightEmpty.put(Direction.BOTTOM, new Impassible());
        RightEmpty.put(Direction.LEFT, new Impassible());
        RightEmpty.put(Direction.RIGHT, new Empty());
        actual = c.chooseAction(RightEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.RIGHT);
        assertEquals(expected, actual);

//        Otherwise, the Clorus will MOVE to a random empty square.
        c = new Clorus(0.8);
        actual = c.chooseAction(RightEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.RIGHT);
        assertEquals(expected, actual);
    }
}
