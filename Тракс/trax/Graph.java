package trax;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    List<Integer> vertices;
    final private String color;

    public Graph() {
        vertices = new ArrayList<>();
        this.color = null;
    }

    public Graph(final int x1, final int x2, final String color) {
        vertices = new ArrayList<>();
        vertices.add(x1);
        vertices.add(x2);
        this.color = color;
    }

    public boolean isCycle() {
        return vertices.get(0) == vertices.get(vertices.size() - 1);
    }

    public Graph add(final int x1, final int x2, final String color) {
        if (!this.color.equals(color)) {
            System.out.println("Incorrect color.");
        } else {
            if (x1 == vertices.get(0)) {
                vertices.add(0, x1);
                vertices.add(0, x2);
            } else if (x1 == vertices.get(vertices.size() - 1)) {
                vertices.add(x1);
                vertices.add(x2);
            } else if (x2 == vertices.get(0)) {
                vertices.add(0, x2);
                vertices.add(0, x1);
            } else if (x2 == vertices.get(vertices.size() - 1)) {
                vertices.add(x2);
                vertices.add(x1);
            } else {
                System.out.println("Incorrect edge.");
                return new Graph();
            }
        }
        return this;
    }

    public String getColor() {
        return color;
    }

    public int getFirst() {
        return vertices.get(0);
    }

    public int getLast() {
        return vertices.get(vertices.size() - 1);
    }

    public boolean contains(final int x) {
        return vertices.contains(x);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Color: ").append(color).append("\n").append("Edges: ");
        for (int i = 0; i < vertices.size() - 1; i++) {
            if (vertices.get(i) != vertices.get(i + 1)) {
                sb.append("(").append(vertices.get(i)).append(", ").append(vertices.get(i + 1)).append(") ");
            }
        }
        sb.append("\n");
        return sb.toString();
    }
}
