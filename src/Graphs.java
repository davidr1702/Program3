import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;

public class Graphs {
	
	Prim p;
	Kruskal k;
	FloydWarshall f;
	
	public static void main(String[] args) 
	{
		Read r=new Read();
	}
}

////////////////////////////////////////////////////////////////

class Read{
	
	public String[] labels= new String[5];
	public Integer[][] adjacency= new Integer[5][5];
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
}

///////////////////////////////////////////////////////////////

class Prim{
	
}

/////////////////////////////////////////////////////

class Kruskal {
	
}

//////////////////////////////////////////////////////

class FloydWarshall{
	
}