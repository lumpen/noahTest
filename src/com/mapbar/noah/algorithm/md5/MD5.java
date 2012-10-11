package com.mapbar.noah.algorithm.md5;

public class MD5 {
	public void proc(String message) {
		int[] k = new int[64];
		int[] r = {7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22,
				5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20, 4, 11,
				16, 23, 4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23, 6, 10, 15,
				21, 6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21 };
		
		for(int i = 0; i < 64; i++){
			k[i] = (int) Math.floor(Math.abs(Math.sin(i+1)) * (2^32));
		}
		
		 int h0 = 0x67452301;
		 int h1 = 0xEFCDAB89;
		 int h2 = 0x98BADCFE;
		 int h3 = 0x10325476;

//		 Integer.toBinaryString()
		 
	}
	
	public static void main(String[] args) throws Exception{
		 java.security.MessageDigest md = java.security.MessageDigest.getInstance( "MD5" );
		 System.out.println(md.getClass().getName());
	}
}
