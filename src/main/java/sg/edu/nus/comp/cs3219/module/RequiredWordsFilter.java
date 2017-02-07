package sg.edu.nus.comp.cs3219.module;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import sg.edu.nus.comp.cs3219.event.LineStorageChangeEvent;
import sg.edu.nus.comp.cs3219.model.Line;
import sg.edu.nus.comp.cs3219.model.LineStorage;

public class RequiredWordsFilter implements Observer {
	final private LineStorage resultStorage;
	private Set<String> requiredWords = new HashSet<>();

	public RequiredWordsFilter(LineStorage storage) {
		resultStorage = storage;
	}
	
	public void setRequiredWords(Set<String> requiredWordSet) {
		requiredWords = requiredWordSet;
	}

	@Override
	public void update(Observable o, Object arg) {
		LineStorage storage = (LineStorage) o;
		LineStorageChangeEvent event = (LineStorageChangeEvent) arg;
		switch (event.getEventType()) {
		case ADD:
			Line line = storage.get(event.getChangedLine());
			makeRequiredWordsLowercase(line);
			filterRequiredWords(line);
			
			break;
		default:
			break;
		}
	}
	
	private void filterRequiredWords(Line line) {
		if (line.size() > 0) {
			if (isRequiredWord(line.getWord(0))) {
				resultStorage.addLine(line);
			}	
		}
	}

	private void makeRequiredWordsLowercase(Line line) {
		for (int i = 0; i < line.size();i++) {
			String word = line.getWord(i);
			if (isRequiredWord(word)) {
				line.setWord(i, word.toLowerCase());
			}
		}
	}
	
	private boolean isRequiredWord(String word) {
		if (requiredWords.isEmpty()) {
			return true;
		}
		return requiredWords.contains(word.toLowerCase());
	}

}
