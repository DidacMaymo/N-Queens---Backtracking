using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Program
{
    class Program
    {
        static void Main(string[] args)
        {
            Solution solution = new Solution();
            List<string> result = solution.solveQueens(4);
            foreach (string row in result)
            {
                Console.WriteLine(row);
            }
            Console.ReadKey();
        }
    }

    class Solution
    {
        HashSet<int> column = new HashSet<int>();
        HashSet<int> positiveDiag = new HashSet<int>();
        HashSet<int> negativeDiag = new HashSet<int>();
        List<string> result = new List<string>();

        public List<string> solveQueens(int n) 
        {
            string[,] board = new string[n, n];
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    board[i, j] = ".";
                }
            }
            backtrack(0, n,board);
            return result;
        }

        private void backtrack(int r, int n, string[,] board)
        {
            if (r == n)
            {
                for (int i = 0; i < n; i++)
                {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < n; j++)
                    {
                        sb.Append(board[i, j]);
                    }
                    result.Add(sb.ToString());
                }
                return;
            }

            for (int c = 0; c < n; c++)
            {
                if (column.Contains(c) || positiveDiag.Contains(r + c) || negativeDiag.Contains(r - c))
                    continue;
                column.Add(c);
                positiveDiag.Add(r + c);
                negativeDiag.Add(r - c);
                board[r, c] = "Q";
                backtrack(r + 1, n, board);
                // Clean up
                column.Remove(c);
                positiveDiag.Remove(r + c);
                negativeDiag.Remove(r - c);
                board[r, c] = ".";
            }
        }
    }
}