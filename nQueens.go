package Main

import (
	"fmt"
	"strings"
)

func main() {
	solver := Solution{}
	result := solver.solveQueens(4)
	fmt.Println(result)
	for _, config := range result {
		for _, row := range config {
			fmt.Println(row)
		}
		fmt.Println()
	}
}

type Solution struct {
	col     map[int]bool
	posDiag map[int]bool
	negDiag map[int]bool
	res     [][]string
	board   [][]string
}

func (s *Solution) solveQueens(n int) [][]string {
	s.col = make(map[int]bool)
	s.posDiag = make(map[int]bool)
	s.negDiag = make(map[int]bool)
	s.board = make([][]string, n)
	for i := 0; i < n; i++ {
		s.board[i] = make([]string, n)
		for j := 0; j < n; j++ {
			s.board[i][j] = "."
		}
	}
	s.backtrack(0, n)
	return s.res
}

func (s *Solution) backtrack(r int, n int) {
	if r == n {
		copy := make([]string, len(s.board))
		for i, row := range s.board {
			copy[i] = strings.Join(row, "")
		}
		s.res = append(s.res, copy)
		return
	}

	for c := 0; c < n; c++ {
		if s.col[c] || s.posDiag[r+c] || s.negDiag[r-c] {
			continue
		}
		s.col[c] = true
		s.posDiag[r+c] = true
		s.negDiag[r-c] = true
		s.board[r][c] = "Q"
		s.backtrack(r+1, n)

		s.col[c] = false
		s.posDiag[r+c] = false
		s.negDiag[r-c] = false
		s.board[r][c] = "."
	}
}
