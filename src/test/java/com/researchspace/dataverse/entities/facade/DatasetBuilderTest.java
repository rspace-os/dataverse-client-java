package com.researchspace.dataverse.entities.facade;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import com.researchspace.dataverse.entities.Citation;
import com.researchspace.dataverse.entities.Dataset;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.researchspace.dataverse.entities.facade.DatasetTestFactory.createFacade;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
/** <pre>
Copyright 2016 ResearchSpace

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
</pre>
*/
public class DatasetBuilderTest {

	DatasetBuilder builder;
	@Before
	public void setUp() throws Exception {
		builder = new DatasetBuilder();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBuildFromFacade() throws MalformedURLException, URISyntaxException {
		DatasetFacade facade = createFacade();
		Dataset dataset = builder.build(facade);

		assertNotNull(dataset);
		assertNotNull(dataset.getDatasetVersion());

		Citation citation = dataset.getDatasetVersion().getMetadataBlocks().getCitation();
		assertNotNull(citation);
		assertFalse(citation.getFields().isEmpty());

		assertTrue(citation.getFields().stream()
						.filter(f -> "title".equals(f.getTypeName()))
						.anyMatch(f -> "title1".equals(f.getValue())));

		assertTrue(citation.getFields().stream().anyMatch(f -> "author".equals(f.getTypeName())));
	}
}
