# knights-tour
Knight’s Tour is a sequence of valid moves of a knight on a chessboard in such a way that the knight covers all the squares on the board. This is a Hamiltonian path problem in computer science which is NP-complete. There are two types of tours – open and closed. If the knight ends on a square from which it can start the tour again from the same square it began, the tour is said to be closed. Otherwise, it is said to be an open tour.

## Moves of a Knight
Moves of a Knight. The knight moves one square in one direction and two squares in the other in any order. Hence, if a knight is on a white square, it will end up on a black square and vice versa.

![Moves of a knight]()

## Algorithms to compute Knight’s Tour
There are many algorithmic techniques that can be used to compute the Knight’s tour on a chessboards of various dimensions such as brute-force, backtracking, divide and conquer, Warnsdorff’s rule, and also neural networks. This project focuses on two techniques - Warnsdorff’s rule and backtracking, and analyses their time complexity.

## Backtracking Algorithm
Backtracking is an algorithm strategy where we go on building the solution in increments. The algorithm discards an increment as soon as it does not lead to a solution and backtracks to try the other increments. In the knight’s tour, the algorithm adds each valid square to the solution and builds the path recursively. If this does not lead to a solution, the square is removed and the algorithm tries other valid alternate squares. It performs an exhaustive search in the worst case. The worst case time complexity of backtracking algorithm is O(8^(N^2)), where N is the dimension of the chessboard. The number 8 serves as the total number of possible valid moves from a given position on the board. The backtracking algorithm tries every possible set of moves in order to obtain a complete solution, hence we can use this method to prove that the tour exists starting from each and every position on the board. Due to computational limitations, we have had to restrict the board size to a maximum of 8×8.

Backtracking of a knight in an 8x8 board.
![Screenshot](8x8.jpg)

Backtracking in a 8x8 board.
![BAcktracking example]()

## Warnsdorff’s Rule
In 1823, H. C. von Warnsdorff proposed a very efficient heuristic for finding the knight’s tour. The rule is as follows – Let an adjacent square be the one on which the knight will land on by making a valid move from the current square. Let the rank of a square on the chessboard be the number of unvisited adjacent squares the knight can move next. The next square in the path is the unvisited adjacent square which has the least rank of all the other unvisited adjacent squares. Ties may or may not be broken at random. Since Warnsdorff’s rule is a heuristic, it cannot guarantee a complete tour starting from
every square. This is due to the random tie-breaks done while choosing the minimum rank when there is more than one minimum rank in order to traverse the knight to the next square. Warnsdorff’s rule finds a tour in linear time. The time complexity of this heuristic is O(N×N) where N is the dimension of the chessboard.

An example of Warnsdorff's heuristic on a 20x20 board.
![Warnsdorff's Rule on a 20x20 board]()
