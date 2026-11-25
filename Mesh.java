public class Mesh {

    public Vector3[] vertices; //Purpose
    public int[][] edges; //Stores which points connect
    public int[][] faces;

    public Mesh(Vector3[] vertices, int[][] edges, int[][] faces) { //constructs a 3d object
        this.vertices = vertices;
        this.edges = edges;
        this.faces = faces;
    }
    public static Mesh createCube() {

    Vector3[] vertices = {
        new Vector3(-1, -1, -1), // 0
        new Vector3( 1, -1, -1), // 1
        new Vector3( 1,  1, -1), // 2
        new Vector3(-1,  1, -1), // 3

        new Vector3(-1, -1,  1), // 4
        new Vector3( 1, -1,  1), // 5
        new Vector3( 1,  1,  1), // 6
        new Vector3(-1,  1,  1)  // 7
    };

    int[][] edges = {
    // First square
    {0, 1}, {1, 2}, {2, 3}, {3, 0},

    // Opposite square
    {4, 5}, {5, 6}, {6, 7}, {7, 4},

    // Connect both squares
    {0, 4}, {1, 5}, {2, 6}, {3, 7}
    };
    int[][] faces = {
    {0, 3, 2, 1},
    {4, 5, 6, 7},
    {0, 4, 7, 3},
    {1, 2, 6, 5},
    {3, 7, 6, 2},
    {0, 1, 5, 4}
};
    return new Mesh(vertices, edges, faces);
    }
}