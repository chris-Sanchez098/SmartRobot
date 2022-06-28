package model;

import java.util.Comparator;

public class NodeCHComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        if (o1.sumCH() > o2.sumCH()) {
            return 1;
        } else if (o1.sumCH() < o2.sumCH()) {
            return -1;
        }
        return 0;
    }
}
