from typing import List
class Solution:
    def solveQueens(self, n: int) -> List[List[str]]:
        col = set()
        posDiag = set()  # (r+c)
        negDiag = set()  # (r-c)

        res = []
        #We create a board of n + n :
        board = [["."] * n for i in range(n)]

        def backtrack(r):  # We go row per row
            if r == n:  # true when we are completed
                copy = ["".join(row) for row in board]
                res.append(copy)
                return
            for c in range(n):  # For each position(column) in the row
                # We look if current column, positive or negative diagonals have been already used
                #We use continue as we are not allowed to use this column in the True case.
                if c in col or (r+c) in posDiag or (r-c) in negDiag:
                    continue

                col.add(c)
                posDiag.add(r+c)
                negDiag.add(r-c)
                board[r][c] = "Q"
                backtrack(r + 1)  # Next row

                #We do a clean up in case we go back to search for all possible solutions!
                col.remove(c)
                posDiag.remove(r+c)
                negDiag.remove(r-c)
                board[r][c] = "."
                #We do not have a return in this loop so once it is done means the function finished
        backtrack(0)
        return res

solver = Solution()
result = solver.solveQueens(4)
print(result)
for config in result:
    for row in config:
        print(row)
    print("\n")

