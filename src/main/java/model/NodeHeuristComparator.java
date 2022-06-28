package model;

import java.util.Comparator;

public class NodeHeuristComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        if (o1.getHeurist() > o2.getHeurist()) {
            return 1;
        } else if (o1.getHeurist() < o2.getHeurist()) {
            return -1;
        }
        return 0;
    }
}