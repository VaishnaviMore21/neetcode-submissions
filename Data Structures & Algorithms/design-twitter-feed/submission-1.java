
class Twitter {

    Map<Integer, Set<Integer>> followMap;
    Map<Integer, List<int[]>> tweetMap;
    int time;

    public Twitter() {
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
        time = 0;
    }

    public void postTweet(int userId, int tweetId) {

        tweetMap.putIfAbsent(userId, new ArrayList<>());

        tweetMap.get(userId).add(new int[]{time++, tweetId});
    }

    public List<Integer> getNewsFeed(int userId) {

        List<Integer> result = new ArrayList<>();

        // Max heap: latest tweet first
        PriorityQueue<int[]> pq =
            new PriorityQueue<>((a, b) -> b[0] - a[0]);

        // Add own latest tweet
        addLatestTweet(userId, pq);

        // Add latest tweet of every followee
        if (followMap.containsKey(userId)) {

            for (int followee : followMap.get(userId)) {
                addLatestTweet(followee, pq);
            }
        }

        // Get 10 latest tweets
        while (!pq.isEmpty() && result.size() < 10) {

            int[] current = pq.poll();

            int tweetId = current[1];
            int user = current[2];
            int index = current[3];

            result.add(tweetId);

            // Add next older tweet from same user
            if (index > 0) {

                int[] next = tweetMap.get(user).get(index - 1);

                pq.offer(new int[]{
                    next[0],
                    next[1],
                    user,
                    index - 1
                });
            }
        }

        return result;
    }

    private void addLatestTweet(int userId, PriorityQueue<int[]> pq) {

        if (!tweetMap.containsKey(userId)) {
            return;
        }

        List<int[]> tweets = tweetMap.get(userId);

        int index = tweets.size() - 1;

        int[] tweet = tweets.get(index);

        pq.offer(new int[]{
            tweet[0],
            tweet[1],
            userId,
            index
        });
    }

    public void follow(int followerId, int followeeId) {

        // A user cannot follow themselves
        if (followerId == followeeId) {
            return;
        }

        followMap.putIfAbsent(followerId, new HashSet<>());

        followMap.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {

        if (followMap.containsKey(followerId)) {
            followMap.get(followerId).remove(followeeId);
        }
    }
}