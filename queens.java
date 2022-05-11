package queen_lbs;

import java.util.Comparator;
import java.util.PriorityQueue;

public class queens {
	char[][] board;
	int score;
	int N;
	int k;
	queens[] q;
	int countChild;
	
	 queens(char[][]b,int k_){
		 board=b;
		 N=b.length;
		 k=k_;
		 score=hyuristic();
		 q=new queens[k];
		 countChild=0;
	 }
	 public char[][] makeBoard(int x,int y) {
		 char [][] temp=new char[N][N];
		 for (int i=0;i<N;i++) {
			 for(int j=0;j<N;j++) {
			     if(x==i&&y==j)
				   temp[i][j]='Q';
			     else if(j==y)
			    	 temp[i][j]='X';
			     else
			    	 temp[i][j]=board[i][j];
		 }
		 }
		 return temp;
	 }
	 public void print() {
		 System.out.println("-----------");
		 System.out.println();
		 for(int i=0;i<N;i++) {
			 for (int j = 0; j < N; j++) {
				System.out.print(board[i][j]);
			}
			 System.out.println();
		 }
		 System.out.println();
		 System.out.println("Score = "+score);
		 System.out.println("-----------");
	 }
	 public int hyuristic() {
		int s=0;
		for (int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(board[i][j]=='Q') {
					for(int k=1;k<N;k++) {
						if((j+k<N)&&board[i][j+k]=='Q')
							s++;
						 if((i+k<N)&&board[i+k][j]=='Q')
							s++;
						 if((i+k<N)&&(j+k<N)&&board[i+k][j+k]=='Q')
							s++;
						 if((i-k>=0)&&(j+k<N)&&board[i-k][j+k]=='Q')
							s++;
					}
				}
			}
		}
		return s;
	 }
	 public void makeChild() {
		 PriorityQueue<queens> maxHeap = new PriorityQueue<queens>(new Comparator<queens>() {
		        @Override
		        public int compare(queens o1, queens o2) {
		        	if(o1.score>o2.score)
		        		return -1;
		        	else if(o1.score<o2.score)
		        		return 1;
		        	else 
		        		return 0;
		        }
		    });
		 for(int i=0;i<N;i++) {
			 for (int j=0;j<N;j++) {
				 if(board[i][j]!='Q') {
					 countChild++;
					 if(countChild<=k) {
						 maxHeap.add(new queens(makeBoard(i, j), k));
					 }
					 else {
						 queens temp=maxHeap.poll();
						 queens q_new=new queens(makeBoard(i, j), k);
						 if(temp.score<q_new.score) {
							 maxHeap.add(temp);
						 }
						 else {
							 maxHeap.add(q_new);
						 }
					 }
			 }	 
		 }
		 }
		 for ( int i=0;i<k;i++) {
			 q[i]=maxHeap.poll();
		 }
	 }
}