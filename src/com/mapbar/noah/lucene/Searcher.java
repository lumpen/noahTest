package com.mapbar.noah.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Searcher {
	public static void main(String[] args) throws IllegalArgumentException, IOException, ParseException{
		if(args.length != 2){
			throw new IllegalArgumentException("Useage : java " + Searcher.class.getName() + " <index dir> <query>");
		}
		String indexDir = args[0];
		String q = args[1];
		search(indexDir, q);
	}
	
	/**
	 * @param indexDir
	 * @param q
	 * @throws IOException
	 * @throws ParseException
	 */
	@SuppressWarnings("deprecation")
	public static void search(String indexDir, String q) throws IOException, ParseException{
		Directory dir = FSDirectory.open(new File(indexDir));
		IndexSearcher is = new IndexSearcher(dir);

//		Term t = new Term(indexDir, q);
//		Query query = new TermQuery(t);
		
		QueryParser parser = new QueryParser(Version.LUCENE_30, "contents", new StandardAnalyzer(Version.LUCENE_30));
//		QueryParser parser = new QueryParser(Version.LUCENE_30, "contents", new SimpleAnalyzer(Version.LUCENE_30));
		Query query = parser.parse(q);
		
//		Term t = new Term("contents", q);
//		Query query = new TermQuery(t);
		
		System.out.println(query.toString());

		long start = System.currentTimeMillis();
		TopDocs hits = is.search(query, 30);
		long end = System.currentTimeMillis();
		
		System.err.println("Found " + hits.totalHits + " docment(s) (in " + (end - start) + " millseconds) that matched query '" + q + "':");
		for(ScoreDoc scoreDoc : hits.scoreDocs){
			Document doc = is.doc(scoreDoc.doc);
			System.out.println(doc.get("fullpath"));
		}
		is.close();
		
		ScoreDoc[] docs = hits.scoreDocs;
		for(ScoreDoc ds : docs){
			System.out.println(ds.score);
		}
	}
	
}
