package Graph;

import java.util.Arrays;

public class GraphUse {
	
	
	// TC = O(n^3)
	// SC = O(n^2), If given is not counted
	
	// Floyd's Warshell algorithm
 	public int findTheCity(int n, int[][] edges, int distanceThreshold) {
 		
        // Use floyd's warshell to build shortest path matrix
        // adjMatrix[i][j] is the shortest path from i to j
        int adjMatrix[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(adjMatrix[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            int dist = edges[i][2];

            adjMatrix[from][to] = dist;
            adjMatrix[to][from] = dist;
        }


        for (int i = 0; i < n; i++) {
            adjMatrix[i][i] = 0;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (adjMatrix[i][k] == Integer.MAX_VALUE || adjMatrix[k][j] == Integer.MAX_VALUE)
                        continue;
                    adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
                }
            }
        }

        int cityCount = Integer.MAX_VALUE;
        int city = -1;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (adjMatrix[i][j] <= distanceThreshold) {
                    count++;
                }
            }

            if (count <= cityCount) {
                cityCount = count;
                city = i;
            }
        }

        return city;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
