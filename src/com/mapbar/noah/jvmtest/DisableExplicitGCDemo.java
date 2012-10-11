package com.mapbar.noah.jvmtest;

import java.nio.ByteBuffer;

import sun.misc.VM;

public class DisableExplicitGCDemo {
	public static void main(String[] args) {  
	    for (int i = 0; i < 100000; i++) {  
	      ByteBuffer.allocateDirect(128);  
	    }
	    System.out.println("Done");
//		System.out.println(Runtime.getRuntime().maxMemory()/(1024*1024));
//		System.out.println(VM.maxDirectMemory()/(1024*1024));
	  }  
}
