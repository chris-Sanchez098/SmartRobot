package model;

import java.util.Objects;
import java.util.PriorityQueue;

public class Greedy {
    private final Integer[][] map;

    public Greedy(Integer[][] map) {
        this.map = map;
    }

    public Node getSolution() {
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeHeuristComparator());
        Node nodeInitial = new Node(map, null, 0, 0, 0, 0, Node.initialPlace(map), Node.findGoals(map,2));
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
                if (node.possibleMove(i) && !node.isAncestor(i)) {
                    pq.add(node.nextNodeH(i));
                }
            }
        }
    }
}
