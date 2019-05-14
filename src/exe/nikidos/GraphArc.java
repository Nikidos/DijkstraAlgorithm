package exe.nikidos;

public class GraphArc {
    public int nZ, kZ;     // Начально, конечное точки арки
    public double weight; //  Вес арки
    GraphArc(int nZ,int kZ, int weight)
    {
        this.nZ=nZ;
        this.kZ=kZ;
        this.weight=weight;
    }
    public boolean hasArc (double nz, double kZ) // Проверка на существование арки
    {
        if((nz==this.nZ) && (kZ==this.kZ)) {
            return true;
        }
        else {
            return false;
        }
    }
}
