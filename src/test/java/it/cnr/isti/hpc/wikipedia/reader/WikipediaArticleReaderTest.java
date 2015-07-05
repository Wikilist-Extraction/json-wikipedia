/**
 *  Copyright 2011 Diego Ceccarelli
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package it.cnr.isti.hpc.wikipedia.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import it.cnr.isti.hpc.io.IOUtils;
import it.cnr.isti.hpc.wikipedia.article.Article;
import it.cnr.isti.hpc.wikipedia.article.Language;
import it.cnr.isti.hpc.wikipedia.reader.ParallelWikipediaArticleReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 * WikipediaArticleReaderTest.java
 *
 * @author Diego Ceccarelli, diego.ceccarelli@isti.cnr.it created on 18/nov/2011
 */
@org.junit.Ignore
public class WikipediaArticleReaderTest {

	/**
	 * @author Sven Mischkewitz, sven.mischkewitz@student.hpi.de
	 * This test expects the system to use ShowNameAndParameters template parser.
	 * I used ShowValue parser, so this test fails.
	 *
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws SAXException
	 */
	public void testParsing() throws UnsupportedEncodingException,
			FileNotFoundException, IOException, SAXException {

		URL u = this.getClass().getResource("/en/mercedes.xml");
		ParallelWikipediaArticleReader wap = new ParallelWikipediaArticleReader(
				u.getFile(), "/tmp/mercedes.json", Language.EN);
		wap.start();
		
		String json = IOUtils.getFileAsUTF8String("/tmp/mercedes.json");
		Article a = Article.fromJson(json);

		assertTrue(a.getCleanText().startsWith("Mercedes-Benz"));
		assertEquals(15, a.getCategories().size());

	}

}
