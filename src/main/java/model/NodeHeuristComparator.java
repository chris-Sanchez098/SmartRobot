package model;

import java.util.Comparator;

public class NodeHeuristComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        if (o1.getHeuristic() > o2.getHeuristic()) {
            return 1;
        } else if (o1.getHeuristic() < o2.getHeuristic()) {
            return -1;
        }
        return 0;
    }
}