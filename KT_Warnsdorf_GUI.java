import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class KT_Warnsdorf_GUI implements Runnable, ActionListener {

	long noOfIterations;
	int noOfVisitedSquares = 1;
	int sleepTime, BOARD_PIXELS = 800;
	int startRow, startCol;
	
	private JFrame chessBoard;
	ArrayList<ArrayList<JButton>> cell = new ArrayList<ArrayList<JButton>>();
	
	KT_Warnsdorf_GUI(int boardSize)
	{
		if(boardSize < 10)
		{
			sleepTime = 400;
		}
		else
		{
			sleepTime = 25;
		}
		
		chessBoard = new JFrame("Knight's Tour");
		chessBoard.setResizable(false);
		//chessBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chessBoard.setSize(BOARD_PIXELS,BOARD_PIXELS);
		//cell = new JButton[boardSize][boardSize];
		chessBoard.setLayout(new GridLayout(boardSize,boardSize));
		
		for(int i = 0; i<boardSize; i++)
		{
			ArrayList<JButton> eightCells = new ArrayList<JButton>();
			for(int j = 0; j<boardSize; j++)
			{
				JButton square = new JButton();
				eightCells.add(square);
				chessBoard.add(square);
				square.addActionListener(this);
			}
			cell.add(eightCells);
		}
			
		
		//adding color to buttons
		for(int i = 0; i< boardSize;i++)
		{
			for(int j = 0; j< boardSize;j++)
			{
				if((i+j) % 2 != 0)
				{
					cell.get(i).get(j).setBackground(Color.WHITE);
				}
				else
					cell.get(i).get(j).setBackground(Color.BLACK);
			}
		}
		
		chessBoard.setVisible(true);
	}
	
	boolean validPosition(int x, int y)
	{
		return x >= 0 && y >= 0 && x < cell.size() && y < cell.size() && cell.get(x).get(y).getText().equals("");
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
	
	void traverse(int startRow, int startCol)
	{
		cell.get(startRow).get(startCol).setText(Integer.toString(1));
		cell.get(startRow).get(startCol).setBackground(Color.RED);
	
		ImageIcon knight = new ImageIcon("E:\\Knight_Chess.gif");
		Image img = knight.getImage() ;  
		Image scaledKnight = img.getScaledInstance( cell.get(0).get(0).getWidth(),cell.get(0).get(0).getHeight(),  java.awt.Image.SCALE_SMOOTH ) ;  
		knight = new ImageIcon(scaledKnight);
		
		cell.get(startRow).get(startCol).setIcon(knight);
		/*try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}*/
		cell.get(startRow).get(startCol).setIcon(null);
		
		int[] rankArray = new int[8];
		int currentRow = startRow, currentCol = startCol, minRank, minPosition;
		//minPosition is to record which position in the array the minimum value is present so that we can move to that position.
		while(noOfVisitedSquares != cell.size()*cell.size())
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
			
			cell.get(currentRow).get(currentCol).setText(Integer.toString(++noOfVisitedSquares));
			cell.get(currentRow).get(currentCol).setIcon(knight);
			cell.get(currentRow).get(currentCol).setBackground(Color.ORANGE);
		
			/*try 
			{
				Thread.sleep(sleepTime);
			} 
			catch(InterruptedException e) 
			{
				e.printStackTrace();
			}*/
			cell.get(currentRow).get(currentCol).setIcon(null);
			
		}
		cell.get(currentRow).get(currentCol).setBackground(Color.GREEN);
		cell.get(currentRow).get(currentCol).setIcon(knight);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		// TODO Auto-generated method stub
		
		
		for(int i = 0; i< cell.size();i++)
		{
			for(int j = 0; j< cell.size(); j++)
			{
				cell.get(i).get(j).setEnabled(false);
				if(ae.getSource() == cell.get(i).get(j))
				{
					startRow = i;
					startCol = j;
				}
			}
		}
		
		new Thread(this).start();
		
	}

	@Override
	public void run() 
	{
		long startTime = 0, endTime = 0, timeTaken = 0;
		
		startTime = System.currentTimeMillis();
		/*while(noOfVisitedSquares != cell.size()*cell.size())
		{
			
			noOfVisitedSquares = 1;
			for(int i = 0; i< cell.size(); i++)
			{
				for(int j = 0; j< cell.size(); j++)
					cell.get(i).get(j).setText("");
			}*/
			traverse(startRow,startCol);
		//}
		endTime = System.currentTimeMillis();
		timeTaken = endTime - startTime;
		System.out.println("Time taken Warnsdorff: " + timeTaken + " ms");
		
	}


}
