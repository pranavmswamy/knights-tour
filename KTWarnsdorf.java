import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class KTWarnsdorf {

	int[][] chessBoard;
	long noOfIterations;
	int noOfVisitedSquares = 1;
	static int noTourCount = 0;
	KTWarnsdorf(int boardSize)
	{
		chessBoard = new int[boardSize][boardSize];
	}
	
	boolean validPosition(int x, int y)
	{
		return x >= 0 && y >= 0 && x < chessBoard.length && y < chessBoard.length && chessBoard[x][y] == 0;
	}
	
	int findRank(int x, int y)
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
	
	int[] getRankArray(int row, int col)
	{
		int[] rankArray = new int[8];
		
		if(validPosition(row-2,col+1))
		{
			rankArray[0] = findRank(row-2,col+1);
		}
		else
		{
			rankArray[0] = 9;
		}
		
		if(validPosition(row-1,col+2)) 
		{
			rankArray[1] = findRank(row-1,col+2);
		}
		else
		{
			rankArray[1] = 9;
		}
		
		if(validPosition(row+1,col+2))
		{
			rankArray[2] = findRank(row+1,col+2);
		}	
		else
		{
			rankArray[2] = 9;
		}
		
		if(validPosition(row+2,col+1))
		{
			rankArray[3] = findRank(row+2,col+1);
		}
		else
		{
			rankArray[3] = 9;
		}
		
		if(validPosition(row+2,col-1))
		{
			rankArray[4] = findRank(row+2,col-1);
		}
		else
		{
			rankArray[4] = 9;
		}
		
		if(validPosition(row+1,col-2))
		{
			rankArray[5] = findRank(row+1,col-2);
		}
		else
		{
			rankArray[5] = 9;
		}
		
		if(validPosition(row-1,col-2))
		{
			rankArray[6] = findRank(row-1,col-2);
		}
		else
		{
			rankArray[6] = 9;
		}
		
		if(validPosition(row-2,col-1))
		{
			rankArray[7] = findRank(row-2,col-1);
		}
		else
		{
			rankArray[7] = 9;
		}
		
		return rankArray;
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
	
	void traverse(int startRow, int startCol)
	{
		
		chessBoard[startRow][startCol] = 1;
		int[] rankArray = new int[8];
		int currentRow = startRow, currentCol = startCol, minRank, minPosition;
		//minPosition is to record which position in the array the minimum value is present so that we can move to that position.
		while(noOfVisitedSquares != chessBoard.length*chessBoard.length)
		{
			noOfIterations++;
			rankArray = getRankArray(currentRow,currentCol);
			
			/*System.out.println();
			for(int k = 0; k< rankArray.length;k++) System.out.print(rankArray[k] + " ");
			System.out.println();*/
			
			minRank = rankArray[0];
			minPosition = 0;
			for(int i = 0; i<rankArray.length;i++)
			{
				if(rankArray[i] < minRank)
				{
					minRank = rankArray[i];
					minPosition = i;
				}	
			}
			
			//-------------------------------randomizing
			ArrayList<Integer> minRankArrayList = new ArrayList<Integer>();
			for(int i = 0;i<rankArray.length;i++)
			{
				if(rankArray[i] == minRank)
				{
					minRankArrayList.add(i);
				}
			}
			//System.out.println("minRankArrayList = " + minRankArrayList);
			int minPositionIndex = new Random().nextInt(minRankArrayList.size());
			minPosition = minRankArrayList.get(minPositionIndex);
			//System.out.println("Min position index = " + minPositionIndex);
			
			//------------------------------------
			
			if(minRank == 9)
			{
				//noTourCount++;
				System.out.println("No minimum rank found.");
				return;
				
			}
			
			switch(minPosition)
			{
			case 0:	currentRow -= 2;
					currentCol += 1;
					break;
			case 1: currentRow -= 1;
					currentCol += 2;
					break;
			case 2:	currentRow += 1;
					currentCol += 2;
					break;
			case 3: currentRow += 2;
					currentCol += 1;
					break;
			case 4:	currentRow += 2;
					currentCol -= 1;
					break;
			case 5:	currentRow += 1;
					currentCol -= 2;
					break;
			case 6: currentRow -= 1;
					currentCol -= 2;
					break;
			case 7: currentRow -= 2;
					currentCol -= 1;
					break;
			}
			
			chessBoard[currentRow][currentCol] = ++noOfVisitedSquares;	
		}
		System.out.println();
		//System.out.println("No of squares visited: " + noOfVisitedSquares);
		//System.out.println();
		printBoard();
		//System.out.println();	
		//System.out.println("No. of squares where no tours possible: " + noTourCount);
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
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
		case 1: KTWarnsdorf obj = new KTWarnsdorf(boardSize);
				System.out.println("Enter the starting row- 0 to "+(boardSize-1)+": ");
				int startRow = sc.nextInt();
				System.out.println("Enter the starting column- 0 to "+(boardSize-1)+": ");
				int startCol = sc.nextInt();
				System.out.println("The Knight's Tour is as follows: ");
				startTime = System.currentTimeMillis();
				obj.traverse(startRow, startCol);
				endTime = System.currentTimeMillis();
				System.out.println("No of iterations: " + obj.noOfIterations);
				System.out.println("Time taken: "+(endTime-startTime)+"ms.");
				break;
		case 2: System.out.println("The Knight's Tour is as follows: ");
				int totalwhilecount = 0;
				long totalStartTime = System.currentTimeMillis();
				for(int i = 0;i<boardSize;i++)
				{
					for(int j=0;j< boardSize; j++)
					{
							System.out.println("(" + i + "," + j +"):");
							//int whilecount = 0;
							KTWarnsdorf obj1 = new KTWarnsdorf(boardSize);
							//startTime = System.currentTimeMillis();
							while(obj1.noOfVisitedSquares != obj1.chessBoard.length*obj1.chessBoard.length)
							{
								//whilecount++;
								//totalwhilecount++;
								obj1.noOfVisitedSquares = 1;
								for(int l = 0; l< obj1.chessBoard.length; l++)
								{
									for(int m = 0; m< obj1.chessBoard.length; m++)
										obj1.chessBoard[l][m] = 0;
								}
								obj1.traverse(i,j);
							}
						
						//obj1.traverse(i,j);
					/*	endTime = System.currentTimeMillis();
						
						System.out.println("No of iterations: " + obj1.noOfIterations);
						System.out.println("Total time = " + (endTime - startTime) + "ms");
						System.out.println("Whilecount = " + whilecount);
						System.out.println("Total while count = " + totalwhilecount);
					
						System.out.println("----------------------------------------");*/
					}
				}
				long totalEndTime = System.currentTimeMillis();
				System.out.println("Total time taken = " + (totalEndTime - totalStartTime) + " ms.");
					
				break;
		case 3: sc.close();
				System.exit(0);
		}
		}
}
}
