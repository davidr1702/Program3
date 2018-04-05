import java.io.BufferedReader;
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
		p.MST();
	}
}

////////////////////////////////////////////////////////////////

class Read{

	public String[] labels= new String[5];
	public int[][] adjacency= new int[5][5];
	public Read() {
		int count=0;
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
						}
						break;
					case 2:
						for(int i=0; i<adjacency[1].length;i++) 
						{
							adjacency[1][i]=Integer.parseInt(words[i]);
							System.out.print(adjacency[1][i]	+	",");
						}
						break;
					case 3:
						for(int i=0; i<adjacency[2].length;i++) 
						{
							adjacency[2][i]=Integer.parseInt(words[i]);
							System.out.print(adjacency[2][i]	+	",");
						}
						break;
					case 4:
						for(int i=0; i<adjacency[3].length;i++) 
						{
							adjacency[3][i]=Integer.parseInt(words[i]);
							System.out.print(adjacency[3][i]	+	",");
						}
						break;
					case 5:
						for(int i=0; i<adjacency[4].length;i++) 
						{
							adjacency[4][i]=Integer.parseInt(words[i]);
							System.out.print(adjacency[4][i]	+	",");
						}
						break;
					}
					count=count+1;
					System.out.println();
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
}

///////////////////////////////////////////////////////////////

class Prim{
	
	public Read r;
	public void MST() 
	{
		r= new Read();
		int value[][]=r.getValue();
		int size=r.getSize();
		int key[]=new int[size];
		int name[]=new int[size];
		Boolean set[]=new Boolean[size];
		
		for(int i=0; i<size; i++) {
			key[i]=Integer.MAX_VALUE;
			set[i]=false;
		}
		
		key[0]=0;
		name[0]=-1;
		
		for (int i=0; i<size-1;i++) {
			int min=findMin(key,set);
			
			set[i]=true;
			
			for(int j=0; j<size;j++) {
				if(set[j]==false && value[min][j]!=0 && value[min][j]<key[j]) {
					name[j]=min;
					key[j]=value[min][j];
				}
			}
		}
		print(name, value);
	}
	
	public int findMin(int key[], Boolean set[]) 
	{
		int size=r.getSize();
		int min=Integer.MAX_VALUE;
		int min2=-1;
		for(int i=0; i<size; i++) {
			if(set[i]==false && key[i]<min) {
				min=key[i];
				min2=i;
			}
		}
		return min2;
	}
	public void print(int[] name, int[][] value) {
		int size=r.getSize();
		String[] labels=new String[size];
		
		for (int i=1; i<size; i++) {
			System.out.println(name[i]	+	"-"	+i);
		}
		
	}
}

/////////////////////////////////////////////////////

class Kruskal {
	
}

//////////////////////////////////////////////////////

class FloydWarshall{
	
}