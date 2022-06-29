package model;

import java.util.Objects;
import java.util.PriorityQueue;

public class AStart {

    private final Integer[][] map;

    public AStart(Integer[][] map) {
        this.map = map;
    }

    public Node getSolution() {
        long timeI = System.currentTimeMillis();
        int nodes = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeCHComparator());
        Node nodeInitial = new Node(map, null, 0, 0, 0, 0, Node.initialPlace(map), Node.findGoals(map,2));
        pq.add(nodeInitial);
        while (true) {
            if (!Objects.nonNull(pq.peek())) {
                return null;
            }
            Node node = pq.poll();
            if (node.getItem() > 1) {
                long timeF = System.currentTimeMillis();
                node.setTime((timeF - timeI)/1000.0);
                node.setNodes(nodes);
                return node;
            }
            nodes++;
            for (int i = 1; i < 5; i++) {
                if (node.possibleMove(i)  && !node.isFather(i)) {
                    pq.add(node.nextNodeH(i));
                }
            }
        }
    }
}
