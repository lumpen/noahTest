package com.mapbar.noah.algorithm.forward;

public class Forward {
	
	public void init(){
		
	}
	
	private float[] pPai = {0.63f,0.17f,0.2f};
	
	private float[][] transProb = {{0.5f, 0.375f, 0.125f},
								   {0.25f, 0.127f, 0.625f},
								   {0.25f, 0.375f, 0.375f}};
	
	private float[][] combiProb = {{0.60f, 0.20f, 0.15f, 0.05f},
			                       {0.25f, 0.25f, 0.25f, 0.25f},
			                       {0.05f, 0.10f, 0.35f, 0.5f}};
	private String[] states = {"sunny", "cloudy", "rainy"};
	private String[] obser = {"dry", "dryish", "damp", "soggy"};
	
}
