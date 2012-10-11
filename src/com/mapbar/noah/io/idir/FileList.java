package com.mapbar.noah.io.idir;

import java.io.File;

public class FileList {
	public static void main(String[] args) {
		String root = "D:/software/develop/apache/lucene/lucene-2.3.0/docs/api/";
		File rootFile = new File(root);
		show(rootFile);
	}
	
	public static void show(File file){
		File[] files = file.listFiles();
		for(File temp : files){
			if(temp.isFile()){
				System.out.println(temp.getName());
			}
			if(temp.isDirectory()){
				show(temp);
			}
		}
	}
}
