class Solution {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();

        char[][] board = new char[n][n];

        // Initialize board with '.'
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        nQueens(board, 0, result);

        return result;
    }

    public void nQueens(char[][] board, int row, List<List<String>> result) {

        // Base Case
        if (row == board.length) {
            result.add(construct(board));
            return;
        }

        // Try every column
        for (int col = 0; col < board.length; col++) {

            if (isSafe(board, row, col)) {

                board[row][col] = 'Q';      // Place Queen

                nQueens(board, row + 1, result); // Recursive Call

                board[row][col] = '.';      // Backtracking
            }
        }
    }

    public boolean isSafe(char[][] board, int row, int col) {

        // Check Vertical
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q')
                return false;
        }

        // Check Left Diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q')
                return false;
        }

        // Check Right Diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q')
                return false;
        }

        return true;
    }

    public List<String> construct(char[][] board) {

        List<String> temp = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            temp.add(new String(board[i]));
        }

        return temp;
    }
}