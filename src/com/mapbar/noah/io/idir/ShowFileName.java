package com.mapbar.noah.io.idir;
import java.io.File;


public class ShowFileName {
	public static void main(String[] args) {
		File file = new File("D:/doc");
		show(file);
	}
	
	public static void show(File file){
		if(file.isFile()){
			System.out.println(file.getAbsolutePath());
			return;
		}
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(File temp : files){
				show(temp);
			}
		}
	}
}
