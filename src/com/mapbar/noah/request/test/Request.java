package com.mapbar.noah.request.test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Request implements Runnable{
	public Request(String[] urlStrs) {
		this.urlStrs = urlStrs;
	}

	@Override
	public void run() {
		int i = 0;
		while(true){
			try{
				long start = System.currentTimeMillis();
				URL url = new URL(urlStrs[i]);
				URLConnection conn = url.openConnection();
				conn.setDoOutput(true);
				InputStream is = conn.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				int b = -1;
				while((b = is.read()) != -1){
					baos.write(b);
				}
				is.close();
				long end = System.currentTimeMillis();
				System.out.println(Thread.currentThread().getName() + " search cost time " + (end - start));
				if(showResult){
					System.out.println(baos.toByteArray().length);
					System.out.println(new String(baos.toByteArray(), "GB18030"));	
				}
			}catch(Exception e){
				e.printStackTrace();
				continue;
			}finally{
				i++;
				i = i % urlStrs.length;	
			}
		}
			
	}

	public void setShowRequest(boolean showResult){
		this.showResult = showResult;
	}
	
	private boolean showResult = false;
	
	private String[] urlStrs;
}
