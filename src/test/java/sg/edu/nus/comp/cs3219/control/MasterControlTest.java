package sg.edu.nus.comp.cs3219.control;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class MasterControlTest {

	MasterControl master;
	
	@Before
	public void setUp() {
		master = new MasterControl();
	}

	@Test
	public void testExample1() {
		Set<String> ignoreWords = new HashSet<>();
		ignoreWords.add("is");
		ignoreWords.add("the");
		ignoreWords.add("of");
		ignoreWords.add("and");
		ignoreWords.add("as");
		ignoreWords.add("a");
		ignoreWords.add("after");

		List<String> input = new ArrayList<>();
		input.add("The Day after Tomorrow");
		input.add("Fast and Furious");
		input.add("Man of Steel");
		
		Set<String> requiredWords = new HashSet<>();
		requiredWords.add("Day");
		requiredWords.add("Tomorrow");
		requiredWords.add("Fast");
		requiredWords.add("Furious");
		requiredWords.add("Man");
		requiredWords.add("Steel");

		List<String> result = master.run(input, ignoreWords, requiredWords);
	
		assertEquals(6, result.size());
		assertEquals("Day after tomorrow the", result.get(0));
		assertEquals("Fast and furious", result.get(1));
		assertEquals("Furious fast and", result.get(2));
		assertEquals("Man of steel", result.get(3));
		assertEquals("Steel man of", result.get(4));
		assertEquals("Tomorrow the day after", result.get(5));
	}
	
	@Test
	public void testExample2() {
		Set<String> ignoreWords = new HashSet<>();
		ignoreWords.add("and");

		List<String> input = new ArrayList<>();
		input.add("Andy ander and hand");
		
		Set<String> requiredWords = new HashSet<>();
		requiredWords.add("Andy");
		requiredWords.add("ander");
		requiredWords.add("hand");
		
		List<String> result = master.run(input, ignoreWords, requiredWords);	

		System.out.println(result);
		
		assertEquals(3, result.size());
		assertEquals("Andy ander and hand", result.get(1));
		assertEquals("Ander and hand andy", result.get(0));
		assertEquals("Hand andy ander and", result.get(2));
	}
	
	@Test
	public void testExample3() {
		Set<String> ignoreWords = new HashSet<>();
		ignoreWords.add("is");
		ignoreWords.add("the");
		ignoreWords.add("of");
		ignoreWords.add("and");
		ignoreWords.add("as");
		ignoreWords.add("a");
		ignoreWords.add("after");
		
		Set<String> requiredWords = new HashSet<>();
		requiredWords.add("Day");
		requiredWords.add("Fast");
		requiredWords.add("Man");

		List<String> input = new ArrayList<>();
		input.add("The day after tomorrow");
		input.add("Fast and Furious");
		input.add("Man of Steel");

		List<String> result = master.run(input, ignoreWords, requiredWords);

		assertEquals(3, result.size());
		assertEquals("Day after tomorrow the", result.get(0));
		assertEquals("Fast and Furious", result.get(1));
		assertEquals("Man of Steel", result.get(2));
	}

	@Test
	public void testExample4() {
		Set<String> ignoreWords = new HashSet<>();
		ignoreWords.add("is");
		ignoreWords.add("the");
		ignoreWords.add("of");
		ignoreWords.add("and");
		ignoreWords.add("as");
		ignoreWords.add("a");
		ignoreWords.add("after");

		Set<String> requiredWords = new HashSet<>();

		List<String> input = new ArrayList<>();
		input.add("The day after tomorrow");
		input.add("Fast and Furious");
		input.add("Man of Steel");

		List<String> result = master.run(input, ignoreWords, requiredWords);

		assertEquals(6, result.size());
		assertEquals("Day after tomorrow the", result.get(0));
		assertEquals("Fast and furious", result.get(1));
		assertEquals("Furious fast and", result.get(2));
		assertEquals("Man of steel", result.get(3));
		assertEquals("Steel man of", result.get(4));
		assertEquals("Tomorrow the day after", result.get(5));
	}
}

