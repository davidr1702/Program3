import java.io.BufferedReader;
import java.util.PriorityQueue;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;

public class Graphs {
	
	Kruskal k;
	FloydWarshall f;
	
	public static void main(String[] args) 
	{
		Prim p=new Prim();
		System.out.println("Prim's algorithm");
		p.MST();
		System.out.println("Kruskal's algorithm");
		System.out.println("Floyd-Warshall's algorithm");
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
							System.out.print(adjacency[0][i]	+	",");
							if (adjacency[0][i]>=1000)
							{
								adjacency[0][i]=Integer.MAX_VALUE;
							}
							if (adjacency[0][i]!=Integer.MAX_VALUE) {
								e[0][n]=new Edge(adjacency[0][i], i);
								n=n+1;
							}
							else {
								e[0][n]=new Edge(0, i);
								n=n+1;
							}
						}
						break;
					case 2:
						for(int i=0; i<adjacency[1].length;i++) 
						{
							adjacency[1][i]=Integer.parseInt(words[i]);
							System.out.print(adjacency[1][i]	+	",");
							if (adjacency[1][i]>=1000)
							{
								adjacency[1][i]=Integer.MAX_VALUE;
							}
							if (adjacency[1][i]!=Integer.MAX_VALUE && adjacency[1][i]!=0) {
								e[1][n]=new Edge(adjacency[1][i], i);
								n=n+1;
							}
							else {
								e[1][n]=new Edge(0, i);
								n=n+1;
							}
						}
						break;
					case 3:
						for(int i=0; i<adjacency[2].length;i++) 
						{
							adjacency[2][i]=Integer.parseInt(words[i]);
							System.out.print(adjacency[2][i]	+	",");
							if (adjacency[2][i]>=1000)
							{
								adjacency[2][i]=Integer.MAX_VALUE;
							}
							if (adjacency[2][i]!=Integer.MAX_VALUE && adjacency[2][i]!=0) {
								e[2][n]=new Edge(adjacency[2][i], i);
								n=n+1;
							}
							else {
								e[2][n]=new Edge(0, i);
								n=n+1;
							}
						}
						break;
					case 4:
						for(int i=0; i<adjacency[3].length;i++) 
						{
							adjacency[3][i]=Integer.parseInt(words[i]);
							System.out.print(adjacency[3][i]	+	",");
							if (adjacency[3][i]>=1000)
							{
								adjacency[3][i]=Integer.MAX_VALUE;
							}
							if (adjacency[3][i]!=Integer.MAX_VALUE && adjacency[3][i]!=0) {
								e[3][n]=new Edge(adjacency[3][i], i);
								n=n+1;
							}
							else {
								e[3][n]=new Edge(0, i);
								n=n+1;
							}
						}
						break;
					case 5:
						for(int i=0; i<adjacency[4].length;i++) 
						{
							adjacency[4][i]=Integer.parseInt(words[i]);
							System.out.print(adjacency[4][i]	+	",");
							if (adjacency[4][i]>=1000)
							{
								adjacency[4][i]=Integer.MAX_VALUE;
							}
							if (adjacency[4][i]!=Integer.MAX_VALUE && adjacency[4][i]!=0) {
								e[4][n]=new Edge(adjacency[4][i], i);
								n=n+1;
							}
							else {
								e[4][n]=new Edge(0, i);
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
	
	int key, end;
	
	public Edge(int k, int e) {
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
		
		PriorityQueue<Edge> queue= new PriorityQueue<Edge>();
		for(int i=0; i<size; i++) {
			set[i]=false;
		}
		
		key[0]=0;
		name[0]=-1;
		set[0]=true;
		Tree=0;
		
		for(int i=0; i<e[0].length; i++) {
			if (e[0][i].key>0) {
				queue.add(e[key[0]][i]);
			}
			if (e[i][0].key>0) {
				queue.add(e[i][key[0]]);
			}
		}
		
		while(!queue.isEmpty()) {
			Edge temp=queue.remove();
			int next=temp.end;
			if (set[temp.end]) {
				continue;
			}
			set[temp.end]=true;
			System.out.println(r.getName(Tree)	+	"-"	+	r.getName(temp.end));
			Tree=temp.end;
			
			for(int i=0; i<e[next].length;i++) {
				queue.add(e[key[next]][i]);
			}
		}
	}
}

/////////////////////////////////////////////////////

class Kruskal {
	public Read r;
	public void MST() {
		r= new Read();
		int size=r.getSize();
		int key[]=new int[size];
		int name[]=new int[size];
		Boolean set[]=new Boolean[size];
		Edge[][] e=r.getEdge();
		int Tree;
		
		PriorityQueue<Edge> queue= new PriorityQueue<Edge>();
		for(int i=0; i<size; i++) {
			for (int j=0; j<e[size].length; j++) {
				if (e[i][j].key>0) {
					queue.add(e[key[0]][i]);
				}
			}
		}
	}
}

//////////////////////////////////////////////////////

class FloydWarshall{
	
}