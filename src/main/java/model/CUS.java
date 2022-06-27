package model;

import java.util.Objects;
import java.util.PriorityQueue;

public class CUS {
    private final Integer[][] map;

    public CUS(Integer[][] map) {
        this.map = map;
    }

    public Node getSolution() {
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeCostComparator());
        Node nodeInitial = new Node(map, null, 0, 0, 0, 0, Node.initialPlace(map));
        pq.add(nodeInitial);
        while (true) {
            if (!Objects.nonNull(pq.peek())) {
                return null;
            }
            Node node = pq.poll();
            if (node.getItem() > 1) {
                return node;
            }
            for (int i = 1; i < 5; i++) {
                if (node.possibleMove(i) && !node.isFather(i)) {
                    pq.add(new Node(node.nextMap(i), node, node.getDeep() + 1, node.nextCost(i),
                            node.nextItem(i), node.nextNav(i), node.nextPlace(i)));
                }
            }
        }
    }
}

