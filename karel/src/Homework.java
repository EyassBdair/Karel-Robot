import stanford.karel.SuperKarel;

public class Homework extends SuperKarel {
    /* You fill the code here */
    int steps = 1;
    int beepers = 0;
    int Column = 1;
    int Row = 1;

    public void run() {
        steps = 1;
        beepers = 0;
        setBeepersInBag(1000);
        scanWorld();
        int col = Column;
        int row = Row;
        System.out.println("Row = " + row);
        System.out.println("Column = " + col);
        if (col != 0 && row != 0) {
            if (col == 1 || row == 1)
            {
                case1_Many(2,  Math.max(col, row),Math.min(col, row), col, row);
            }
            else if (col <= 2 && row <= 2)
            {
                if (col != 1 && row != 1)
                    CaseEvenEqual(1);
            }
            else if (col <= 3 || row <= 3)
            {
                int x, y;
                x = col;
                y = row;
                if (x > y && x >= 3) {
                    if (x >= 7)
                        case2_Many(x, y);
                    else if (x < 7) {
                        case1_Many(1, x, y, 0, 0);
                    }
                } else if (y > x && y >= 3) {
                    if (y >= 7)
                        case2_Many(x, y);
                    else if (y < 7) {
                        case1_Many(1, x, y, 0, 0);
                    }
                }
            }
            else if (col % 2 != 0 || row % 2 != 0 || col != row)
            {
                turnAround();
                moveDiv2(row);
                turnRight();
                PutManyBeepers();
                if (row % 2 == 0) {
                    underUp(1);
                    turnRight();
                    PutManyBeepers();
                }
                MoveTo();
                turnLeft();
                moveDiv2(col);
                turnLeft();
                PutManyBeepers();
                if (col % 2 == 0) {
                    underUp(2);
                    PutManyBeepers();
                }
            }
            else if (col % 2 == 0 || row % 2 == 0 || col == row)
            {
                CaseEvenEqual(1);
                turnLeft();
                turnAround();
                MoveTo();
                CaseEvenEqual(2);
            }
        }
        System.out.println("steps = " + steps);
        System.out.println("beepers = " + beepers);
    }
    private void moveDiv2(int r) {
        for (int i = 0; i < r / 2; i++) {
            if (frontIsClear()) {
                MoveOne();
            }
        }
    }
    private void PutManyBeepers() {
        while (frontIsClear()) {
            PutManyBeeper();
            MoveOne();
        }
        PutManyBeeper();
        turnLeft();
    }
    public void MoveOne() {
        move();
        steps++;
    }
    public void MoveManySteps(int s, boolean b) {
        if (b) s++;
        for (int i = 0; i < s; i++) {
            if (frontIsBlocked()) break;
            MoveOne();
        }
    }
    public void MoveTo() {
        while (frontIsClear()) {
            MoveOne();
        }
    }
    public void CaseEvenEqual(int x) {
        if (x == 1) {
            turnAround();
            PutManyBeeper();
            MoveOne();
            while (frontIsClear()) {
                turnRight();
                MoveOne();
                PutManyBeeper();
                turnLeft();
                MoveOne();
            }
            turnRight();
        } else {
            tlp();
            MoveOne();
            while (frontIsClear()) {
                turnLeft();
                MoveOne();
                PutManyBeeper();
                turnRight();
                MoveOne();
            }
            turnLeft();
        }
        MoveOne();
        PutManyBeeper();
    }
    public void PutOneBeepers() {
        putBeeper();
        beepers++;
    }
    public void PutManyBeeper() {
        if (!beepersPresent()) {
            PutOneBeepers();
        }
    }
    public void underUp(int x) {
        if (x == 1) {
            turnAround();
            MoveOne();
        } else if (x == 2) {
            MoveOne();
            turnLeft();
        }
    }
    public void scanWorld() {
        Column = 1;
        Row = 1;
        while (frontIsClear()) {
            MoveOne();
            Column++;
        }
        turnLeft();
        while (frontIsClear()) {
            MoveOne();
            Row++;
        }
    }
    public void case1_Many(int c, int q, int p, int rows, int cols) {
        if (c == 1) {
            if (q < p) {
                turnLeft();
                MoveTo();
                q = Math.max(q, p);
            }
            turnLeft();
            if (isOdd(q)) {
                MoveOne();
            }
            tlp();
            if (q > 3) {
                turnAround();
                MoveOne();
                MoveOne();
                turnRight();
                PutManyBeepers();
                if (q > 5) {
                    MoveOne();
                    MoveOne();
                    tlp();
                }
            }
        } else if (c == 2) {
            if (rows >= 7 || cols >= 7) {
                int m = isOdd1(q);
                int r = q - m;
                int l = q - m;
                turnAround();
                if (frontIsBlocked()) turnRight();
                MoveManySteps(m - 1, false);
                if (!isOdd(q)) {
                    PutOneBeepers();
                    MoveOne();
                }
                PutOneBeepers();
                int ll = isOdd1(l);
                int rr = isOdd1(r);
                if(q%4== 0){
                    MoveManySteps(ll, false);
                    PutOneBeepers();
                    turnAround();
                    MoveManySteps(ll, false);
                    MoveOne();
                    MoveManySteps(rr, false);
                    PutOneBeepers();
                }
                else {
                    if (l + r == q && q % 4 == 0) {
                        if (q % 4 != 0) {
                            ll--;
                            rr--;
                        }
                    }
                    MoveManySteps(ll, false);
                    PutOneBeepers();
                    turnAround();
                    if (!isOdd(q) && q % 4 != 0) {
                        MoveOne();
                        PutOneBeepers();
                    } else if ((q - 3) % 4 != 0) {
                        turnAround();
                        MoveOne();
                        PutManyBeeper();
                        turnAround();
                        MoveOne();
                    }
                    MoveManySteps(ll, false);
                    if (q % 4 == 0) MoveOne();
                    else if (q % 2 == 0) rr--;
                    MoveManySteps(rr, false);
                    PutOneBeepers();
                    if (!isOdd(q) && q % 4 != 0) {
                        MoveOne();
                        PutOneBeepers();
                    } else if ((q - 3) % 4 != 0) {
                        MoveOne();
                        PutManyBeeper();
                    }}
            } else {
                int max = Math.max(rows, cols);
                if (rows < cols) {
                    turnAround();
                } else {
                    turnLeft();
                }
                if (isOdd(max)) {
                    MoveOne();
                }
                PutManyBeeper();
                if (max > 3) {
                    MoveOne();
                    MoveOne();
                    PutManyBeeper();
                    if (q > 5) {
                        MoveOne();
                        MoveOne();
                        PutManyBeeper();
                    }
                }
            }
        }
    }
    public int isOdd1(int o) {
        if (o % 2 == 0) return o / 2;
        return (o / 2 + 1);
    }
    public boolean isOdd(int o) {
        if (o % 2 == 0) return false;
        return true;
    }
    public void tlp() {
        turnLeft();
        PutManyBeepers();
    }
    public void case2_Many(int x, int y) {
        int q = Math.max(x, y);int m = isOdd1(q);
        int r = q - m;int l = q - m;
        turnLeft();
        if (y > x) {
            MoveTo();
            turnLeft();
        }
        MoveManySteps(m - 1, false);
        tlp();
        if (!isOdd(q)) {
            underUp(1);
            turnRight();
            PutManyBeepers();
        } else {
            turnLeft();
            MoveTo();
            turnLeft();
        }
        int ll = isOdd1(l);
        int rr = isOdd1(r);
        if (l + r == q && q % 4 == 0) {
            if (q % 4 != 0) {
                ll--;rr--;
            }
        }
        if(q%4==0){
            MTlPmb(ll);
            TlMtTr();
            MoveManySteps(ll, false);
            MoveOne();
            MoveManySteps(rr, false);
            turnRight();
            PutManyBeepers();
        }
        else {
            MTlPmb(ll);
            if (!isOdd(q) && q % 4 != 0) {
                underUp(2);
                pt();
            } else if ((q - 3) % 4 != 0) {
                underUp(1);
                turnRight();
                pt();
                MoveOne();
            } else {
                TlMtTr();
            }
            MoveManySteps(ll, false);
            if (q % 4 == 0) MoveOne();
            else if (q % 2 == 0) rr--;
            MoveManySteps(rr, false);
            turnRight();
            PutManyBeepers();
            if (!isOdd(q) && q % 4 != 0) {
                underUp(2);
                PutManyBeepers();
            } else if ((q - 3) % 4 != 0) {
                underUp(2);
                PutManyBeepers();
            }
        }
    }
    public void MTlPmb(int ll){
        MoveManySteps(ll, false);
        turnLeft();
        PutManyBeepers();
    }
    public void TlMtTr(){
        turnLeft();
        MoveTo();
        turnRight();
    }
    public void pt(){
        PutManyBeepers();
        turnAround();
    }
}