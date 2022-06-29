package model;

import java.util.ArrayDeque;
import java.util.Queue;

public class BFS {
    private final Integer[][] map;

    public BFS(Integer[][] map) {
        this.map = map;
    }

    public Node getSolution() {
        Queue<Node> queue = new ArrayDeque<>();
        Node nodeInitial = new Node(map, null, 0, 0, 0, 0, Node.initialPlace(map));
        queue.add(nodeInitial);
        while (true) {
            if (queue.isEmpty()) {
                return null;
            }
            Node node = queue.remove();
            if (node.getItem() == 2) {
                return node;
            }
            for (int i = 1; i < 5; i++) {
                if (node.possibleMove(i) && !node.isFather(i)) {
                    queue.add(node.nextNode(i));
                }
            }
        }
    }

}
