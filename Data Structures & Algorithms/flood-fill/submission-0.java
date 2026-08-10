
class Solution {

    private void dfs(int row, int col,
                     int[][] image,
                     int oldColor,
                     int newColor) {

        int n = image.length;
        int m = image[0].length;

        // Change current cell
        image[row][col] = newColor;

        // 4 directions: up, right, down, left
        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};

        for (int i = 0; i < 4; i++) {

            int nRow = row + dRow[i];
            int nCol = col + dCol[i];

            // Check boundary and old color
            if (nRow >= 0 && nRow < n &&
                nCol >= 0 && nCol < m &&
                image[nRow][nCol] == oldColor) {

                dfs(nRow, nCol, image, oldColor, newColor);
            }
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        int oldColor = image[sr][sc];

        // Important edge case
        if (oldColor == color) {
            return image;
        }

        dfs(sr, sc, image, oldColor, color);

        return image;
    }
}