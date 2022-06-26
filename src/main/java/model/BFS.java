package model;

import java.util.ArrayDeque;
import java.util.Queue;

public class BFS {
    private final Integer[][] map;

    public BFS(Integer[][] map) {
        this.map = map;
    }

    private Integer[] getPlace() {
        if (map == null) {
            return null;
        }
        Integer[] place = new Integer[2];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map[i][j] == 2) {
                    place[0] = i;
                    place[1] = j;
                }
            }
        }
        return place;
    }

    public void getSolution() {
        int i = 0;
        Queue<Node> queue = new ArrayDeque<>();
        Node nodeInitial = new Node(map, null, 0, 0, 0, 0, getPlace());
        queue.add(nodeInitial);
        while (true) {
            if (queue.isEmpty()) {
                System.out.println("Not found solution");
                break;
            }
            Node actual = queue.remove();
            if (actual.getItem() == 2) {
                System.out.println("Meta");
                actual.viewMap();
                System.out.println("Profundidad: " + actual.getDeep());
                System.out.println("costo: " + actual.getCost());
                break;
            } else {
                if (actual.possibleMove(1)) {
                    queue.add(new Node(actual.nextMap(1), actual, actual.getDeep() + 1, actual.nextCost(1),
                            actual.nextItem(1), actual.nextNav(1), actual.nextPlace(1)));
                }
                if (actual.possibleMove(2)) {
                    queue.add(new Node(actual.nextMap(2), actual, actual.getDeep() + 1, actual.nextCost(2),
                            actual.nextItem(2), actual.nextNav(2), actual.nextPlace(2)));
                }
                if (actual.possibleMove(3)) {
                    queue.add(new Node(actual.nextMap(3), actual, actual.getDeep() + 1, actual.nextCost(3),
                            actual.nextItem(3), actual.nextNav(3), actual.nextPlace(3)));
                }
                if (actual.possibleMove(4)) {
                    queue.add(new Node(actual.nextMap(4), actual, actual.getDeep() + 1, actual.nextCost(4),
                            actual.nextItem(4), actual.nextNav(4), actual.nextPlace(4)));
                }
            }
            i++;
        }
        System.out.println("Cantidad de while: " + i);
    }

}
