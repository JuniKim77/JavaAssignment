package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public interface ICollisionEventListener {
    void listen(ArrayList<ArrayList<ArrayList<Unit>>> worldMap);
    boolean isBomb();
}
