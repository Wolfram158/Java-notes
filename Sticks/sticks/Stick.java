package sticks;

public class Stick {
    Point point1;
    Point point2;

    public Stick(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Stick other = (Stick) obj;

        if (!(this.point1.equals(other.point1))) {
            return this.point2.equals(other.point1) && this.point1.equals(other.point2);
        }

        return this.point2.equals(other.point2);
    }

    @Override
    public int hashCode() {
        int hash = 239;
        hash += hash * point1.hashCode() + 179 * point2.hashCode();
        return hash;
    }
}
