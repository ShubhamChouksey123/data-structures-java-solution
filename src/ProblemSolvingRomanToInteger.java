import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1, 1, 0, 0
 * 1, 1, 0, 1
 * 1, 1, 1, 1
 * 1, 1, 1, 1
 */

public class ProblemSolvingRomanToInteger {

    private static final int offset[] = {-1, 0, 1};
    private static final int xOffset[] = {1, 0, -1, 0};
    private static final int yOffset[] = {0, 1, 0, -1};

    private static void print(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    private static boolean liesInRange(int x, int y, int n, int m){
        if(x >=0 && x < n && y >= 0 && y < m){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        int[][] matrix = new int[][]{{1, 1, 0, 0}, {1, 1, 0, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};
//        int[][] matrix = new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        updateMatrix( matrix);

    }

    public static int[][] updateMatrix(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;

        int[][] minDist = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                minDist[i][j] = -1;
            }
        }

        Queue<Coordinate> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    minDist[i][j] = 0;
                    for (int k = 0; k < xOffset.length; k++) {
                        if (liesInRange(i + xOffset[k] , j + yOffset[k] ,  n ,  m) && mat[i + xOffset[k]][j + yOffset[k]] == 1) {
                            Coordinate coordinate = new Coordinate(i + xOffset[k], j + yOffset[k], 1);
                            queue.add(coordinate);
                        }
                    }
                }
            }
        }


        System.out.println("queue : " + queue);

        while(queue.size() > 0){

            Coordinate coordinate = queue.remove();
            Integer x = coordinate.getX();
            Integer y = coordinate.getY();
            Integer dist = coordinate.getDist();

            if(minDist[x][y] == -1){
                minDist[x][y] = dist;
                for (int k = 0; k < xOffset.length; k++) {
                    if (liesInRange(x + xOffset[k] , y + yOffset[k] ,  n ,  m) && minDist[x + xOffset[k]][y + yOffset[k]] == -1) {
                        Coordinate coordinateNeighbour = new Coordinate(x + xOffset[k], y + yOffset[k], dist + 1);
                        queue.add(coordinateNeighbour);
                    }
                }
            }
        }

        print( minDist);
        return minDist;
    }



}
