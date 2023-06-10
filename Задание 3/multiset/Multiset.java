package multiset;

import java.util.LinkedList;
import java.util.List;

public class Multiset {
    List<Pair> multiset = new LinkedList<>();

    public Multiset() {
        this.multiset = multiset;
    }

    public void add(Object element) {
        if (multiset.size() == 0) {
            multiset.add(0, new Pair(element, 1));
        } else {
            boolean signal = true;
            for (int i = 0; i < multiset.size(); i++) {
                if (element.hashCode() < multiset.get(i).getKey().hashCode()) {
                    multiset.add(i, new Pair(element, 1));
                    signal = false;
                    break;
                } else if (element.equals(multiset.get(i).getKey())) {
                    multiset.set(i, new Pair(multiset.get(i).getKey(), multiset.get(i).getCount() + 1));
                    signal = false;
                    break;
                }
            }
            if (signal) {
                multiset.add(multiset.size(), new Pair(element, 1));
            }
        }
    }

    public void add(Object element, int count) {
        if (multiset.size() == 0) {
            multiset.add(0, new Pair(element, count));
        } else {
            boolean signal = true;
            for (int i = 0; i < multiset.size(); i++) {
                if (element.hashCode() < multiset.get(i).getKey().hashCode()) {
                    multiset.add(i, new Pair(element, count));
                    signal = false;
                    break;
                } else if (element.equals(multiset.get(i).getKey())) {
                    multiset.set(i, new Pair(multiset.get(i).getKey(), multiset.get(i).getCount() + count));
                    signal = false;
                    break;
                }
            }
            if (signal) {
                multiset.add(multiset.size(), new Pair(element, count));
            }
        }
    }

    public boolean remove(Object element) {
        boolean signal = false;
        for (int i = 0; i < multiset.size(); i++) {
            if (element.equals(multiset.get(i).getKey())) {
                if (multiset.get(i).getCount() == 1) {
                    multiset.remove(i);
                } else {
                    multiset.set(i, new Pair(multiset.get(i).getKey(), multiset.get(i).getCount() - 1));
                }
                signal = true;
            }
        }
        return signal;
    }

    public int getCount() {
        return multiset.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Multiset other = (Multiset) obj;

        if (this.getCount() != other.getCount()) {
            return false;
        }

        for (int i = 0; i < this.multiset.size(); i++) {
            if (!this.multiset.get(i).equals(other.multiset.get(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 179;
        for (int i = 0; i < this.getCount(); i++) {
            hash += this.multiset.get(i).hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        for (int i = 0; i < multiset.size() - 1; i++) {
            sb.append(multiset.get(i).toString()).append(", ");
        }
        if (multiset.size() > 0) {
            sb.append(multiset.get(multiset.size() - 1).toString());
        }
        sb.append(" ]");
        return sb.toString();
    }
}
