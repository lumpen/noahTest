package com.mapbar.noah.lucene.test;

import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import junit.framework.TestCase;

@SuppressWarnings("deprecation")
public class IndexingTest extends TestCase {
	protected String[] ids = {"1", "2"};
	protected String[] unindexed = {"Czech", "Germany"};
	protected String[] unstored = {"Amsterdam has lots of bridges", "Vinice has lots of canals"};
	protected String[] text = {"Amsterdam", "Venice"};
	
	private Directory directory;
	
	protected void setUp() throws Exception{
		directory = new RAMDirectory();
		IndexWriter writer = getWriter();
		for(int i = 0; i < ids.length; i++){
			org.apache.lucene.document.Document doc = new Document();
			doc.add(new Field("id", ids[i], Field.Store.YES, Field.Index.NOT_ANALYZED));
			doc.add(new Field("country", unindexed[i], Field.Store.YES, Field.Index.NO));
			doc.add(new Field("contents", unstored[i], Field.Store.NO, Field.Index.ANALYZED));
			doc.add(new Field("city", text[i], Field.Store.YES, Field.Index.ANALYZED));
			writer.addDocument(doc);
		}
		writer.close();
	}

	protected int getHitCount(String fieldName, String searchString) throws Exception{
		IndexSearcher searcher = new IndexSearcher(directory);
		Term t = new Term(fieldName, searchString);
		Query query = new TermQuery(t);
		TopDocs docs = searcher.search(query, 10);
		int hitCount = docs.totalHits;
		searcher.close();
		return hitCount;
	}
	
	public void testIndexWriter() throws Exception{
		IndexWriter writer = getWriter();
		assertEquals(ids.length, writer.numDocs());
	}
	
	public void testIndexReader() throws Exception{
		IndexReader reader = IndexReader.open(directory);
		assertEquals(ids.length, reader.maxDoc());
		assertEquals(ids.length, reader.numDocs());
		reader.close();
	}
	
	private IndexWriter getWriter() throws Exception{
		return new IndexWriter(directory, new WhitespaceAnalyzer(), IndexWriter.MaxFieldLength.UNLIMITED);
	}
}
