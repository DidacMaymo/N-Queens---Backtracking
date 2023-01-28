import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class nQueens {
  public static void main(String[] args) {
    Solution solution = new Solution();
    List<String> result = solution.solveQueens(4);
    for (String row : result) {
      System.out.println(row);
    }
  }
}

class Solution {
  Set<Integer> column = new HashSet<Integer>();
  Set<Integer> positiveDiag = new HashSet<Integer>();
  Set<Integer> negativeDiag = new HashSet<Integer>();
  List<String> result = new ArrayList<>();
  String[][] board;
  String[] copy;

  public List<String> solveQueens(int n) {
    board = new String[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        board[i][j] = ".";
      }
    }
    backtrack(0, n);
    return result;
  }

  private void backtrack(int r, int n) {
    if (r == n) {
      copy = new String[board.length];
      for (String[] row : board) {
        String copy = String.join("", row);
        result.add(copy);
      }
      return;
    }

    for (int c = 0; c < n; c++) {
      if (column.contains(c) || positiveDiag.contains(r + c) || negativeDiag.contains(r - c))
        continue;
      column.add(c);
      positiveDiag.add(r + c);
      negativeDiag.add(r - c);
      board[r][c] = "Q";
      backtrack(r + 1, n);
      // Clean up
      column.remove(c);
      positiveDiag.remove(r + c);
      negativeDiag.remove(r - c);
      board[r][c] = ".";
    }
  }
}

/*
 * A better way to print
 * public static void main(String[] args) {
 * Solution solution = new Solution();
 * List<String> result = solution.solveQueens(4);
 * int count = 0;
 * for (String row : result) {
 * System.out.println(row);
 * count++;
 * if (count % 4 == 0) {
 * System.out.println();
 * }}}
 */
