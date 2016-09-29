import java.util.*;

/**
 * Created by Johan Pettersson on 2016-09-28.
 */
public class Main {

    private static final int ARR_SIZE = 10;
    private static final int COLOR_RED = 2;
    private static final int COLOR_BLUE = 1;

    public static void main(String[] args) {

        ArrayList<int[]> input = new ArrayList<int[]>();

        /*
            this part is strongly connected and bipartite with 7 vertexes.
            see .../resources/graph.PNG
         */
        //input.add(new int[]{0, 1});
        input.add(new int[]{0, 2});
        //input.add(new int[]{1, 0});
        input.add(new int[]{1, 2});
        input.add(new int[]{1, 3});
        input.add(new int[]{2, 0});
        input.add(new int[]{2, 1});
        //input.add(new int[]{2, 3});
        input.add(new int[]{2, 4});
        input.add(new int[]{2, 6});
        input.add(new int[]{3, 1});
        //input.add(new int[]{3, 2});
        input.add(new int[]{3, 4});
        input.add(new int[]{4, 2});
        input.add(new int[]{4, 3});
        input.add(new int[]{4, 5});
        input.add(new int[]{5, 4});
        input.add(new int[]{5, 6});
        input.add(new int[]{6, 5});
        input.add(new int[]{6, 2});

        /*
            this part is strongly connected and non-bipartite with 3 vertexes, but is disconnected from the graph above.
            see .../resources/graph.PNG
         */
        input.add(new int[]{7, 8});
        input.add(new int[]{7, 9});
        input.add(new int[]{8, 7});
        input.add(new int[]{8, 9});
        input.add(new int[]{9, 8});
        input.add(new int[]{9, 7});

        int[][] matrix = new int[ARR_SIZE][ARR_SIZE];

        /*
            Building adjacency matrix from input
         */
        //TODO: represent as adjacency list instead to get O(V*E) instead of O(n^2)
        for (int i = 0; i < input.size(); i++) {
            int vertex1 = input.get(i)[0];
            int vertex2 = input.get(i)[1];
            matrix[vertex1][vertex2] = 1;
        }

        int[] layer = new int[ARR_SIZE];
        boolean[] queued = new boolean[ARR_SIZE];
        Queue<Integer> queue = new LinkedList<Integer>();
        int[] colors = new int[ARR_SIZE];
        boolean bipartite = true;

        for (int i = 0; i < ARR_SIZE; i++) {
            if (!queued[i]) {
                queue.add(i);
                queued[i] = true;
                layer[i] = 0;
                while (!queue.isEmpty()) {
                    int polled = queue.poll();
                    for (int j = 0; j < ARR_SIZE; j++) {
                        if (matrix[polled][j] == 1) {
                            if (!queued[j]) {
                                queue.add(j);
                                queued[j] = true;
                                layer[j] = layer[polled] + 1;
                            }
                            if (layer[j] % 2 == 0)
                                colors[j] = COLOR_RED;
                            else
                                colors[j] = COLOR_BLUE;

                            if (colors[polled] == colors[j])
                                bipartite = false;
                        }
                    }
                }
            }
        }
        System.out.println("bipartite: " + bipartite);
    }
}
