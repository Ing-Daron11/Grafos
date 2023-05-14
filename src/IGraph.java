public interface IGraph<K, T> {
    public void addNode(K key, T value);

    void addNode(Comparable key, Object value);

    public String connectVertex(K vStart, K vFinal);
    public void bfs(Vertex s);
    public void dfs(Vertex s);
}
