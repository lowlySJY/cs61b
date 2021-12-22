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
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {

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
    /**
     * fraction of energy to retain when replicating.
     */
    private double repEnergyRetained = 0.5;
    /**
     * fraction of energy to bestow upon offspring.
     */
    private double repEnergyGiven = 0.5;
    /**
     * probability of taking a move when see a "clorus" neighbor
     */
    private double moveProbability = 0.5;
    /**
     * the unit of energy lost when taking a move.
     */
    private double lostEnergyMove = 0.15;

    /**
     * creates plip with energy equal to E.
     */
    public Plip(double e) {
        super("plip");
        r = 99;
        g = 63;
        b = 76;
        energy = e;
        g += 96 * e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        return color(r, g, b);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        // TODO
        energy -= lostEnergyMove;
        energy = Math.max(energy, 0);
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        // TODO
        energy += 0.2;
        energy = Math.min(energy, 2);
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Plip replicate() {
        double babyEnergy = energy * repEnergyGiven;
        energy = energy * repEnergyRetained;
        return new Plip(babyEnergy);
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        boolean anyClorus = false;
        // TODO
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        for (Direction dir : neighbors.keySet()) {
            if (neighbors.get(dir).name().equals("empty")) {
                emptyNeighbors.add(dir);
            }
            if (neighbors.get(dir).name().equals("clorus")) {
                anyClorus = true;
            }
        }
        Random random = new Random();
        // Rule 1
        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        // Rule 2
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
        // Rule 3
        } else if (anyClorus && Math.random() < moveProbability) {
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
        // Rule 4
        } else {
            return new Action(Action.ActionType.STAY);
        }
    }
}
