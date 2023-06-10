package multiset;

public class Main {
    public static void main(String[] args) {
        Multiset mul1 = new Multiset();
        Multiset mul2 = new Multiset();
        mul1.add(mul2);
        mul1.add(mul2);
        System.out.println(mul1);
        mul1.add(3);
        mul1.add(100);
        mul1.add(2);
        mul1.add(3);
        mul2.add(4);
        mul2.add(4);
        mul2.add(3);
        System.out.println(mul1);
        System.out.println(mul2);
        System.out.println(intersect(mul1, mul2));
        System.out.println(union(mul1, mul2));
    }

    public static Multiset intersect(Multiset a, Multiset b) {
        int pointerA = 0;
        int pointerB = 0;
        Multiset result = new Multiset();
        while (pointerA < a.getCount() && pointerB < b.getCount()) {
            if (a.multiset.get(pointerA).getKey().hashCode() < b.multiset.get(pointerB).getKey().hashCode()) {
                pointerA++;
            } else if (a.multiset.get(pointerA).getKey().hashCode() > b.multiset.get(pointerB).getKey().hashCode()) {
                pointerB++;
            } else {
                result.add(a.multiset.get(pointerA).getKey(), Math.min(a.multiset.get(pointerA).getCount(),
                        b.multiset.get(pointerB).getCount()));
                pointerA++;
                pointerB++;
            }
        }
        return result;
    }

    public static Multiset union(Multiset a, Multiset b) {
        int pointerA = 0;
        int pointerB = 0;
        Multiset result = new Multiset();
        while (pointerA < a.getCount() && pointerB < b.getCount()) {
            if (a.multiset.get(pointerA).getKey().hashCode() < b.multiset.get(pointerB).getKey().hashCode()) {
                result.add(a.multiset.get(pointerA).getKey(), a.multiset.get(pointerA).getCount());
                pointerA++;
            } else if (a.multiset.get(pointerA).getKey().hashCode() > b.multiset.get(pointerB).getKey().hashCode()) {
                result.add(b.multiset.get(pointerB).getKey(), b.multiset.get(pointerB).getCount());
                pointerB++;
            } else {
                result.add(a.multiset.get(pointerA).getKey(), Math.max(a.multiset.get(pointerA).getCount(),
                        b.multiset.get(pointerB).getCount()));
                pointerA++;
                pointerB++;
            }
        }
        while (pointerA < a.getCount()) {
            result.add(a.multiset.get(pointerA).getKey(), a.multiset.get(pointerA).getCount());
            pointerA++;
        }
        while (pointerB < b.getCount()) {
            result.add(b.multiset.get(pointerB).getKey(), b.multiset.get(pointerB).getCount());
            pointerB++;
        }
        return result;
    }
}
