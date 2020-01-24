import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class KT_Backtracking_GUI implements Runnable, ActionListener
{

	long noOfIterations;
	int noOfVisitedSquares = 1;
	int sleepTime, BOARD_PIXELS = 800;
	int firstRow, firstCol;
	long backtrackCount;
	long startTime = 0, endTime = 0, timeTaken = 0, startTimeNano = 0;
	private JFrame chessBoard;
	private JButton[][] cell;
	
	private ImageIcon knight; 
	private Image img, scaledKnight;
	
	
	KT_Backtracking_GUI(int boardSize)
	{
		cell = new JButton[boardSize][boardSize];
		
		if(boardSize < 10)
		{
			sleepTime = 1000;
		}
		else
		{
			sleepTime = 50;
		}
		
		chessBoard = new JFrame("Knight's Tour");
		chessBoard.setResizable(false);
		chessBoard.setSize(BOARD_PIXELS,BOARD_PIXELS);
		chessBoard.setLayout(new GridLayout(boardSize,boardSize));
		
		for(int i = 0;i<cell.length;i++)
		{
			for(int j = 0;j<cell.length;j++)
			{
				cell[i][j] = new JButton();
				chessBoard.add(cell[i][j]);
				cell[i][j].addActionListener(this);
				
				if((i+j)%2 != 0)
					cell[i][j].setBackground(Color.WHITE);
				else
					cell[i][j].setBackground(Color.BLACK);
			}
		}
		
		chessBoard.setVisible(true);
		
		knight = new ImageIcon("E:\\Knight_Chess.gif");
		img = knight.getImage() ;  
		scaledKnight = img.getScaledInstance( cell[0][0].getWidth(),cell[0][0].getHeight(), java.awt.Image.SCALE_SMOOTH ) ;  
		knight = new ImageIcon(scaledKnight);
	}
	
	boolean validPosition(int x, int y)
	{
		return x >= 0 && y >= 0 && x < cell.length && y < cell.length && cell[x][y].getText().equals("");
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
	
	boolean traverse(int startRow, int startCol)
	{
		int j;
		
		boolean[] validPositionArray = new boolean[8];
		int nextRowMove = 0, nextColMove = 0;
		if(noOfVisitedSquares == cell.length*cell.length) //BASE CASE
		{
			cell[startRow][startCol].setIcon(knight);
			cell[startRow][startCol].setBackground(Color.GREEN);
			return true;
		}
			
		noOfIterations++;
		
		validPositionArray = findPosition(startRow, startCol);
		int noOfChoices = findNoOfChoices(startRow, startCol);
		
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
					cell[nextRowMove][nextColMove].setText(Integer.toString(++noOfVisitedSquares));
					cell[nextRowMove][nextColMove].setBackground(Color.ORANGE);
					cell[nextRowMove][nextColMove].setIcon(knight);
					/*/-----------------------------------------------------------------
					if(noOfVisitedSquares > 23)
					{
						sleepTime = 1500;
					}
					else
					{
						sleepTime = 0;
					}
					//------------------------------------------------------------------delete later, expt.*/
					/*try 
					{
						Thread.sleep(sleepTime);
					} 
					catch (InterruptedException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					
					cell[nextRowMove][nextColMove].setIcon(null);
					
					break;
				}
			}
			
			if(traverse(nextRowMove, nextColMove))
				return true;
			else
			{
				long ttForBt = System.nanoTime() - startTimeNano; 
				if(backtrackCount == 0)
					System.out.println("Time taken for " + ++backtrackCount + " backtrack = " + ttForBt + " nanoseconds." );
				cell[nextRowMove][nextColMove].setBackground(Color.CYAN);
				/*try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				cell[nextRowMove][nextColMove].setText("");
				validPositionArray[j] = false;
				noOfVisitedSquares--;
				cell[nextRowMove][nextColMove].setIcon(null);
				if((nextRowMove+nextColMove)%2 != 0)
					cell[nextRowMove][nextColMove].setBackground(Color.WHITE);
				else
					cell[nextRowMove][nextColMove].setBackground(Color.BLACK);
			
			}
			
		}
		
		return false;
	}

	
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		// TODO Auto-generated method stub

		for(int i = 0; i< cell.length;i++)
		{
			for(int j = 0; j< cell.length; j++)
			{
				cell[i][j].setEnabled(false);
				if(ae.getSource() == cell[i][j])
				{
					firstRow = i;
					firstCol = j;
				}
			}
		}
		
		new Thread(this).start();
	}

	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		cell[firstRow][firstCol].setText(Integer.toString(1));
		cell[firstRow][firstCol].setBackground(Color.RED);
		cell[firstRow][firstCol].setIcon(knight);
		/*try 
		{
			Thread.sleep(750);
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		cell[firstRow][firstCol].setIcon(null);
		
		startTimeNano = System.nanoTime();
		startTime = System.currentTimeMillis();
		traverse(firstRow,firstCol);
		endTime = System.currentTimeMillis();
		timeTaken = endTime - startTime;
		System.out.println("Time taken using Backtracking: " + timeTaken + " ms\n");
	}
	
}
