package exe.nikidos;
import java.util.ArrayList;
public class Main {

    private static ArrayList<GraphArc> initialazeGraph()  // Инициализация графа
    {
        ArrayList<GraphArc> tmp= new ArrayList<>();
        tmp.add(new GraphArc(1,1,0));
        tmp.add(new GraphArc(1,2,4));
        tmp.add(new GraphArc(1,5,12));
        tmp.add(new GraphArc(1,6,10));
        tmp.add(new GraphArc(1,8,14));
        tmp.add(new GraphArc(2,3,3));
        tmp.add(new GraphArc(2,5,4));
        tmp.add(new GraphArc(6,5,10));
        tmp.add(new GraphArc(6,7,8));
        tmp.add(new GraphArc(6,8,2));
        tmp.add(new GraphArc(3,4,10));
        tmp.add(new GraphArc(3,5,6));
        tmp.add(new GraphArc(4,9,8));
        tmp.add(new GraphArc(5,4,10));
        tmp.add(new GraphArc(5,7,12));
        tmp.add(new GraphArc(7,4,14));
        tmp.add(new GraphArc(7,9,6));
        tmp.add(new GraphArc(8,7,18));
        tmp.add(new GraphArc(8,9,20));

        return tmp;
    }
    private static ArrayList<ReshGraph> initialazeResh(ArrayList<GraphArc> Graph)
    {
        double max=Double.MIN_VALUE;
        for (GraphArc graphArc : Graph) {
            if (graphArc.kZ > max) max = graphArc.kZ;
        }
        ArrayList<ReshGraph> tmp= new ArrayList<>();
        for(int i=0; i<max;i++)
        {
            tmp.add(new ReshGraph(i));
        }
        tmp.get(0).okrash=true;
        tmp.get(0).weight=0;
        return tmp;
    }
    private  static ReshGraph findMinimalResh(ArrayList<ReshGraph> tmp)
    {
        double min=Double.MAX_VALUE;
        ReshGraph minResh= tmp.get(0);
        for (ReshGraph reshGraph : tmp) {
            if ((reshGraph.weight < min) && (!reshGraph.okrash)) {
                min = reshGraph.weight;
                minResh = reshGraph;
            }
        }
        minResh.okrash=true;
        return minResh;
    }
    private static boolean checkOkrash(ArrayList<ReshGraph> tmp)
    {
        boolean state=false;
        int size=0;
        for (ReshGraph Gr:tmp) {
            if(Gr.okrash)
            {
                size++;
            }
        }
        if(size==tmp.size())
        {
            state=true;
        }
        return state;
    }

    public static void main(String[] args) {

        ArrayList<GraphArc> Graph= initialazeGraph();
        ArrayList <ReshGraph> Resh= initialazeResh(Graph);

        Resh.get(0).okrash=true;
        Resh.get(0).wayMap.add(Graph.get(0));
        Resh.get(0).weight=0;
        ReshGraph OkrashResh = Resh.get(0);
        while(!checkOkrash(Resh))
        {
            for (int i = 0; i < Resh.size(); i++)
            {
                for (GraphArc Arc : Graph)
                {
                    if ( (Arc.hasArc(OkrashResh.point,i+1)) && (!Resh.get(i).okrash) ) {
                        if(OkrashResh.weight + Arc.weight<Resh.get(i).weight)
                        {
                            Resh.get(i).weight=OkrashResh.weight + Arc.weight;
                            Resh.get(i).wayMap.clear();
                            for(GraphArc predArc: OkrashResh.wayMap)            // Построение карты веса
                            {                                                  //  c разваротом карты
                                Resh.get(i).wayMap.add(predArc);              //   окрашеной точки
                            }
                            Resh.get(i).wayMap.add(Arc);
                        }
                        else
                        {
                            Resh.get(i).weight=Resh.get(i).weight;
                        }
                        System.out.println(Resh.get(i).weight);
                    }
                }
            }
            OkrashResh = findMinimalResh(Resh);
        }

        System.out.println();                          // Вывод решения на экран
        for (ReshGraph r: Resh) {
            for (GraphArc arc: r.wayMap) {
                System.out.print(arc.kZ+"-");
            }
            System.out.println("= "+r.weight);
        }
    }
}
