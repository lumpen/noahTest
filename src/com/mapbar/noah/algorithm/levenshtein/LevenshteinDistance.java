package com.mapbar.noah.algorithm.levenshtein;

public class LevenshteinDistance {

	public static void main(String[] args) {
		String str1 = "defg";
		String str2 = "fgde";
		System.out.println(getDistance(str1, str2));
	}

	/**
	 * 最小编辑距离计算 首先初始化一个str1.length列str2.length行的矩阵matrix[m][n]，作为编辑距离的存储矩阵。
	 * 然后初始化第一行为0~n，第一列为0~m。 矩阵中的每一个元素值matrix[i][j]表示的含义为： 字串str2[1~i] ->
	 * 字串str1[1~j]的编辑距离。 value1 = (str1[j] == str2[i] ? 0 : 1) +
	 * matrix[i-1][j-1]，即 字串str2[1~i-1] ->
	 * 字串str1[1~j-1]的编辑距离加此两个字符串的下一个字符是否相等的变动值。 value2 =
	 * matrix[i-1][j]+1，即字符串str2[1~i-1]->str1[1~j]的编辑距离。(+1是因为至少要转化一步，下同) value3
	 * = matrix[i][j-1]+1，即字符串str2[1~i]->str1[1~j-1]的编辑距离。 此以上三个值都为str2[1~i] ->
	 * 字串str1[1~j]转化过程中的一步计算值，取其最小值则为其最小的编辑距离。
	 * 根据初始化为从空串变到str1和str2的编辑距离，按照数学归纳法，得到最终的编辑距离矩阵matrix[m][n]为两个字符串的最小编辑距离。
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static int getDistance(String str1, String str2) {
		int distance = 0;
		if (str1.equals(str2))
			return distance;
		int n = str1.length();
		int m = str2.length();
		if (n == 0)
			return m;
		if (m == 0)
			return n;
		int[][] disMatrix = init(n, m);

		for (int i = 1; i <= n; i++) {
			char c1 = str1.charAt(i - 1);
			for (int j = 1; j <= m; j++) {
				char c2 = str2.charAt(j - 1);
				int cost = (c1 == c2) ? 0 : 1;
				int x = disMatrix[j][i - 1] + 1;
				int y = disMatrix[j - 1][i] + 1;
				int z = disMatrix[j - 1][i - 1] + cost;
				disMatrix[j][i] = min(x, y, z);
			}
		}
		distance = disMatrix[m][n];
		return distance;
	}

	public static int[][] init(int n, int m) {
		int[][] disMatrix = new int[m + 1][n + 1];
		for (int i = 1; i <= n; i++)
			disMatrix[0][i] = i;
		for (int i = 1; i <= m; i++)
			disMatrix[i][0] = i;
		return disMatrix;
	}

	public static int min(int x, int y, int z) {
		int min = x;
		if (y < min)
			min = y;
		if (z < min)
			min = z;
		return min;
	}
}