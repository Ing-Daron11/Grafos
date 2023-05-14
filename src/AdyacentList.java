import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class AdyacentList <K extends Comparable<K>,T> {


    private HashMap<K, Vertex<K, T>> hashMapVertexes;

    private int time = 0;

    public AdyacentList() {
        hashMapVertexes = new HashMap<>();
    }

    public void addNode(K key, T value) { //Add vertice
        Vertex<K, T> newVertex = new Vertex<>(key, value);
        hashMapVertexes.put(key, newVertex);
    }

    //1 - simple
    //2- multigrafo
    // 3- bucle/multigrafo
    //4 dirigido
    public String connectVertex(K vStart, K vFinal, int typeGraph) {
        String msj = "One of those vertexes doesn't exist";
        Vertex<K, T> vertexStart = hashMapVertexes.get(vStart);
        Vertex<K, T> vertexFinal = hashMapVertexes.get(vFinal);
        if (vertexStart != null && vertexFinal != null) {
            switch (typeGraph) {
                case 1:
                    if (vertexStart != vertexFinal) {
                        if (vertexStart.isAlreadyInTheAdj(vertexFinal) || vertexFinal.isAlreadyInTheAdj(vertexStart)) {
                            msj = "This is a simple graph, loops aren't allowed";
                        } else {
                            vertexStart.addVertexToAdj(vertexFinal);
                            vertexFinal.addVertexToAdj(vertexStart);
                            msj = "Simple Vertexes connected";
                        }
                    }
                    break;
                case 2:
                    if (vertexStart != vertexFinal) {
                        vertexStart.addVertexToAdj(vertexFinal);
                        vertexFinal.addVertexToAdj(vertexStart);
                        msj = "multigraph Vertexes connected";
                    }else{
                        msj = "loops aren't allowed";
                    }
                    break;
                case 3:
                    vertexStart.addVertexToAdj(vertexFinal);
                    msj = "loops/multigraph Vertexes connected";
                    break;
                case 4:
                    if (vertexStart != vertexFinal) {
                        vertexStart.addVertexToAdj(vertexFinal);
                        msj = "directed Vertexes connected";
                    }else{
                        msj = "loops aren't allowed";
                    }
                    break;
            }
        }
        return msj;
    }

    public String showAdjList(K key) {
        String msj = "";
        Vertex<K, T> vertex = hashMapVertexes.get(key);
        msj = vertex.toString();
        return msj;
    }

    public void bfs(K vStart){
        Vertex<K,T> S = hashMapVertexes.get(vStart);
        for(K u : hashMapVertexes.keySet()){
            hashMapVertexes.get(u).setColor("WHITE");
            hashMapVertexes.get(u).setDistance(Integer.MAX_VALUE);
            hashMapVertexes.get(u).setParent(null);
        }
        S.setColor("GREY");
        S.setDistance(0);
        S.setParent(null);

        Queue<Vertex> Q = new LinkedList<>();
        Q.add(S);
        while (!Q.isEmpty()){
            Vertex<K,T> u  = Q.poll();

            for(Vertex<K,T> v : u.getAdj() ){
                if(v.getColor() == "WHITE"){
                    v.setColor("GREY");
                    v.setDistance(u.getDistance() + 1);
                    v.setParent(u);
                    Q.add(v);
                }
            }
            u.setColor("BLACK");
        }
    }

    public void dfs(){
        for (K u : hashMapVertexes.keySet()){
            hashMapVertexes.get(u).setColor("WHITE");
            hashMapVertexes.get(u).setParent(null);
        }

        time = 0;

        for (K u : hashMapVertexes.keySet()){
            if(hashMapVertexes.get(u).getColor() == "WHITE"){
                dfsVisit(hashMapVertexes.get(u));
            }
        }
    }

    public void dfsVisit(Vertex<K,T> u){
        time = time + 1;
        u.setStartTime(time);
        u.setColor("GREY");
        for (Vertex<K,T> v : u.getAdj()){
            if(v.getColor() == "WHITE"){
                v.setParent(u);
                dfsVisit(v);
            }
        }
        u.setColor("BLACK");
        time = time +1;
        u.setFinalTime(time);
    }

    public HashMap<K,Vertex<K,T>> getHashMapVertexes() {
        return hashMapVertexes;
    }

}