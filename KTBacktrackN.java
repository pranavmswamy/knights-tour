import java.util.Scanner;

public class KTBacktrackN {
	
	int[][] chessBoard;
	long noOfIterations;
	int noOfVisitedSquares = 1;
	
	KTBacktrackN(int boardSize)
	{
		chessBoard = new int[boardSize][boardSize];
	}
	
	boolean validPosition(int x, int y)
	{
		return x >= 0 && y >= 0 && x < chessBoard.length && y < chessBoard.length && chessBoard[x][y] == 0;
	}
	
	int findNoOfChoices(int x, int y)
	{
		int count = 0;
		if(validPosition(x+2,y+1)) count++;
		if(validPosition(x+1,y+2)) count++;
		if(validPosition(x-2,y-1)) count++;
		if(validPosition(x-1,y-2)) count++;
		if(validPosition(x+2,y-1)) count++;
		if(validPosition(x-2,y+1)) count++;
		if(validPosition(x+1,y-2)) count++;
		if(validPosition(x-1,y+2)) count++;
		return count;	
	}
	
	boolean[] findPosition(int x, int y)
	{
		boolean[] validPositionArray = new boolean[8];
		
		if(validPosition(x-2,y+1))
		{
			validPositionArray[0] = true;
		}
		else
		{
			validPositionArray[0] = false;
		}
		
		if(validPosition(x-1,y+2)) 
		{
			validPositionArray[1] = true;
		}
		else
		{
			validPositionArray[1] = false;
		}
		
		if(validPosition(x+1,y+2))
		{
			validPositionArray[2] = true;
		}	
		else
		{
			validPositionArray[2] = false;
		}
		
		if(validPosition(x+2,y+1))
		{
			validPositionArray[3] = true;
		}
		else
		{
			validPositionArray[3] = false;
		}
		
		if(validPosition(x+2,y-1))
		{
			validPositionArray[4] = true;
		}
		else
		{
			validPositionArray[4] = false;
		}
		
		if(validPosition(x+1,y-2))
		{
			validPositionArray[5] = true;
		}
		else
		{
			validPositionArray[5] = false;
		}
		
		if(validPosition(x-1,y-2))
		{
			validPositionArray[6] = true;
		}
		else
		{
			validPositionArray[6] = false;
		}
		
		if(validPosition(x-2,y-1))
		{
			validPositionArray[7] = true;
		}
		else
		{
			validPositionArray[7] = false;
		}
		return validPositionArray;	
	}
	
	void printBoard()
	{
		for(int row = 0;row<chessBoard.length;row++)
		{
			for(int col = 0;col<chessBoard.length;col++)
				System.out.format("%d\t",chessBoard[row][col]);
			System.out.println();
		}
	}
	
	boolean traverse(int startRow, int startCol)
	{
		int j;
		boolean[] validPositionArray = new boolean[8];
		int nextRowMove = 0, nextColMove = 0;
		if(noOfVisitedSquares == chessBoard.length*chessBoard.length) //BASE CASE
		{
			return true;
		}
			
		noOfIterations++;
		/*System.out.println();
		System.out.println();*/
		//printBoard();
		//System.out.println();
		validPositionArray = findPosition(startRow, startCol);
		int noOfChoices = findNoOfChoices(startRow, startCol);
		
		/*for(int f = 0; f< validPositionArray.length; f++) System.out.println(validPositionArray[f]);
		System.out.println();
		System.out.println();*/
		
		j = 0;
		for(int i = 0; i < noOfChoices; i++) //loop for trying all possible choices
		{
			for(j = 0; j < validPositionArray.length; j++)
			{
				if(validPositionArray[j]) //trying the first immediate choice (breaks after switch)
				{
					switch(j)
					{
					case 0: nextRowMove =  startRow - 2;
							nextColMove = startCol + 1;
							//chessBoard[nextRowMove][nextColMove] = ++noOfVisitedSquares;
							break;
			
					case 1: nextRowMove =  startRow - 1;
							nextColMove = startCol + 2;
							//chessBoard[nextRowMove][nextColMove] = ++noOfVisitedSquares;
							break;
			
					case 2: nextRowMove =  startRow + 1;
							nextColMove = startCol + 2;
							//chessBoard[nextRowMove][nextColMove] = ++noOfVisitedSquares;
							break;
			
					case 3: nextRowMove =  startRow + 2;
							nextColMove = startCol + 1;
							//chessBoard[nextRowMove][nextColMove] = ++noOfVisitedSquares;
							break;
			
					case 4: nextRowMove =  startRow + 2;
							nextColMove = startCol - 1;
							//chessBoard[nextRowMove][nextColMove] = ++noOfVisitedSquares;
							break;
			
					case 5: nextRowMove =  startRow + 1;
							nextColMove = startCol - 2;
							//chessBoard[nextRowMove][nextColMove] = ++noOfVisitedSquares;
							break;
			
					case 6: nextRowMove =  startRow - 1;
							nextColMove = startCol - 2;
							//chessBoard[nextRowMove][nextColMove] = ++noOfVisitedSquares;
							break;
			
					case 7: nextRowMove =  startRow - 2;
							nextColMove = startCol - 1;
							//chessBoard[nextRowMove][nextColMove] = ++noOfVisitedSquares;
							break;
					}
					chessBoard[nextRowMove][nextColMove] = ++noOfVisitedSquares;
					break;
				}
			}
			
			if(traverse(nextRowMove, nextColMove))
				return true;
			else
			{
				chessBoard[nextRowMove][nextColMove] = 0;
				validPositionArray[j] = false;
				noOfVisitedSquares--;
				/*switch(j)
				{
				case 0: startRow += 2;
						startCol -= 1;
						break;
		
				case 1: startRow += 1;
						startCol -= 2;
						break;
		
				case 2: startRow -= 1;
						startCol -= 2;
						break;
		
				case 3:
						startRow -= 2;
						startCol -= 1;
						break;
		
				case 4: 
						startRow -= 2;
						startCol += 1;
						break;
		
				case 5: 
						startRow -= 1;
						startCol += 2;
						break;
		
				case 6: 
						startRow += 1;
						startCol += 2;
						break;
		
				case 7: startRow += 2;
						startCol += 1;
						break;
				}*/
			}
			
		}
		
		return false;
	}

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the board size: ");
		int boardSize = sc.nextInt();
		long startTime, endTime;
		while(true)
		{
			
		System.out.println("\n1. Input start position\n2. Calculate for all positions.\n3. Exit");
		int choice = sc.nextInt();
		switch(choice)
		{
		case 1: KTBacktrackN bt = new KTBacktrackN(boardSize);
				System.out.println("Enter the starting row- 0 to "+(boardSize-1)+": ");
				int startRow = sc.nextInt();
				System.out.println("Enter the starting column- 0 to "+(boardSize-1)+": ");
				int startCol = sc.nextInt();
				bt.chessBoard[startRow][startCol] = 1;
				System.out.println("The Knight's Tour is as follows: ");
				startTime = System.currentTimeMillis();
				if(!bt.traverse(startRow,startCol))
				{
					System.out.println("Tour not found.");
					bt.printBoard();
				}
				else
				{
					bt.printBoard();
				}
				endTime = System.currentTimeMillis();
				System.out.println("No of iterations: " + bt.noOfIterations);
				System.out.println("Total time = " + (endTime - startTime) + "ms");
				break;
				
		case 2: System.out.println("The Knight's Tour is as follows: ");
				for(int i = 0;i<boardSize;i++)
					for(int j=0;j< boardSize; j++)
					{
						System.out.println("(" + i + "," + j +"):");
						KTBacktrackN obj = new KTBacktrackN(boardSize);
						obj.chessBoard[i][j] = 1;
						startTime = System.currentTimeMillis();
						if(!obj.traverse(i,j))
						{
							System.out.println("Tour not found.");
							obj.printBoard();
						}
						else
						{
							obj.printBoard();
						}
						endTime = System.currentTimeMillis();
				
						System.out.println("No of iterations: " + obj.noOfIterations);
						System.out.println("Total time = " + (endTime - startTime) + "ms");
					}
				break;
						
		case 3:	System.exit(0);
		}
		}
	}
	
}