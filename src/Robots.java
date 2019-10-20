public class Robots {

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public static class Robot {
        int x;
        int y;
        Direction dir;

        public Robot(int x, int y, Direction dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public Direction getDirection() {
            return dir;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void turnLeft() {
            if (dir == Direction.UP) {
                dir = Direction.LEFT;
            } else if (dir == Direction.DOWN) {
                dir = Direction.RIGHT;
            } else if (dir == Direction.LEFT) {
                dir = Direction.DOWN;
            } else if (dir == Direction.RIGHT) {
                dir = Direction.UP;
            }
        }

        public void turnRight() {
            if (dir == Direction.UP) {
                dir = Direction.RIGHT;
            } else if (dir == Direction.DOWN) {
                dir = Direction.LEFT;
            } else if (dir == Direction.LEFT) {
                dir = Direction.UP;
            } else if (dir == Direction.RIGHT) {
                dir = Direction.DOWN;
            }
        }

        public void stepForward() {
            if (dir == Direction.UP) {
                y++;
            }
            if (dir == Direction.DOWN) {
                y--;
            }
            if (dir == Direction.LEFT) {
                x--;
            }
            if (dir == Direction.RIGHT) {
                x++;
            }
        }

        public String getPosition() {
            return x + " " + y;
        }

        public void setPosition(int x, int y, String side) {
            this.x = x;
            this.y = y;
            this.dir = side.equals("UP") ? Direction.UP :
                    side.equals("DOWN") ? Direction.DOWN : side.equals("LEFT") ? Direction.LEFT : Direction.RIGHT;
        }
    }

    public static void moveRobot(Robot robot, int toX, int toY) {
        while (robot.getDirection() != Direction.RIGHT) robot.turnRight();
        while (robot.getX() < toX) robot.stepForward();
        robot.turnRight();
        while (robot.getY() > toY) robot.stepForward();
        robot.turnRight();
        while (robot.getX() > toX) robot.stepForward();
        robot.turnRight();
        while (robot.getY() < toY) robot.stepForward();
    }

    public interface RobotConnection extends AutoCloseable {
        void moveRobotTo(int x, int y);

        @Override
        void close();
    }

    public interface RobotConnectionManager {
        RobotConnection getConnection();
    }

    public static class RobotConnectionException extends RuntimeException {

        public RobotConnectionException(String message) {
            super(message);

        }

        public RobotConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {
        boolean success = false;
        for (int i = 0; !success && (i < 3); ++i) {
            try (RobotConnection connection = robotConnectionManager.getConnection()) {
                connection.moveRobotTo(toX, toY);
                success = true;
            } catch (RobotConnectionException e) {}
        }
        if (!success) {
            throw new RobotConnectionException("3 attempts failed");
        }
    }
}
