

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // Adjacency list
        List<List<Integer>> adj = new ArrayList<>();

        // In-degree of every course
        int[] inDegree = new int[numCourses];

        // Initialize adjacency list
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        // Build graph
        for (int[] pre : prerequisites) {

            int course = pre[0];
            int prerequisite = pre[1];

            adj.get(prerequisite).add(course);

            inDegree[course]++;
        }

        // Courses with no prerequisites
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        int count = 0;

        // BFS
        while (!q.isEmpty()) {

            int node = q.poll();
            count++;

            for (int nei : adj.get(node)) {

                inDegree[nei]--;

                if (inDegree[nei] == 0) {
                    q.offer(nei);
                }
            }
        }

        return count == numCourses;
    }
}