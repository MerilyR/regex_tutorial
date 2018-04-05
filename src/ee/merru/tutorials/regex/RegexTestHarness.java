package ee.merru.tutorials.regex;

import java.io.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexTestHarness {
	public static void main(String[] args){
        Pattern pattern = null;
        Pattern pattern1 = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}:\\d{2},\\d{3}\\s+\\((.+)\\)\\s+\\[[^\\]]*\\]\\s+([\\S])+\\s+in\\s+\\d+");
        Pattern pattern2 = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}:\\d{2},\\d{3}\\s+\\((.+)\\)\\s+\\[[^\\]]*\\]\\s+(\\w)+(\\s+([\\S])+)*\\s+in\\s+\\d+");
        Matcher matcher = null;
        Matcher matcher1 = null;
        Matcher matcher2 = null;

        Console console = System.console();
        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }
        while (true) {
            try{
            	String s = console.readLine("%nUse predefined regex (y) or enter your own (n)?");
            	boolean choice = 
            	s.equalsIgnoreCase("true") || s.equalsIgnoreCase("t") || 
                s.equalsIgnoreCase("yes") || s.equalsIgnoreCase("y");
            	
            	if (!choice) {
            		pattern = 
            		Pattern.compile(console.readLine("%nEnter your regex: "));
	
	                matcher = 
	                pattern.matcher(console.readLine("%nEnter input string to search: "));
	                
	                boolean found = false;
	                while (matcher.find()) {
	                    console.format("I found the text" +
	                        " \"%s\" starting at " +
	                        "index %d and ending at index %d.%n",
	                        matcher.group(),
	                        matcher.start(),
	                        matcher.end());
	                    found = true;
	                }
	                if(!found){
	                    //console.format("No match found.%n");
	                	console.format ("Input string doesn't match !?!%n");
	                }
	                
            	}
            	else {
            		int lines = Integer.parseInt(console.readLine("%nEnter how many lines you want to check: "));
            		
            		for (int i = 0; i < lines; i++) {
            			String searchString = console.readLine("%nEnter input string to check: ");
            			matcher1 = pattern1.matcher(searchString);
            			matcher2 = pattern2.matcher(searchString);
            			if (matcher1.find())
            				console.format("Line matches Pattern 1%n");
            			if (matcher2.find())
            				console.format("Line matches Pattern 2%n");
            			console.format("Line "+i+1+" checked!%n%n");
            		}
            		
            	}
            }
            catch(PatternSyntaxException pse){
                console.format("There is a problem" +
                               " with the regular expression!%n");
                console.format("The pattern in question is: %s%n",
                               pse.getPattern());
                console.format("The description is: %s%n",
                               pse.getDescription());
                console.format("The message is: %s%n",
                               pse.getMessage());
                console.format("The index is: %s%n",
                               pse.getIndex());
                System.exit(0);
            }
        }
    }
}
