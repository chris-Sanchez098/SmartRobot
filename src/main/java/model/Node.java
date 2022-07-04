package model;

public class Node {
    private Integer[][] map;
    private Node parent;
    private Integer deep;
    private Integer cost;
    private Integer item;
    private Integer[] place;
    private Integer nav;
    private Integer heuristic = 0;
    private Integer[][] goals;
    private Integer nodes = 0;
    private Double time = 0.0;

    /**
     * Constructor source node
     *
     * @param map   environment
     * @param place robot place
     * @param goals in map
     */
    public Node(Integer[][] map, Integer[] place, Integer[][] goals) {
        this.map = map;
        this.parent = null;
        this.deep = 0;
        this.cost = 0;
        this.item = 0;
        this.place = place;
        this.nav = 0;
        this.goals = goals;
    }

    /**
     * Constructor child node
     *
     * @param parent    parent node
     * @param direction 1 right, 2 left, 3 up and other down
     */
    public Node(Node parent, Integer direction) {
        Integer[] nextPlace = parent.nextPlace(direction);
        this.parent = parent;
        this.goals = parent.getGoals();
        this.deep = parent.getDeep() + 1;
        this.cost = parent.nextCost(direction, nextPlace);
        this.map = parent.nextMap(direction, nextPlace);
        this.item = parent.nextItem(direction, nextPlace);
        this.place = nextPlace;
        this.nav = parent.nextNav(direction, nextPlace);
        if (goals != null) {
            this.heuristic = setHeuristic();
        }

    }

    public Integer getCost() {
        return cost;
    }

    public Node getParent() {
        return parent;
    }

    public Integer getDeep() {
        return deep;
    }

    public Integer getItem() {
        return item;
    }

    public Integer[][] getMap() {
        return map;
    }

    public Integer[] getPlace() {
        return place;
    }

    public Integer getNav() {
        return nav;
    }

    public Integer sumCH() {
        return cost + heuristic;
    }

    public Integer getHeuristic() {
        return heuristic;
    }

    public Integer[][] getGoals() {
        return goals;
    }

    public Integer getNodes() {
        return nodes;
    }

    public Double getTime() {
        return time;
    }

    public void setNodes(Integer nodes) {
        this.nodes = nodes;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    /**
     * In a map search robot place
     *
     * @param newMap environment
     * @return robot place
     */
    public static Integer[] initialPlace(Integer[][] newMap) {
        if (newMap == null) {
            return null;
        }
        Integer[] place = new Integer[2];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (newMap[i][j] == 2) {
                    place[0] = i;
                    place[1] = j;
                }
            }
        }
        return place;
    }

    /**
     * In a map search goals
     *
     * @param newMap environment
     * @param size   number of goals
     * @return goals
     */
    public static Integer[][] findGoals(Integer[][] newMap, int size) {
        Integer[][] auxGoals = new Integer[2][2];
        int n = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (newMap[i][j] == 5) {
                    auxGoals[n][0] = i;
                    auxGoals[n][1] = j;
                    n++;
                    if (n == size) {
                        break;
                    }
                }
            }
        }
        return auxGoals;
    }

    /**
     * @param direction 1 right, 2 left, 3 up and other down
     * @return if a move can be made
     */
    public Boolean possibleMove(Integer direction) {
        switch (direction) {
            case 1: //right
                if (place[1] == 9 || map[place[0]][place[1] + 1] == 1) {
                    return false;
                }
                break;
            case 2: //left
                if (place[1] == 0 || map[place[0]][place[1] - 1] == 1) {
                    return false;
                }
                break;
            case 3: //up
                if (place[0] == 0 || map[place[0] - 1][place[1]] == 1) {
                    return false;
                }
                break;
            default: //down
                if (place[0] == 9 || map[place[0] + 1][place[1]] == 1) {
                    return false;
                }
                break;
        }
        return true;
    }

    /**
     * @param direction 1 right, 2 left, 3 up and other down
     * @return if a next position is the father
     */
    public boolean isFather(Integer direction) {
        Integer[] nextPlace = nextPlace(direction);
        if (parent == null) {
            return false;
        }
        if (nav == 10 && parent.getNav() != 11 || nav == 20 && parent.getNav() == 0 || item == 1 && parent.getItem() == 0) {
            return false;
        } else {
            return nextPlace[0] == parent.getPlace()[0] && nextPlace[1] == parent.getPlace()[1];
        }
    }

    /**
     * @param direction 1 right, 2 left, 3 up and other down
     * @return if a next position is an ancestor
     */
    public Boolean isAncestor(Integer direction) {
        Node node = this;
        Integer[] nextPlace = nextPlace(direction);
        while (node.getParent() != null) {
            if (node.getNav() == 10 && node.getParent().getNav() != 11 || node.getNav() == 20 || item == 1 && node.getParent().getItem() == 0) {
                return false;
            }
            if (nextPlace[0] == node.getParent().getPlace()[0] && nextPlace[1] == node.getParent().getPlace()[1]) {
                return true;
            }
            node = node.getParent();
        }
        return false;
    }

    /**
     * @param direction 1 right, 2 left, 3 up and other down
     * @return position on next move
     */
    public Integer[] nextPlace(Integer direction) {
        Integer[] nextPlace = new Integer[2];
        switch (direction) {
            case 1 -> { //right
                nextPlace[0] = place[0];
                nextPlace[1] = place[1] + 1;
            }
            case 2 -> { //left
                nextPlace[0] = place[0];
                nextPlace[1] = place[1] - 1;
            }
            case 3 -> { //up
                nextPlace[0] = place[0] - 1;
                nextPlace[1] = place[1];
            }
            default -> { //down
                nextPlace[0] = place[0] + 1;
                nextPlace[1] = place[1];
            }
        }
        return nextPlace;
    }

    /**
     * @param direction 1 right, 2 left, 3 up and other down
     * @param nextPlace position on next move
     * @return value of cost on next move
     */
    public Integer nextCost(Integer direction, Integer[] nextPlace) {
        Integer nextCost = calCost(map[nextPlace[0]][nextPlace[1]]);
        return nextCost + cost;
    }

    /**
     * @param direction 1 right, 2 left, 3 up and other down
     * @param nextPlace position on next move
     * @return number of items on next move
     */
    public Integer nextItem(Integer direction, Integer[] nextPlace) {
        if (map[nextPlace[0]][nextPlace[1]] == 5) {
            return item + 1;
        }
        return item;
    }

    /**
     * @param direction 1 right, 2 left, 3 up and other down
     * @param nextPlace position on next move
     * @return fuel on next move
     */
    public Integer nextNav(Integer direction, Integer[] nextPlace) {
        if (nav > 0) {
            return nav - 1;
        }
        if (map[nextPlace[0]][nextPlace[1]] == 3) {
            return 20;
        }
        if (map[nextPlace[0]][nextPlace[1]] == 4) {
            return 10;
        }
        return 0;
    }

    /**
     * @param direction 1 right, 2 left, 3 up and other down
     * @param nextPlace position on next move
     * @return map after movement
     */
    public Integer[][] nextMap(Integer direction, Integer[] nextPlace) {
        Integer[][] nextMap = new Integer[10][10];
        for (int i = 0; i < 10; i++) {
            System.arraycopy(map[i], 0, nextMap[i], 0, 10);
        }
        nextMap[place[0]][place[1]] = parent == null ? 0 : (parent.getMap()[place[0]][place[1]] < 5 && parent.getNav() > 1) ? parent.getMap()[place[0]][place[1]] : 0;
        nextMap[nextPlace[0]][nextPlace[1]] = 2;
        return nextMap;
    }


    public void viewMap() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }
    }

    /**
     * the sum of the manhattan distance to the goals, provided they exist
     *
     * @return value of heuristic
     */
    private Integer setHeuristic() {
        int auxHeuristic = 0;
        if (map[goals[0][0]][goals[0][1]] == 5) {
            auxHeuristic += manhattan(0);
        }
        if (map[goals[1][0]][goals[1][1]] == 5) {
            auxHeuristic += manhattan(1);
        }
        return auxHeuristic;
    }

    /**
     *
     * @param goal goal number
     * @return manhattan distance from robot to goal
     */
    private Integer manhattan(Integer goal) {
        Integer x = (goals[goal][1] > place[1]) ? goals[goal][1] - place[1] : place[1] - goals[goal][1];
        Integer y = (goals[goal][0] > place[0]) ? goals[goal][0] - place[0] : place[0] - goals[goal][0];
        return x + y;
    }

    /**
     *
     * @param type value in the environment
     * @return cost of move
     */
    private Integer calCost(Integer type) {
        if (type == 6 && nav == 0) {
            return 4;
        }
        return 1;
    }
}
