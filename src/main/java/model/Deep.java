package model;

import java.util.Objects;
import java.util.Stack;

public class Deep {
    private final Integer[][] map;

    public Deep(Integer[][] map) {
        this.map = map;
    }

    public Node getSolution() {
        long timeI = System.currentTimeMillis();
        int nodes = 0;
        Stack<Node> stack = new Stack<>();
        Node nodeInitial = new Node(map, null, 0, 0, 0, 0, Node.initialPlace(map));
        stack.push(nodeInitial);
        while (true) {
            if (!Objects.nonNull(stack.peek())) {
                return null;
            }
            Node node = stack.pop();
            if (node.getItem() > 1) {
                long timeF = System.currentTimeMillis();
                node.setTime((timeF - timeI)/1000.0);
                node.setNodes(nodes);
                return node;
            }
            nodes++;
            for (int i = 1; i < 5; i++) {
                if (node.possibleMove(i) && !node.isAncestor(i)) {
                    stack.push(node.nextNode(i));
                }
            }
        }
    }
}
