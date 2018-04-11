import java.io.BufferedReader;
import java.util.PriorityQueue;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
/*	
 * Authors: David Rodriguez and Jacob Hargiss 
 * Date: 04/10/2018
 * Overview: Graphs (Prim, Kruskal and Floyd-Warshall algorithms
*/
public class Graphs {
	

	
	public static void main(String[] args) 
	{
		Prim p=new Prim();
		Kruskal k= new Kruskal();
		FloydWarshall f=new FloydWarshall();
		System.out.println();
		System.out.println("Prim's algorithm");
		p.MST();
		System.out.println();
		System.out.println("Kruskal's algorithm");
		k.MST();
		System.out.println();
		System.out.println("Floyd-Warshall's algorithm");
		f.MST();
	}
}

////////////////////////////////////////////////////////////////

class Read{

	public String[] labels= new String[5];
	public int[][] adjacency= new int[5][5];
	Edge[][] e= new Edge[5][5];
	public Read() {
		int count=0;
		int n=0;
		Charset charset=Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newBufferedReader(FileSystems.getDefault().getPath("input", "input.txt"), charset))
		{
				String line=null;
				while ((line = reader.readLine()) !=null)
				{
					String[] words=line.split(",");
					switch (count) {
					
					case 0:
						for(int i=0; i<labels.length;i++) 
						{
							labels[i]=words[i];
							System.out.print(labels[i]	+	",");
						}
						break;
					case 1:
						for(int i=0; i<adjacency[0].length;i++) 
						{
							adjacency[0][i]=Integer.parseInt(words[i]);
							
							if (adjacency[0][i]>=1000)
							{
								adjacency[0][i]=Integer.MAX_VALUE;
								System.out.print("INF,");
							}
							else {
								System.out.print(adjacency[0][i]	+	",");
							}
							if (adjacency[0][i]!=Integer.MAX_VALUE) {
								e[0][n]=new Edge(0,adjacency[0][i], i);
								n=n+1;
							}
							else {
								e[0][n]=new Edge(0, 0, i);
								n=n+1;
							}
						}
						break;
					case 2:
						for(int i=0; i<adjacency[1].length;i++) 
						{
							adjacency[1][i]=Integer.parseInt(words[i]);
							if (adjacency[1][i]>=1000)
							{
								adjacency[1][i]=Integer.MAX_VALUE;
								System.out.print("INF,");
							}
							else {
								System.out.print(adjacency[1][i]	+	",");
							}
							if (adjacency[1][i]!=Integer.MAX_VALUE && adjacency[1][i]!=0) {
								e[1][n]=new Edge(1,adjacency[1][i], i);
								n=n+1;
							}
							else {
								e[1][n]=new Edge(1, 0, i);
								n=n+1;
							}
						}
						break;
					case 3:
						for(int i=0; i<adjacency[2].length;i++) 
						{
							adjacency[2][i]=Integer.parseInt(words[i]);
							if (adjacency[2][i]>=1000)
							{
								adjacency[2][i]=Integer.MAX_VALUE;
								System.out.print("INF,");
							}
							else {
								System.out.print(adjacency[2][i]	+	",");
							}
							if (adjacency[2][i]!=Integer.MAX_VALUE && adjacency[2][i]!=0) {
								e[2][n]=new Edge(2, adjacency[2][i], i);
								n=n+1;
							}
							else {
								e[2][n]=new Edge(2, 0, i);
								n=n+1;
							}
						}
						break;
					case 4:
						for(int i=0; i<adjacency[3].length;i++) 
						{
							adjacency[3][i]=Integer.parseInt(words[i]);
							if (adjacency[3][i]>=1000)
							{
								adjacency[3][i]=Integer.MAX_VALUE;
								System.out.print("INF,");
							}
							else {
								System.out.print(adjacency[3][i]	+	",");
							}
							if (adjacency[3][i]!=Integer.MAX_VALUE && adjacency[3][i]!=0) {
								e[3][n]=new Edge(3, adjacency[3][i], i);
								n=n+1;
							}
							else {
								e[3][n]=new Edge(3, 0, i);
								n=n+1;
							}
						}
						break;
					case 5:
						for(int i=0; i<adjacency[4].length;i++) 
						{
							adjacency[4][i]=Integer.parseInt(words[i]);
							
							if (adjacency[4][i]>=1000)
							{
								adjacency[4][i]=Integer.MAX_VALUE;
								System.out.print("INF,");
							}
							else {
								System.out.print(adjacency[4][i]	+	",");
							}
							if (adjacency[4][i]!=Integer.MAX_VALUE && adjacency[4][i]!=0) {
								e[4][n]=new Edge(4, adjacency[4][i], i);
								n=n+1;
							}
							else {
								e[4][n]=new Edge(4, 0, i);
								n=n+1;
							}
						}
						break;
					}
					count=count+1;
					System.out.println();
					n=0;
				}
			} catch (IOException x)
			{
				System.err.format("IOException: %s%n", x);
			}	
		System.out.println("");
	}
	public int getSize() {
		return labels.length;
	}
	public String getName(int i) {
		return labels[i];
	}
	
	public int[][] getValue() {
		return adjacency;
	}
	public Edge[][] getEdge() {
		return e;
	}
}

///////////////////////////////////////////////////////////////
class Edge implements Comparable<Edge>{
	
	int source, key, end;
	
	public Edge(int s, int k, int e) {
		source=s;
		key=k;
		end=e;
	}
	
	public int compareTo(Edge e) {
		return key-e.key;
	}
}

////////////////////////////////////////////////////////////////
class Prim{
	
	public Read r;
	public void MST() 
	{
		r= new Read();
		int size=r.getSize();
		int key[]=new int[size];
		int name[]=new int[size];
		Boolean set[]=new Boolean[size];
		Edge[][] e=r.getEdge();
		int Tree;
		int check=0;
		
		PriorityQueue<Edge> queue= new PriorityQueue<Edge>();
		for(int i=0; i<size; i++) {
			set[i]=false;
		}

		key[0]=0;
		name[0]=-1;
		set[0]=true;
		Tree=0;
		
		for(int i=0; i<size; i++) {
			for (int j=0; j<e[i].length; j++) {
				if (e[i][j].key>0) {
					if (e[i][j].source==0 || e[i][j].end==0) {
						queue.add(e[i][j]);
					}
				}
				
			}
		}
		
		
		while(!queue.isEmpty()) {
			Edge temp=queue.remove();
			int next=0;
			if (check==0) {
				next=temp.end;
			}
			else {
				next=temp.source;
			}
			if (set[next]) {
				continue;
			}
			set[next]=true;
			System.out.println(r.getName(Tree)	+	"-"	+	r.getName(next));
			Tree=next;
			
			for(int i=0; i<size; i++) {
				for (int j=0; j<e[i].length; j++) {
					if (e[i][j].key>0) {
						if (e[i][j].source==next) {
							queue.add(e[i][j]);
							check=0;
						}
						else if (e[i][j].end==next) {
							queue.add(e[i][j]);
							check=1;
						}
					}
					
				}
			}
		}
	}
}

class Subset{
	int source, weight;
}
/////////////////////////////////////////////////////

class Kruskal {
	public Edge[] edge=new Edge[5];
	int union=0;
	public Read r;
	public void MST() {
		r= new Read();
		int size=r.getSize();
		int key[]=new int[size];
		Subset set[]= new Subset[size];
		Edge[][] e=r.getEdge();
		
		PriorityQueue<Edge> queue= new PriorityQueue<Edge>();
		for(int i=0; i<size; i++) {
			for (int j=0; j<e[i].length; j++) {
				if (e[i][j].key>0) {
					queue.add(e[i][j]);
				}
			}
		}
		
		for(int i=0;i<size;i++) {
			set[i]=new Subset();
		}
		
		for (int i=0; i<size; i++) {
			set[i].source=i;
			set[i].weight=0;
		}
		
		while (union<size-1) {
			Edge temp=queue.remove();
			int x=check(set, temp.source);
			int y=check(set, temp.end);
			
			if (x!=y)
			{
				edge[union]=temp;
				join(set, x, y);
				union=union+1;
			}
		}
		for (int i=0; i<union;i++) {
			System.out.println(r.getName(edge[i].source) + "-"	+	r.getName(edge[i].end)+"-Weight: "+edge[i].key);
		}
	}
	
	public int check(Subset s[], int n) {
		if (s[n].source!=n) {
			s[n].source=check(s, s[n].source);
		}
		return s[n].source;
	}
	
	public void join(Subset s[], int x, int y) {
		int xr=check(s, x);
		int yr=check(s,x);
		
		s[xr].source=yr;
	}
}

//////////////////////////////////////////////////////

class FloydWarshall{
	public Read r;
	public void MST() {
		r = new Read();
		Edge[][] e = r.getEdge();
		int size = r.getSize();
		
		int graph[][]=new int[size][size];
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				if(e[i][j].key==0 && i!=j)
				{
					graph[i][j]=Integer.MAX_VALUE;
				}
				else {
					graph[i][j]=e[i][j].key;
				}
			}
		}
		
		for(int k = 0; k < size; ++k) {
			for(int i = 0; i < size; ++i) {
				for(int j = 0; j < size; ++j) {
					if (graph[i][j]>(graph[i][k]+graph[k][j]) && (graph[i][k]+graph[k][j])>0) {
						graph[i][j]=graph[i][k]+graph[k][j];
					}
					printE(size, graph);
				}
			}
		}
		
	}
	private Edge[][] realign(int size, Edge[][] e) {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(i != j) {
					if(e[i][j].key == 0) {
						e[i][j].key = -1;
					}
				}
			}
		}
		return e;
	}
	
	public void printE(int size, int[][] graph) {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(graph[i][j] != Integer.MAX_VALUE) {
					System.out.print(graph[i][j]);
				}
				else {
					System.out.print("INF");
				}
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println("--------------");
	}
}