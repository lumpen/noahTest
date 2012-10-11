package com.mapbar.noah.request.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class RequestTimeCheck {
	public static void main(String[] args) throws Exception {
		String file = "";
		int threadNum = 100;
		boolean showResult = false;
		if(args.length == 0){
//			String file = "D:/siege/o_jsp_qa_para_0.138.txt";
			file = "D:/siege/o_jsp_qa_para_9.4.txt";	
		}else{
			file = args[0];
			if(args.length > 2)
				showResult = Boolean.parseBoolean(args[2].trim());
			if(args.length > 1)
				threadNum = Integer.parseInt(args[1].trim());
		}
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		String line = "";
		List<String> urlList= new ArrayList<String>();
		while((line = br.readLine()) != null){
			urlList.add(line.trim());
		}
		br.close();
		String[] urls = urlList.toArray(new String[0]);
		for(int i = 0; i < threadNum; i++){
			Request req = new Request(urls);
			req.setShowRequest(showResult);
			new Thread(req).start();
		}
	}
}
