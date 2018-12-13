package yogi.filecommand;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import yogi.base.relationship.index.ManyIndexedManager;

/**
 * @author Vikram Vadavala
 *
 */
public class FileCommandManager extends ManyIndexedManager<FileCommand, String> {
	private static FileCommandManager itsInstance = new FileCommandManager();
	private Set<Pattern> patternSet = new HashSet<Pattern>();

	protected FileCommandManager() {
		super();
	}

	public static FileCommandManager get() {
		return itsInstance;
	}
	
	@Override
	protected void buildRelationships(FileCommand fileCommand) {	
		if(fileCommand.isRegularExpression()) patternSet.add(Pattern.compile(fileCommand.getFileName(),Pattern.CASE_INSENSITIVE));
	}

	@Override
	protected void deleteRelationships(FileCommand fileCommand) {
		if(patternSet.contains(fileCommand)) patternSet.remove(fileCommand.getFileName());
	}
	
	public Set<Pattern> getPatterns(String fileName){
		return patternSet;
	}
	
	public List<FileCommand> getFileCommands(String name){
		List<FileCommand> fileCommands = new ArrayList<FileCommand>(this.getObjects(name));
		if (!patternSet.isEmpty()) {
			for (Pattern pattern : patternSet) {
				boolean match = doesFileMatchWithPattern(pattern,name);
				if (match) {
					fileCommands.addAll(this.getObjects(pattern.pattern()));
				}
			}
		}
		return fileCommands;
	}
	
	private boolean doesFileMatchWithPattern(Pattern pattern, String fileName) {
		Matcher matcher = pattern.matcher(fileName.trim());
		return matcher.matches();
	} 
	
		
}
