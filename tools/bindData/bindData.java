import java.io.*;

class bindData
{
	public static void main(String[]files)throws IOException
	{
		FileOutputStream wr = new FileOutputStream(new File("data"));
		PrintStream indices = new PrintStream(new File("indice.java"));
		indices.println("public interface indice{");
		
		int bytesReceived = 0;
		int index=0;
		for(int i=0;i<files.length;i++)
		{
			File file = new File(files[i]);
			String filename=files[i].substring(0,files[i].indexOf("."));
			
			FileInputStream fis = new FileInputStream(file);
			byte[] outBuffer = new byte[(int)file.length()];
			
			indices.println("public final int "+filename+"_Start="+index+";");
			indices.println("public final int "+filename+"_End="+(index+file.length())+";");
			
			while((bytesReceived = fis.read(outBuffer))>0)
			{
				wr.write(outBuffer,0,bytesReceived);
			}
			index+=(int)file.length();
			fis.close();
		}
			wr.close();
			indices.println("}");
			indices.close();
	}
}