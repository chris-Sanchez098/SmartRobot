package model;

public class Node {
    private Integer[][] map;
    private Node parent;
    private Integer deep;
    private Integer cost;
    private Integer item;
    private Integer[] place;
    private Integer nav;

    public Node(Integer[][] map, Node parent, Integer deep, Integer cost, Integer item, Integer nav, Integer[] place) {
        this.map = map;
        this.parent = parent;
        this.deep = deep;
        this.cost = cost;
        this.item = item;
        this.place = place;
        this.nav = nav;
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

    public Boolean possibleMove(Integer direction) {
        if (parent == null || nav == 10 || nav == 20) {
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
        } else {
            switch (direction) {
                case 1: //right
                    if (place[1] == 9 || map[place[0]][place[1] + 1] == 1 || place[1] + 1 == parent.getPlace()[1]) {
                        return false;
                    }
                    break;
                case 2: //left
                    if (place[1] == 0 || map[place[0]][place[1] - 1] == 1 || place[1] - 1 == parent.getPlace()[1]) {
                        return false;
                    }
                    break;
                case 3: //up
                    if (place[0] == 0 || map[place[0] - 1][place[1]] == 1 || place[0] - 1 == parent.getPlace()[0]) {
                        return false;
                    }
                    break;
                default: //down
                    if (place[0] == 9 || map[place[0] + 1][place[1]] == 1 || place[0] + 1 == parent.getPlace()[0]) {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }

    public Integer nextCost(Integer direction) {
        Integer[] nextPlace = nextPlace(direction);
        Integer nextCost = calCost(map[nextPlace[0]][nextPlace[1]]);
        return nextCost + cost;
    }


    public Integer nextItem(Integer direction) {
        switch (direction) {
            case 1: //right
                if (map[place[0]][place[1] + 1] == 5) {
                    return item + 1;
                }
                break;
            case 2: //left
                if (map[place[0]][place[1] - 1] == 5) {
                    return item + 1;
                }
                break;
            case 3: //up
                if (map[place[0] - 1][place[1]] == 5) {
                    return item + 1;
                }
                break;
            default: //down
                if (map[place[0] + 1][place[1]] == 5) {
                    return item + 1;
                }
                break;
        }
        return item;
    }


    public Integer nextNav(Integer direction) {
        if (nav > 0) {
            return nav - 1;
        }
        Integer[] nextPlace = nextPlace(direction);
        if(map[nextPlace[0]][nextPlace[1]] == 3){
            return 20;
        }
        if(map[nextPlace[0]][nextPlace[1]] == 4){
            return 10;
        }
        return 0;
    }

    public Integer[] nextPlace(Integer direction) {
        Integer[] nextPlace = new Integer[2];
        switch (direction) {
            case 1: //right
                nextPlace[0] = place[0];
                nextPlace[1] = place[1] + 1;

            case 2: //left
                nextPlace[0] = place[0];
                nextPlace[1] = place[1] - 1;
            case 3: //up
                nextPlace[0] = place[0] - 1;
                nextPlace[1] = place[1];
            default: //down
                nextPlace[0] = place[0] + 1;
                nextPlace[1] = place[1];
        }
        return nextPlace;
    }

    public Integer[][] nextMap(Integer direction) {
        Integer[][] nextMap = new Integer[10][10];
        for (int i = 0; i < 10; i++) {
            System.arraycopy(map[i], 0, nextMap[i], 0, 10);
        }
        Integer[] nextPlace = nextPlace(direction);
        nextMap[place[0]][place[1]] = 0;
        nextMap[nextPlace[0]][nextPlace[1]] = 2;
        return nextMap;
    }

    private Integer calCost(Integer type) {
        if (type == 6 && nav == 0) {
            return 4;
        }
        return 1;
    }
    public void viewMap() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
