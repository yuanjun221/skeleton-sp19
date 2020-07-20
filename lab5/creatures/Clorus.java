package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {
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
     * creates clorus with energy equal to E.
     */
    public Clorus(double e) {
        super("Clours");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    /**
     * creates a clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    @Override
    public void move() {
        if (energy - 0.03 < 0) {
            energy = 0;
        } else {
            energy -= 0.03;
        }
    }

    @Override
    public void attack(Creature c) {
        this.energy += c.energy();
    }

    @Override
    public Clorus replicate() {
        this.energy = this.energy / 2;
        return new Clorus(this.energy);
    }

    @Override
    public void stay() {
        if (energy - 0.01 < 0) {
            energy = 0;
        } else {
            energy -= 0.01;
        }
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {

        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plips = new ArrayDeque<>();

        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
            if (entry.getValue().name().equals("empty")) {
                emptyNeighbors.add(entry.getKey());
            }
            if (entry.getValue().name().equals("plip")) {
                plips.add(entry.getKey());
            }
        }

        // Rule 1 : If there are no empty spaces, the clorus should STAY.
        if (emptyNeighbors.isEmpty() && plips.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2 : Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
        if (!plips.isEmpty()) {
            Direction randomDirection = randomEntry(plips);
            return new Action(Action.ActionType.ATTACK, randomDirection);
        }

        // Rule 3: Otherwise, if the Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square.
        if (this.energy >= 1.0) {
            Direction randomDirection = randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, randomDirection);
        }

        Direction randomDirection = randomEntry(emptyNeighbors);

        return new Action(Action.ActionType.MOVE, randomDirection);
    }

    private Direction randomEntry(Deque<Direction> neighbors) {
        int num = (int) (Math.random() * neighbors.size());
        return (Direction) neighbors.toArray()[num];
    }

    @Override
    public Color color() {
        return color(r, g, b);
    }
}
