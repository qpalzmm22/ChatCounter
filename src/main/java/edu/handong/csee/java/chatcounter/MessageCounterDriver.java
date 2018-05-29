package edu.handong.csee.java.chatcounter;

import java.util.*;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;

public class MessageCounterDriver {
	
	String inputPath;
	String outputPath;
	boolean help;
	
	public static void main(String[] args) {
		MessageCounterDriver main = new MessageCounterDriver();
		main.run(args);
	}

	private void run(String[]  args) {
		Options options = createOptions();
		
		if(parseOptions(options, args)) {
			if(help) {
				printHelp(options);
				return;
			}
			FileLoader fileLoader = new FileLoader(inputPath);
			
			//MUST BE IN THIS ORDER(load mac first, then windows)
			fileLoader.loadMacFiles();
			fileLoader.loadWindowsFiles();
			System.out.println("Loading Complete...");
			
			
			HashMap<String, ArrayList<NDMdata>> NMlist = fileLoader.getMessages();
			List<NMcount> ncList = new ArrayList<NMcount>();
	
			for(String name : NMlist.keySet()) {
				if(!name.equals(""))
					ncList.add(new NMcount(name, NMlist.get(name).size()));	
			}
			Collections.sort(ncList);
			
			FileExporter fileExporter = new FileExporter(outputPath);
			fileExporter.makeItCSVFile(ncList);
			System.out.println("Exporting Complete...");
		}
	}

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			inputPath = cmd.getOptionValue("i");
			outputPath = cmd.getOptionValue("o");
			help = cmd.hasOption("h");

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	private Options createOptions() {
		Options options = new Options();

		options.addOption(Option.builder("i").longOpt("input-path")
				.desc("Set a path of a directory for files to import")
				.hasArg()
				.argName("Path name for import files")
				.required()
				.build());
		
		options.addOption(Option.builder("o").longOpt("output-path")
				.desc("Set a path + files name of a file to export")
				.hasArg()
				.argName("Path name + file name for export file ")
				.required()
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Help")
		        .build());

		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "CLI for Message Counter program";
		String footer = "End of the help";
		formatter.printHelp("CLIExample", header, options, footer, true);
	}
}
