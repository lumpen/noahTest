package com.mapbar.noah.algorithm.insection;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Insection {
	public static void main(String[] args) {
//		Polygon p = new Polygon();
//		p.xpoints = new int[]{10,30,45,29,15};
//		p.ypoints = new int[]{10,13,32,40,30};
//		Point pp = new Point(15,15);
//		System.out.println(p.contains(pp));
//		int n = p.npoints;
//		System.out.println(n);
		List<Double> xA = Arrays.asList(new Double[]{10d,30d,45d,29d,15d});
		List<Double> yA = Arrays.asList(new Double[]{10d,13d,32d,40d,30d});
		System.out.println(System.currentTimeMillis());
		Random ran = new Random();
		for(int i = 0; i < 100000000; i++){
			double x = ran.nextDouble();
			double y = ran.nextDouble();
			isPointInPolygon(x, y, xA, yA);	
		}
		System.out.println(System.currentTimeMillis());
	}
	
	public static boolean isPointInPolygon(double px, double py,  
            List<Double> polygonXA, List<Double> polygonYA) {  
        boolean isInside = false;  
        double ESP = 1e-9;  
        int count = 0;  
        double linePoint1x;  
        double linePoint1y;  
        double linePoint2x = 180;  
        double linePoint2y;  
  
        linePoint1x = px;  
        linePoint1y = py;  
        linePoint2y = py;  
  
        for (int i = 0; i < polygonXA.size() - 1; i++) {  
            double cx1 = polygonXA.get(i);  
            double cy1 = polygonYA.get(i);  
            double cx2 = polygonXA.get(i + 1);  
            double cy2 = polygonYA.get(i + 1);  
            if (isPointOnLine(px, py, cx1, cy1, cx2, cy2)) {  
                return true;  
            }  
            if (Math.abs(cy2 - cy1) < ESP) {  
                continue;  
            }  
  
            if (isPointOnLine(cx1, cy1, linePoint1x, linePoint1y, linePoint2x,  
                    linePoint2y)) {  
                if (cy1 > cy2)  
                    count++;  
            } else if (isPointOnLine(cx2, cy2, linePoint1x, linePoint1y,  
                    linePoint2x, linePoint2y)) {  
                if (cy2 > cy1)  
                    count++;  
            } else if (isIntersect(cx1, cy1, cx2, cy2, linePoint1x,  
                    linePoint1y, linePoint2x, linePoint2y)) {  
                count++;  
            }  
        }  
        if (count % 2 == 1) {  
            isInside = true;  
        }  
  
        return isInside;  
    }  
  
    public static double Multiply(double px0, double py0, double px1, double py1,  
            double px2, double py2) {  
        return ((px1 - px0) * (py2 - py0) - (px2 - px0) * (py1 - py0));  
    }  
  
    public static boolean isPointOnLine(double px0, double py0, double px1,  
            double py1, double px2, double py2) {  
        boolean flag = false;  
        double ESP = 1e-9;  
        if ((Math.abs(Multiply(px0, py0, px1, py1, px2, py2)) < ESP)  
                && ((px0 - px1) * (px0 - px2) <= 0)  
                && ((py0 - py1) * (py0 - py2) <= 0)) {  
            flag = true;  
        }  
        return flag;  
    }  
  
    public static boolean isIntersect(double px1, double py1, double px2, double py2,  
            double px3, double py3, double px4, double py4) {  
        boolean flag = false;  
        double d = (px2 - px1) * (py4 - py3) - (py2 - py1) * (px4 - px3);  
        if (d != 0) {  
            double r = ((py1 - py3) * (px4 - px3) - (px1 - px3) * (py4 - py3))  
                    / d;  
            double s = ((py1 - py3) * (px2 - px1) - (px1 - px3) * (py2 - py1))  
                    / d;  
            if ((r >= 0) && (r <= 1) && (s >= 0) && (s <= 1)) {  
                flag = true;  
            }  
        }  
        return flag;  
    }  
}
