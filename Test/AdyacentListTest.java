import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdyacentListTest {
    private AdyacentList<Integer, String> graph;
    private AdyacentList<Integer, Integer> graph2;
    private AdyacentList<String, Integer> graph3;

    @BeforeEach
    public void setUp() {
        graph = new AdyacentList<>();
        graph2 = new AdyacentList<>();
        graph3 = new AdyacentList<>();
    }

    @Test
    public void testAddNode() {
        graph.addNode(1, "Jairo");
        graph.addNode(2, "Alfonso");

        String adjList1 = graph.showAdjList(1);
        String adjList2 = graph.showAdjList(2);

        //No debe mostrar nada ya que no se han conectado los vertices
        Assertions.assertEquals("", adjList1);
        Assertions.assertEquals("", adjList2);
    }

    @Test
    public void testAddNode2() {
        graph.addNode(1, "Alberto");
        graph.addNode(2, "Buitrera");

        graph.connectVertex(1, 2, 1);
        String adjList1 = graph.showAdjList(1);
        String adjList2 = graph.showAdjList(2);

        //Debe mostrar la lista de adyacencia de ambos vértices
        Assertions.assertEquals("[Buitrera]", adjList1);
        Assertions.assertEquals("[Alberto]", adjList2);
    }
    @Test
    public void testAddNode3() {
        graph2.addNode(11, 2020);
        graph2.addNode(34, 2026);

        graph2.connectVertex(11, 11, 3);
        graph2.connectVertex(11, 11, 3);
        graph2.connectVertex(11, 34, 3); //Aquí no debería agregar ningún vértice a ninguna lista de adyacencia, ya que al ser bucle, el vStart y vFinal deben ser iguales

        String adjList1 = graph2.showAdjList(11);
        String adjList2 = graph2.showAdjList(34);

        Assertions.assertEquals("[2020][2020][2026]", adjList1);
        Assertions.assertEquals("", adjList2);
    }

    @Test
    public void testConnectVertex() {
        graph.addNode(1, "Algo");
        graph.addNode(2, "No se");
        graph.addNode(3, "Como?");

        String result1 = graph.connectVertex(1, 2, 1); // Grafo simple
        String result2 = graph.connectVertex(1, 2, 2); // Multigrafo
        String result3 = graph.connectVertex(1, 2, 3); // Bucle/Multigrafo
        String result4 = graph.connectVertex(1, 3, 4); // Grafo dirigido

        Assertions.assertEquals("Simple Vertexes connected", result1);
        Assertions.assertEquals("multigraph Vertexes connected", result2);
        Assertions.assertEquals("loops/multigraph Vertexes connected", result3);
        Assertions.assertEquals("directed Vertexes connected", result4);
    }

    @Test
    public void testConnectVertex2() {
        graph.addNode(1, "Santiago");
        graph.addNode(2, "vaca");
        graph.addNode(3, "Diamante");

        String result = graph.connectVertex(1, 2, 2); // Multigrafo

        Assertions.assertEquals("multigraph Vertexes connected", result);

        // conectamos los mismos vertices y no debe arrojar error, ya que es multigrafo
        result = graph.connectVertex(1, 2, 2); // Multigrafo

        Assertions.assertEquals("multigraph Vertexes connected", result);
    }

    @Test
    public void testConnectVertex3() {
        graph.addNode(1, "Oro");
        graph.addNode(2, "Plata");
        graph.addNode(3, "Bronce");

        String result1 = graph.connectVertex(1, 1, 3); // Bucle/multigrafo
        String result2 = graph.connectVertex(2, 2, 3); // Bucle/multigrafo

        Assertions.assertEquals("loops/multigraph Vertexes connected", result1);
        Assertions.assertEquals("loops/multigraph Vertexes connected", result2);
    }
    @Test
    public void testShowAdjList1() {
        graph.addNode(1, "Añadir");
        graph.addNode(2, "Eliminar");

        String adjList = graph.showAdjList(1);
        //Debe estar vacío, ya que no se conectaron los vertices
        Assertions.assertEquals("", adjList);
    }

    @Test
    public void testShowAdjList2() {
        graph3.addNode("Perro",1);
        graph3.addNode("Gato",3);


        graph3.connectVertex("Perro", "Perro", 3);//Bucle
        graph3.connectVertex("Perro", "Perro", 3);//Bucle
        String adjList = graph3.showAdjList("Perro");

        Assertions.assertEquals("[1][1]", adjList);
    }

    @Test
    public void testShowAdjList3() {
        graph2.addNode(1, 45);
        graph2.addNode(2, 44);
        graph2.addNode(3, 43);

        graph2.connectVertex(1, 2, 2); // multigrafo
        graph2.connectVertex(1, 3, 2); // multigrafo

        String adjList = graph2.showAdjList(1);

        Assertions.assertEquals("[44][43]", adjList);
    }

    @Test
    public void testBfs1() {
        graph.addNode(1, "Daron");
        graph.bfs(1);

        Vertex<Integer, String> vertex = graph.getHashMapVertexes().get(1); //Obtenemos el vértice a partir de la clave
        Assertions.assertEquals("BLACK", vertex.getColor()); //Una vez ejecutado el Bfs, el color del vértice debe ser BLACK
        Assertions.assertEquals(0, vertex.getDistance()); //Como no tiene padre, su distancia permaneció en 0
        Assertions.assertNull(vertex.getParent()); //Su padre debe ser nulo
    }

    @Test
    public void testBfs2() {
        graph3.addNode("Bruno", 2);
        graph3.addNode("Daron", 5);
        graph3.addNode("Alberto", 7);
        graph3.addNode("Adriana", 111);

        graph3.connectVertex("Bruno", "Daron", 1);//Simple
        graph3.connectVertex("Alberto", "Adriana", 1);//Simple
        graph3.connectVertex("Bruno", "Alberto", 1);//Simple
        graph3.bfs("Bruno");

        Vertex<String, Integer> vertex = graph3.getHashMapVertexes().get("Bruno");//Obtenemos el vértice a partir de la clave
        Assertions.assertEquals("BLACK", vertex.getColor());
        Assertions.assertNull(vertex.getParent()); //Su padre debe ser nulo

    }

    @Test
    public void testBfs3() {
        graph3.addNode("Bruno", 2);
        graph3.addNode("Daron", 5);
        graph3.addNode("Alberto", 7);
        graph3.addNode("Adriana", 111);

        graph3.connectVertex("Bruno", "Daron", 4);//Dirigido
        graph3.connectVertex("Alberto", "Adriana", 4);//Dirigido
        graph3.connectVertex("Bruno", "Alberto", 4);//Dirigido
        graph3.bfs("Bruno"); //Empezamos en el vertice que tiene como clave "Bruno"

        Vertex<String, Integer> vertex = graph3.getHashMapVertexes().get("Adriana");//Obtenemos el vértice a partir de la clave
        Assertions.assertEquals(2, vertex.getDistance()); //La distancia de adriana debe ser de 2
        Assertions.assertNotEquals("GREY", vertex.getColor()); //Al finalizar el BSF todos los vertices siempre van a ser BLACK

    }

    @Test
    public void testDfs1() {
        graph.addNode(1, "Daron");
        graph.dfs();

        Vertex<Integer, String> vertex = graph.getHashMapVertexes().get(1); //Obtenemos el vértice a partir de la clave
        Assertions.assertEquals("BLACK", vertex.getColor()); //Una vez ejecutado el Dfs, el color de todos los vértices debe ser BLACK
        Assertions.assertEquals(1, vertex.getStartTime()); //El tiempo de inicio es 1
        Assertions.assertEquals(2, vertex.getFinalTime());//El tiempo de final es 2, ya que solo hay un vértice
        Assertions.assertNull(vertex.getParent()); //Su padre debe ser nulo
    }

    @Test
    public void testDfs2() {
        graph3.addNode("Ingrid", 7);
        graph3.addNode("Cesar", 5);
        graph3.addNode("Deicy", 24);
        graph3.addNode("Daron", 84);

        graph3.connectVertex("Ingrid", "Cesar", 3);//Dirigido
        graph3.connectVertex("Ingrid", "Daron", 3);//Dirigido
        graph3.connectVertex("Ingrid", "Deicy", 3);//Dirigido
        graph3.dfs();

        //En este caso el Dfs toma como punto de partida al vertice con clave Deicy
        Vertex<String, Integer> vertex = graph3.getHashMapVertexes().get("Deicy");//Obtenemos el vértice a partir de la clave
        Assertions.assertEquals("BLACK", vertex.getColor());
        Assertions.assertEquals(1, vertex.getStartTime());
        Assertions.assertEquals(2, vertex.getFinalTime());
    }

    @Test
    public void testDfs3() {
        graph3.addNode("Ingrid", 7);
        graph3.addNode("Cesar", 5);
        graph3.addNode("Deicy", 24);
        graph3.addNode("Daron", 84);
        graph3.addNode("Breiner", 33); //Este no está cpnectado a ningún vertice


        graph3.connectVertex("Ingrid", "Cesar", 3);//Dirigido
        graph3.connectVertex("Ingrid", "Daron", 3);//Dirigido
        graph3.connectVertex("Ingrid", "Deicy", 3);//Dirigido
        graph3.dfs();

        Vertex<String, Integer> vertex = graph3.getHashMapVertexes().get("Cesar");//Obtenemos el vértice a partir de la clave
        Assertions.assertEquals(8, vertex.getStartTime()); //El tiempo inicial de Cesar debe ser 6
        Assertions.assertEquals(9, vertex.getFinalTime()); //El tiempo final de Cesar debe ser 7
        Assertions.assertNotEquals("GREY", vertex.getColor()); //Al finalizar el DSF todos los vertices siempre van a ser BLACK

        Vertex<String, Integer> vertex2 = graph3.getHashMapVertexes().get("Daron");
        Assertions.assertEquals(5, vertex2.getStartTime()); //El tiempo inicial de Daron debe ser 3
        Assertions.assertEquals(6, vertex2.getFinalTime()); //El tiempo final de Daron debe ser 4
        Assertions.assertEquals("BLACK", vertex.getColor());

        Vertex<String, Integer> vertex3 = graph3.getHashMapVertexes().get("Breiner");
        Assertions.assertEquals("BLACK", vertex3.getColor());//Con esto nos aseguramos que efectivamente se alcanzó el vértice que no estaba conectado
    }
}
