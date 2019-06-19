package edu.handong.bonus;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;





public class ImplementsLS {
	String pOption;
	boolean sOption;
	boolean fOption;
	boolean mOption;
	boolean rOption;
	String setPath;

	boolean help;
	
	//C:\Users\한주희\Desktop\hw5data.csv C:\Users\한주희\Desktop\Iwannagobakchame\ladfasdfasdfsdfd.csv

	public void run(String[] args) {

			
		
		Options options = createOptions();

		
		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				return;
			}
			
			if(pOption==null) {
				setPath = System.getProperty("user.dir");
			}
			else setPath=pOption;
			
			File file = new File(setPath); 
			
		ArrayList<String> elements = new ArrayList<String>();
		

		if(fOption) {
				File []fileList=file.listFiles();
				for(File tempFile : fileList) {
					   if (tempFile.isDirectory()) {
			                elements.add(tempFile.getName());
			            }
			            if (tempFile.isFile()) {
			            	elements.add(tempFile.getName());
			            }
				}
				elements.add(".");
				elements.add("..");
//				System.out.println("ffffffffffffffffffffffff");
				
		}else if(sOption){
			File []fileList=file.listFiles();
			int fliesize =0;
			for(File tempFile : fileList) {
				   if (tempFile.isDirectory()) {
		                elements.add(tempFile.length()+" "+tempFile.getName());
		                fliesize+=tempFile.length();
		                
		            }
		            if (tempFile.isFile()) {
		            	elements.add(tempFile.length()+" "+tempFile.getName());
		            	fliesize+=tempFile.length();
		            }
			}
			if(rOption)Collections.reverse(elements);
			System.out.println("tatol "+fliesize);
			
		}else if(rOption){
			File []fileList=file.listFiles();
			for(File tempFile : fileList) {
				   if (tempFile.isDirectory()) {
		                if(tempFile.getName().indexOf(".")!=0)elements.add(tempFile.getName());
		                	
		            }
		            if (tempFile.isFile()) {
		            	if(tempFile.getName().indexOf(".")!=0)elements.add(tempFile.getName());
		            }
			}
//			System.out.println("rrrrrrrrrrrrrrrrrrrrrrr");
			Collections.reverse(elements);
		
		}else {
			File []fileList=file.listFiles();
			for(File tempFile : fileList) {
				   if (tempFile.isDirectory()) {
		                if(tempFile.getName().indexOf(".")!=0)elements.add(tempFile.getName());
		                	
		            }
		            if (tempFile.isFile()) {
		            	if(tempFile.getName().indexOf(".")!=0)elements.add(tempFile.getName());
		            }
			}System.out.println("elsekdkddkd");
		}
		
		if(mOption) {
			int count=0;
			for(String Filename: elements) {
				System.out.print(Filename);
				if(count<elements.size()-1)System.out.print(", ");
				count++;
			}
		}else {
			for(String Filename: elements) {
//				System.out.println("마지막 프린트");
				System.out.println(Filename);
			}
		}
		

			
	}

}

	
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);
		

			pOption = cmd.getOptionValue("p");
			sOption = cmd.hasOption("s");
			fOption= cmd.hasOption("f");
			mOption = cmd.hasOption("m");
			rOption= cmd.hasOption("r");
			
			help = cmd.hasOption("h");


		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	// Definition Stage
	private Options createOptions() {
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("p").longOpt("pOption")
				.desc("Set an Relative path")
				.hasArg()
				.argName("Relative path")
				.build());
		
		options.addOption(Option.builder("s").longOpt("sOption")
				.desc("print the allocated size of each file, in blocks")
				.argName("size")
				.build());
		
		options.addOption(Option.builder("f").longOpt("fOption")
				.desc("do not sort, enable -au, disable -ls --color")
				.argName("output path")
				.build());
		
		options.addOption(Option.builder("m").longOpt("mOption")
				.desc("fill width with a comma separated list of entries")
				.argName("output path")
				.build());
		
		options.addOption(Option.builder("r").longOpt("rOption")
				.desc("reverse order while sorting")
				.argName("reverse")
				.build());
		

		
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Show a Help page")
		        .argName("Help")
		        .build());
		


		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "ls program";
		String footer =";";
		formatter.printHelp("ls program", header, options, footer, true);
	}

}



