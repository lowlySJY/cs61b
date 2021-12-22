package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Random;

/**
 * An implementation of Clorus, a fierce blue-colored predator that
 * enjoys nothing more than snacking on hapless Plips.
 * @author fox shawn
 */

public class Clorus extends Creature{
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    public Clorus(double e) {
        super("Clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    public Clorus() {this(1);}

    /**
     * The color() method for Cloruses should always
     * return the color red = 34, green = 0, blue = 231.
     */
    public Color color() {
        return color(r, g, b);
    }

    /**
     * If a Clorus attacks another creature, it should gain that creature’s energy.
     * This should happen in the attack() method, not in chooseAction(). You do not
     * need to worry about making sure the attacked creature dies—the simulator
     * does that for you.
     */
    public void attack(Creature c) {
        energy += c.energy();
    }

    /**
     * Cloruses should lose 0.03 units of energy on a MOVE action.
     */
    public void move() {
        energy -= 0.03;
    }

    /**
     * Cloruses should lose 0.01 units of energy on a STAY action.
     */
    public void stay() {
        energy += 0.01;
    }

    /**
     * Like a Plip, when a Clorus replicates, it keeps 50% of its energy.
     * The other 50% goes to its offspring. No energy is lost in the replication
     * process.
     */
    public Clorus replicate() {
        energy = energy * 0.5;
        return new Clorus(energy);
    }

    /**
     * Cloruses should obey exactly the following behavioral rules:
     * If there are no empty squares, the Clorus will STAY (even if there are Plips
     * nearby they could attack since plip squares do not count as empty squares).
     * Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     * Otherwise, if the Clorus has energy greater than or equal to one, it will
     * REPLICATE to a random empty square.
     * Otherwise, the Clorus will MOVE to a random empty square.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // record "empty" direction in emptyNeighbors
        // record "plip" direction in plipNeighbors
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        for (Direction dir : neighbors.keySet()) {
            if (neighbors.get(dir).name().equals("empty")) {
                emptyNeighbors.add(dir);
            }
            if (neighbors.get(dir).name().equals("plip")) {
                plipNeighbors.add(dir);
            }
        }
        Random random = new Random();
        // Rule 1
        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        } else if (plipNeighbors.size() != 0) {
            int randomInt = random.nextInt(plipNeighbors.size());
            Deque<Direction> pickDir = plipNeighbors;
            if (randomInt == 0) {
                return new Action(Action.ActionType.ATTACK, pickDir.getFirst());
            } else {
                for (int i=0; i < randomInt; i++) {
                    pickDir.removeFirst();
                }
                return new Action(Action.ActionType.ATTACK, pickDir.getFirst());
            }
        // Rule 3
        } else if (energy >= 1) {
            int randomInt = random.nextInt(emptyNeighbors.size());
            Deque<Direction> pickDir = emptyNeighbors;
            if (randomInt == 0) {
                return new Action(Action.ActionType.REPLICATE, pickDir.getFirst());
            } else {
                for (int i=0; i < randomInt; i++) {
                    pickDir.removeFirst();
                }
                return new Action(Action.ActionType.REPLICATE, pickDir.getFirst());
            }
        // Rule 4
        } else {
            int randomInt = random.nextInt(emptyNeighbors.size());
            Deque<Direction> pickDir = emptyNeighbors;
            if (randomInt == 0) {
                return new Action(Action.ActionType.MOVE, pickDir.getFirst());
            } else {
                for (int i=0; i < randomInt; i++) {
                    pickDir.removeFirst();
                }
                return new Action(Action.ActionType.MOVE, pickDir.getFirst());
            }
        }
    }
}
