package model;

import java.util.Objects;
import java.util.Stack;

public class DFS {
    private final Integer[][] map;

    public DFS(Integer[][] map) {
        this.map = map;
    }

    public Node getSolution() {
        Stack<Node> stack = new Stack<>();
        Node nodeInitial = new Node(map, null, 0, 0, 0, 0, Node.initialPlace(map));
        stack.push(nodeInitial);
        while (true) {
            if (!Objects.nonNull(stack.peek())) {
                return null;
            }
            Node node = stack.pop();
            if (node.getItem() > 1) {
                return node;
            }
            for (int i = 1; i < 5; i++) {
                if (node.possibleMove(i) && !node.isAncestor(i)) {
                    stack.push(node.nextNode(i));
                }
            }
        }
    }
}
