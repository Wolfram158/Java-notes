package sticks;

public class Point<T> {
    private final T x;
    private final T y;

    Point (T x, T y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Point other = (Point) obj;

        return (this.x.equals(other.x)) && (this.y.equals(other.y));
    }

    @Override
    public int hashCode() {
        int hash = 239;
        hash += hash * x.hashCode() + 179 * y.hashCode();
        return hash;
    }
}
