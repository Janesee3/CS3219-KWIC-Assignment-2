package sg.edu.nus.comp.cs3219.module;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.cs3219.model.LineStorage;

public class RequiredWordsFilterTest {
	LineStorage shiftedLineStorage;
	LineStorage afterFilterLineStorage;
	RequiredWordsFilter filter;
	
	
	@Before
	public void setUp() {
		shiftedLineStorage = new LineStorage();
		afterFilterLineStorage = new LineStorage();
		filter = new RequiredWordsFilter(afterFilterLineStorage);
		Set<String> words = new HashSet<>();
		words.add("day");
		filter.setRequiredWords(words);
		shiftedLineStorage.addObserver(filter);
	}

	@Test
	public void test1() {
		shiftedLineStorage.addLine("Day after Tomorrow the");
		shiftedLineStorage.addLine("Tomorrow the Day after");
		
		assertEquals(1, afterFilterLineStorage.size());
		assertEquals("day after Tomorrow the", afterFilterLineStorage.get(0).toString());
	}
}
