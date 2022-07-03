package model;

import java.util.ArrayDeque;
import java.util.Queue;

public class Breadth {
    private final Integer[][] map;

    public Breadth(Integer[][] map) {
        this.map = map;
    }

    public Node getSolution() {
        long timeI = System.currentTimeMillis();
        int nodes = 0;
        Queue<Node> queue = new ArrayDeque<>();
        Node nodeInitial = new Node(map ,Node.initialPlace(map), null);
        queue.add(nodeInitial);
        while (true) {
            if (queue.isEmpty()) {
                return null;
            }
            Node node = queue.remove();
            if (node.getItem() == 2) {
                long timeF = System.currentTimeMillis();
                node.setTime((timeF - timeI)/1000.0);
                node.setNodes(nodes);
                return node;
            }
            nodes++;
            for (int i = 1; i < 5; i++) {
                if (node.possibleMove(i) && !node.isFather(i)) {
                    queue.add(new Node(node, i));
                }
            }
        }
    }

}
