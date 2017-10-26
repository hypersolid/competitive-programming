// package main
//
// import "fmt"
//
// func main() {
// 	fmt.Println(countBattleships([][]byte{
// 		[]byte("X..X"),
// 		[]byte("X..."),
// 		[]byte("XXX."),
// 	}))
// }

type Node struct {
	nodes  []*Node
	marked bool
}

func (n *Node) mark() {
	n.marked = true
	for _, sibling := range n.nodes {
		if !sibling.marked {
			sibling.mark()
		}
	}
}

func countBattleships(board [][]byte) int {
	memo := make([][]*Node, len(board)+1)
	for i := 0; i <= len(board); i++ {
		memo[i] = make([]*Node, len(board[0])+1)
	}

	for i := range board {
		for j := range board[i] {
			if board[i][j] == 'X' {
				weave(memo, i, j)
			}
		}
	}

	counter := 0
	for i := range memo {
		for j := range memo[i] {
			if memo[i][j] != nil && !memo[i][j].marked {
				memo[i][j].mark()
				counter++
			}
		}
	}

	return counter
}

func weave(memo [][]*Node, i, j int) {
	i++
	j++
	memo[i][j] = &Node{nodes: make([]*Node, 0)}
	if memo[i][j-1] != nil {
		memo[i][j].nodes = append(memo[i][j].nodes, memo[i][j-1])
		memo[i][j-1].nodes = append(memo[i][j-1].nodes, memo[i][j])
	}
	if memo[i-1][j] != nil {
		memo[i][j].nodes = append(memo[i][j].nodes, memo[i-1][j])
		memo[i-1][j].nodes = append(memo[i-1][j].nodes, memo[i][j])
	}
}
