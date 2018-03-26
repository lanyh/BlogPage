package myself.QQcopy.serve;


import java.io.*;
import java.net.*;
import java.util.*;

import myself.QQcopy.util.closeUtil;


public class Server 
{
	private List<MyChannel> all = new ArrayList<MyChannel>();
	
	
	public static void main(String[] args) throws IOException 
	{
		new Server().start();
	}
	
	
	public void start() throws IOException
	{
		ServerSocket server =new ServerSocket(9999);
		while(true)
		{
			Socket client =server.accept();		
			MyChannel channel = new MyChannel(client);
			all.add(channel);//统一管理
			new Thread(channel).start(); //一条道路
		}
	}

	
	private class MyChannel implements Runnable
	{
		private DataInputStream dis;
		private DataOutputStream dos;
		private boolean isRunning = true;
		
		
		public MyChannel(Socket client) 
		{	
			try {
				dis = new DataInputStream((client.getInputStream()));
				dos = new DataOutputStream((client.getOutputStream()));
			} catch (IOException e) {
				closeUtil.closeAll(dis, dos);
				isRunning = false;
			}
		}
		
		
		private String receive()
		{
			String msg = "";
			
			try {
				msg = dis.readUTF();
			} catch (IOException e) {
				closeUtil.closeAll(dis);
				isRunning = false;
				all.remove(this);
			}
			return msg;
		}
		
		private void send(String msg)
		{
			if(null == msg || msg.equals("")) return;
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				closeUtil.closeAll(dos);
				isRunning = false;
				all.remove(this);
			}
		}
		
		
		private void sendOthers()
		{
			String msg = this.receive();
			
			for(MyChannel other : all)
			{
				if(other == this)continue;
				
				other.send(msg);
			}
		}
		
		
		@Override
		public void run() 
		{
			while(isRunning)
			{
				sendOthers();
			}
		}
		
	}
}
