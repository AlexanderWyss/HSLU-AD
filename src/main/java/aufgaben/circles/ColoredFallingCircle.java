package aufgaben.circles;

import aufgaben.shape.Circle;
import aufgaben.shape.Point;

import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ColoredFallingCircle extends Circle implements Runnable {
    private Color color;
    private final int fallingSpeed;
    private Predicate<Circle> checkDyingPredicate;
    private Consumer<Circle> onDead;

    private ColoredFallingCircle(Point position, int diameter, Color color, int fallingSpeed, Predicate<Circle> checkDyingPredicate, Consumer<Circle> onDead) {
        super(position, diameter);
        this.color = color;
        this.fallingSpeed = fallingSpeed;
        this.checkDyingPredicate = checkDyingPredicate;
        this.onDead = onDead;
    }

    @Override
    public void run() {
        while (getDiameter() > 0 && !Thread.currentThread().isInterrupted()) {
            if (checkDyingPredicate.test(this)) {
                setDiameter(getDiameter() - 2);
                getPosition().moveRelative(1, 2);
                color = color.brighter();
                color = new Color(color.getRed(), color.getGreen(), color.getBlue(), Math.max(color.getAlpha() - 20, 0));
            } else {
                getPosition().moveRelative(0, fallingSpeed);
            }
            try {
                //noinspection BusyWait
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        onDead.accept(this);
    }

    public Color getColor() {
        return color;
    }

    public boolean isDead() {
        return getDiameter() <= 0;
    }

    public static class ColoredFallingCircleBuilder {
        private int diameter;
        private Color color;
        private int fallingSpeed;
        private Point position;
        private Predicate<Circle> checkDyingPredicate;
        private Consumer<Circle> onDead;

        public ColoredFallingCircleBuilder setPosition(Point position) {
            this.position = position;
            return this;
        }

        public ColoredFallingCircleBuilder setDiameter(int diameter) {
            this.diameter = diameter;
            return this;
        }

        public ColoredFallingCircleBuilder setColor(Color color) {
            this.color = color;
            return this;
        }

        public ColoredFallingCircleBuilder setFallingSpeed(int fallingSpeed) {
            this.fallingSpeed = fallingSpeed;
            return this;
        }

        public ColoredFallingCircleBuilder setCheckDying(Predicate<Circle> checkDyingPredicate) {
            this.checkDyingPredicate = checkDyingPredicate;
            return this;
        }

        public ColoredFallingCircleBuilder setOnDead(Consumer<Circle> onDead) {
            this.onDead = onDead;
            return this;
        }

        public ColoredFallingCircle build() {
            return new ColoredFallingCircle(position, diameter, color, fallingSpeed, checkDyingPredicate, onDead);
        }
    }
}
