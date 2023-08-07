package trax;

import java.util.*;
import java.util.stream.Stream;

public class Configuration {
    List<Graph> graphs;
    Map<Integer, String> pointToColor;

    public Configuration() {
        graphs = new ArrayList<>();
        pointToColor = new HashMap<>();
    }

    public boolean add(final int x1, final int x2, final String color) {
        if (!graphs.isEmpty()) {
            if (!((!pointToColor.containsKey(x1) || pointToColor.get(x1).equals(color)) &&
                    (!pointToColor.containsKey(x2) || pointToColor.get(x2).equals(color)))) {
                System.out.println("Incorrect move.");
                return false;
            }
            for (int i = 0; i < graphs.size(); i++) {
                if (graphs.get(i).getColor().equals(color)) {
                    if (graphs.get(i).getLast() == x1 || graphs.get(i).getFirst() == x1 ||
                            graphs.get(i).getLast() == x2 || graphs.get(i).getFirst() == x2) {
                        graphs.set(i, graphs.get(i).add(x1, x2, color));
                        if (!pointToColor.containsKey(x1)) {
                            pointToColor.put(x1, color);
                        }
                        if (!pointToColor.containsKey(x2)) {
                            pointToColor.put(x2, color);
                        }
                        return true;
                    }
                }
            }
        }
        graphs.add(new Graph(x1, x2, color));
        return true;
    }

    public boolean hasCycle(final String color) {
        for (int i = 0; i < graphs.size(); i++) {
            if (graphs.get(i).getColor().equals(color) && graphs.get(i).isCycle()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Stream.of(graphs).map(x -> x.toString()).forEach(sb::append);
        return sb.toString();
    }
}
