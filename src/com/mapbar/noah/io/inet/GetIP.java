package com.mapbar.noah.io.inet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.InetAddress;


public class GetIP {
	public static void main(String[] args) throws Exception{
		if(args.length == 0){
			System.out.println("没有输入文件");
			System.exit(1);
		}
		File input = new File(args[0]);
		String filePath = input.getAbsolutePath().substring(0, input.getAbsolutePath().lastIndexOf(File.separator));
		BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filePath + File.separator + "result.txt")));
		String line = "";
		while((line = br.readLine()) != null){
			try{
				InetAddress addr=InetAddress.getByName(line.trim());
				String IPName=addr.getHostAddress();
				System.out.println(line);
				bw.write(line + " " + IPName);
				bw.newLine();
			}catch(Exception e){
				bw.write(line + " " + "不能获取IP");
				bw.newLine();
			}
		}
		bw.flush();
		bw.close();
		br.close();
		System.out.println("获取完成");
	}
}
