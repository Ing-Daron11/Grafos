import java.util.ArrayList;
import java.util.TreeMap;

public class Vertex<K extends Comparable<K>,T>{

    private ArrayList<Vertex<K,T>> adj;
    private T value; //El objeto o valor que contiene el vertice
    private K key; //esta es la clave con la que vamos a comparar
    private String color;

    private int distance;
    private Vertex parent;

    private int startTime;
    private int finalTime;
    public Vertex(K key, T value) {
        this.value = value;
        this.key = key;
        adj = new ArrayList<>();
    }

    public void addVertexToAdj(Vertex<K,T> vertex){
        adj.add(vertex);
    }


    @Override
    public String toString(){
        String msj = "";
        for (int i = 0; i < adj.size(); i++) {
            msj += "[" + adj.get(i).getValue() + "]";
        }
        return msj;
    }

    public boolean isAlreadyInTheAdj(Vertex<K,T> vertexSearched){
        for (int i = 0; i < adj.size(); i++) {
            if(vertexSearched == adj.get(i)){
                return true;
            }
        }
        return false;
    }
    // ------------ Getters and setters ----------------------

    public ArrayList<Vertex<K,T>> getAdj(){
        return adj;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public String getColor() {
        return color;
    }

    public int getDistance() {
        return distance;
    }

    public Vertex getParent() {
        return parent;
    }
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setFinalTime(int finalTime) {
        this.finalTime = finalTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getFinalTime() {
        return finalTime;
    }

}
