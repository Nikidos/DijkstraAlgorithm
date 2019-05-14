package exe.nikidos;

import java.util.ArrayList;

public class ReshGraph {
    public ArrayList<GraphArc> wayMap; // Карта путей
    public double weight;             //  Вес решения
    public boolean okrash;           //   Окраска решения
    public double point;            //    Представление точки
    ReshGraph(int pt) {
        wayMap = new ArrayList<>();
        weight=Double.POSITIVE_INFINITY;
        okrash=false;
        point=pt+1;

    }
}
