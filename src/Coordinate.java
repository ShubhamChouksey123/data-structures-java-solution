public class Coordinate {

    private Integer x;
    private Integer y;

    private Integer dist;

    public Coordinate() {
    }

    public Coordinate(Integer x, Integer y, Integer dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getDist() {
        return dist;
    }

    public void setDist(Integer dist) {
        this.dist = dist;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                ", dist=" + dist +
                '}';
    }
}
