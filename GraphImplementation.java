import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GraphImplementation implements Graph {
    private int[][] matrix;
    int vertices;

    public GraphImplementation(int v) {
        this.matrix = new int[v][v];
        this.vertices = v;

    }

    public void addEdge(int v1, int v2) throws Exception {
        if (v1 < 0 || v2 < 0 || v1 >= matrix[0].length || v2 >= matrix[0].length) {
            throw new Exception();
        }
        matrix[v1][v2] = 1;
    }

    @Override
    public List<Integer> topologicalSort() {
        /*
    public void dfs(int v) throws Exception{
        boolean[] visited = new boolean[vertices];
        visited[v] = true;
        dfs(v,visited);
    }

    private void dfs(int v, boolean[] visited) throws Exception{
        Iterator<Integer> it = neighbors(v).iterator();
        while(it.hasNext()){
            int n = it.next();
            if(!visited[n]){
                visited[n] = true;
                dfs(n, visited);
            }
        }
    }*/
        System.out.print("The result of topological sort: ");
        List schedule = new ArrayList<Integer>();
        int[] sum = new int[vertices];
        for (int i = 0; i < vertices; ++i) {
            for (int j = 0; j < vertices; ++j) {
                sum[i] += matrix[j][i];
            }
        }
        for (int count = 0; count < vertices; count++) {
            int n = findZero(sum); // Find the vertex that has the lowest dependency
            sum[n] = -1;
            schedule.add(n);
            for (int i = 0; i < vertices; ++i) {
                sum[i] -= matrix[n][i]; // decrement the sum of dependency of other vertices
            }
        }
        for(int i = 0; i < vertices; ++i){
            System.out.print(schedule.get(i) + " ");
        }
        System.out.println();
        return schedule;
    }

    private int findZero(int[] sum) {
        for (int i = 0; i < vertices; ++i) {
            if (sum[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public List<Integer> neighbors(int vertex) throws Exception {

        List<Integer> neighbour = new ArrayList<Integer>();
        for (int i = 0; i < vertices; ++i) {
            if (matrix[vertex][i] == 1) {
                neighbour.add(i);
            }
        }
        return neighbour;
    }
}
