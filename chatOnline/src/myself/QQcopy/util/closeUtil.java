package myself.QQcopy.util;

import java.io.Closeable;

public class closeUtil 
{
	public static void closeAll(Closeable... io)
	{
		for(Closeable temp : io)
		{
			
			try {
				if(null != temp)
					temp.close();
			} catch (Exception e) {}
			
		}
	}
}
