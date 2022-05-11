
package queen_lbs;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

public class main {
	public static char[][] makemat(int [] temp) {
		char[][] mat=new char[temp.length][temp.length];
		for(int i=0;i<temp.length;i++) {
			for(int j=0; j<temp.length;j++) {
				if(j==temp[i])
					mat[j][i]='Q';
				else
					mat[j][i]='X';
			}
		}
		return mat;
		
	}

	public static void main(String[] args) {
		Random rand = new Random();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter size of the board(N):");
		int n=sc.nextInt();
		System.out.println("Enter number of iterations(X):");
		int x=sc.nextInt();
		System.out.println("Enter number of tracking(K):");
		int k=sc.nextInt();
		while (k>(Math.pow(n,2)-n)) {
			System.out.println("Enter number of tracking(K) again:");
			k=sc.nextInt();
		}
		int [] temp=new int [n];
		queens[] arrayQueens=new queens[k];
		int maxScore=Integer.MAX_VALUE;
		System.out.println("ITERATION 0:");
		for(int i=0;i<k;i++) {
			for(int j=0; j<n;j++) {
				temp[j]=rand.nextInt(n);
			}
			arrayQueens[i]=new queens(makemat(temp), k);
			if(arrayQueens[i].score<maxScore)
				maxScore=arrayQueens[i].score;
			arrayQueens[i].print();
		}
		PriorityQueue<queens> minHeap = new PriorityQueue<queens>(new Comparator<queens>() {
	        @Override
	        public int compare(queens o1, queens o2) {
	        	if(o1.score<o2.score)
	        		return -1;
	        	else if(o1.score>o2.score)
	        		return 1;
	        	else 
	        		return 0;
	        }
	    });
		boolean flag=false;
		for(int i=1;i<x;i++) {
			for(int m=0;m<k;m++) {
			arrayQueens[m].makeChild();
			for (int j=0;j<arrayQueens[m].q.length;j++) {
				if(arrayQueens[m].q[j].score==0) {
					System.out.println();
					arrayQueens[m].q[j].print();
					System.out.println("SOLUTION("+i+" iterations)");
					flag=true;
					break;
				}
				minHeap.add(arrayQueens[m].q[j]);
			}
			if(flag)
				break;
			}
			if(flag)
				break;
			System.out.println("ITERATION "+i);
			for(int m=0;m<k;m++) {
				arrayQueens[m]=minHeap.poll();
				if(arrayQueens[m].score<maxScore)
					maxScore=arrayQueens[m].score;
				arrayQueens[m].print();
			}
			
			minHeap.clear();
		}
		if(!flag){
			System.out.println("Fail (score="+maxScore+")");
		}
		sc.close();
	}
	}

