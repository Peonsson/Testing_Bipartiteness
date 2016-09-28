import java.util.*;

/**
 * Created by Xsnud on 2016-09-28.
 */
public class Main {

    private static final int ARR_SIZE = 7;
    private static final int START_NODE = 4;

    private static final int COLOR_RED = 2;
    private static final int COLOR_BLUE = 1;

    public static void main(String[] args) {

        ArrayList<int[]> input = new ArrayList();
        //input.add(new int[]{0, 1});
        input.add(new int[]{0, 2});
        //input.add(new int[]{1, 0});
        input.add(new int[]{1, 2});
        input.add(new int[]{1, 3});
        input.add(new int[]{2, 0});
        input.add(new int[]{2, 1});
        input.add(new int[]{2, 4});
        input.add(new int[]{3, 1});
        input.add(new int[]{3, 4});
        input.add(new int[]{4, 2});
        input.add(new int[]{4, 3});
        input.add(new int[]{4, 5});
        input.add(new int[]{5, 4});
        input.add(new int[]{5, 6});
        input.add(new int[]{6, 5});

        int[][] matrix = new int[ARR_SIZE][ARR_SIZE];

        for (int i = 0; i < input.size(); i++) {
            int vert1 = input.get(i)[0];
            int vert2 = input.get(i)[1];
            matrix[vert1][vert2] = 1;
        }

//        for (int i = 0; i < matrix.length; i++) {
//            System.out.println(Arrays.toString(matrix[i]));
//        }

        int[] layer = new int[ARR_SIZE];
        boolean[] queued = new boolean[ARR_SIZE];
        Queue<Integer> queue = new LinkedList<Integer>();
        int[] colors = new int[ARR_SIZE];

        queue.add(START_NODE);
        queued[START_NODE] = true;
        layer[START_NODE] = 0;
        //colors[START_NODE] = COLOR_BLUE;
        boolean bipartite = true;

        while (!queue.isEmpty()) {
            int polled = queue.poll();
            System.out.println("polled: " + polled);
            for (int i = 0; i < ARR_SIZE; i++) {
                if (matrix[polled][i] == 1) {
                    if(queued[i] == false) {
                        queue.add(i);
                        queued[i] = true;
                        layer[i] = layer[polled] + 1;
                    }
                    if ((layer[i]) % 2 == 0)
                        colors[i] = COLOR_RED;
                    else
                        colors[i] = COLOR_BLUE;

                    if(colors[polled] == colors[i])
                        bipartite = false;
                }
            }
        }
        System.out.println("layer: " + Arrays.toString(layer));
        System.out.println("colors: " + Arrays.toString(colors));
        System.out.println("bipartite: " + bipartite);
    }
}
