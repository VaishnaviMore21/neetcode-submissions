
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {

            // Convert string to character array
            char[] arr = s.toCharArray();

            // Sort the characters
            Arrays.sort(arr);

            // Convert sorted array back to string
            String key = String.valueOf(arr);

            // If key doesn't exist, create a new list
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }

            // Add original string to its anagram group
            map.get(key).add(s);
        }

        // Return all grouped anagrams
        return new ArrayList<>(map.values());
    }
}