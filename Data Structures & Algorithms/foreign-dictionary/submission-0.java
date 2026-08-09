

class Solution {

    public String foreignDictionary(String[] words) {

        int K = 26;

        // 1. Create adjacency list
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            adj.add(new ArrayList<>());
        }

        // 2. In-degree array
        int[] inDegree = new int[K];

        // 3. Track characters present in input
        boolean[] present = new boolean[K];

        int uniqueCount = 0;

        for (String word : words) {

            for (char c : word.toCharArray()) {

                if (!present[c - 'a']) {
                    present[c - 'a'] = true;
                    uniqueCount++;
                }
            }
        }

        // 4. Build graph by comparing adjacent words
        for (int i = 0; i < words.length - 1; i++) {

            String s1 = words[i];
            String s2 = words[i + 1];

            int len = Math.min(s1.length(), s2.length());

            boolean foundDiff = false;

            for (int j = 0; j < len; j++) {

                // Find first different character
                if (s1.charAt(j) != s2.charAt(j)) {

                    int u = s1.charAt(j) - 'a';
                    int v = s2.charAt(j) - 'a';

                    // u comes before v
                    adj.get(u).add(v);

                    inDegree[v]++;

                    foundDiff = true;

                    // Only first difference matters
                    break;
                }
            }

            // Invalid prefix case
            if (!foundDiff && s1.length() > s2.length()) {
                return "";
            }
        }

        // 5. Create queue
        Queue<Integer> q = new LinkedList<>();

        // 6. Add all characters having inDegree = 0
        for (int i = 0; i < K; i++) {

            if (present[i] && inDegree[i] == 0) {
                q.offer(i);
            }
        }

        // 7. Topological sort
        StringBuilder ans = new StringBuilder();

        while (!q.isEmpty()) {

            int node = q.poll();

            // Convert number back to character
            ans.append((char) (node + 'a'));

            // Process neighbors
            for (int nei : adj.get(node)) {

                inDegree[nei]--;

                // When inDegree becomes 0,
                // add it to queue
                if (inDegree[nei] == 0) {
                    q.offer(nei);
                }
            }
        }

        // 8. Cycle detection
        if (ans.length() != uniqueCount) {
            return "";
        }

        return ans.toString();
    }
}