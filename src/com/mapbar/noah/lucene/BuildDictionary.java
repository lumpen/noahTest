//package com.mapbar.noah.lucene;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//
//import org.apache.lucene.analysis.WhitespaceAnalyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.index.IndexReader;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.util.Constants;
//
//public class BuildDictionary {
//	public void buildIndex(String indexDir, String dictionaryFile){
//		try{
//			IndexWriter writer = new IndexWriter(indexDir, new WhitespaceAnalyzer(), true);
//			writer.setMaxFieldLength(1000000);
//			FileInputStream is = new FileInputStream(dictionaryFile);
//			Document doc = new Document();
//			BufferedReader br = new BufferedReader(new InputStreamReader(is));
//			String line = br.readLine();
//			int lineNumber = 1;
//			StringBuffer sbString = new StringBuffer();
//			while(line != null){
//				if(lineNumber % 1000 != 0){
//					sbString.append(line);
//					sbString.append(" ");
//					line = br.readLine();
//					lineNumber++;
//					continue;
//				}
//				doc.add(new Field("dictionary", new String(sbString.toString().getBytes(), "GB2312"), Field.Store.NO, Field.Index.TOKENIZED));
//				writer.addDocument(doc);
//				line = br.readLine();
//				lineNumber++;
//				sbString = null;
//				sbString = new StringBuffer();
//			}
//			writer.optimize();
//			writer.close();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	public int getHighTotalFreq(String word) throws Exception{
////		IndexReader reader = null;
////		reader = IndexReader.open(Constants.);
//		return 0;
//	}
//}
