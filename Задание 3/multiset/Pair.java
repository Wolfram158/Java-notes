package multiset;

public class Pair {
    private Object key;
    private int count;

    public Pair(Object key, int count) {
        this.key = key;
        this.count = count;
    }

    public Object getKey() {
        return key;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return key + " : " + count;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Pair other = (Pair) obj;
        if ((this.key == null) ? (other.key != null) : !this.key.equals(other.key)) {
            return false;
        }

        if (this.key != other.key || this.count != other.count) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 239;
        hash += hash * this.getKey().hashCode() + 179 * this.getCount();
        return hash;
    }
}
