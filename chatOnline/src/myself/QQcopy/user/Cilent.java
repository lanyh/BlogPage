package myself.QQcopy.user;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import myself.QQcopy.util.closeUtil;


public class Cilent 
{
	private MyFrame windows;
	
	public static void main(String[] args) throws UnknownHostException, IOException 
	{
		new Cilent().start();
	}
	
	
	public void start() throws UnknownHostException, IOException
	{ 
		Socket client = new Socket("localhost", 9999);
		windows = new MyFrame(client);
		windows.setVisible(true);
		while(true) 
		{
			String msg = (new MyChannel(client).receive());
			windows.setMsg(msg);		
		}
		/*receiveIO a = new receiveIO(client);
		new Thread(a).start();
		String msg = new String("");
		while(true)
		{
			String str = new String(a.getMsg());
			if(!str.equals(msg)) {windows.setMsg(str);msg = new String(str);}
		}*/
	}
	
	private class MyChannel
	{
		private DataInputStream dis;
		
		public MyChannel(Socket socket)
		{
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				closeUtil.closeAll(dis);
			}
		}
		
		public String receive()
		{
			String msg ="";
			try {
				msg=dis.readUTF();
			} catch (IOException e) {
				closeUtil.closeAll(dis);
			}
			return msg;
		}

	}
}
