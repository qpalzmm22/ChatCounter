package edu.handong.csee.java.chatcounter;

import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;

/**
 * This class is the driver for main method
 * This class implements CLI using commons-cli
 * The CLI contains 3 options of inputPath, outputPath and help
 * @author qpalz
 *
 */
public class MessageCounterDriver {
	
	private String inputPath;
	private String outputPath;
	private int numOfThreads;
	private boolean help;
	
	/**
	 * Main method to call the run method with arguments, args 
	 * @param args
	 */
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
			File dir = new File(inputPath);
			
			ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);
			ArrayList<ThreadUsingLoader> loaders = new ArrayList<ThreadUsingLoader>();
			
			for(File file : dir.listFiles()) {
				Runnable loader = new ThreadUsingLoader(file);
				executor.execute(loader); 
				loaders.add((ThreadUsingLoader) loader);
			}
			
			executor.shutdown();
			while (!executor.isTerminated()) {}
			
			HashMap<String, ArrayList<NDMdata>> uniquePool = new HashMap<String, ArrayList<NDMdata>>();
			
			FileParser parser = new FileParser();
			for(ThreadUsingLoader e : loaders) {
				for(NDMdata data:e.getDataPool()) {
					uniquePool = parser.addUnique(uniquePool, data);
				}
			}
			
			System.out.println("Loading Complete...");
			
			List<NMcount> ncList = new ArrayList<NMcount>();
	
			for(String name : uniquePool.keySet()) {
				if(!name.equals(""))
					ncList.add(new NMcount(name, uniquePool.get(name).size()));	
			}
			Collections.sort(ncList);
			
			
			// uncomment it if you want to see it work in eclipse
			
			
			System.out.println("kakao_id, count");
			for(NMcount e: ncList) {
				e.print();
			}
			
			
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
			numOfThreads = Integer.parseInt(cmd.getOptionValue("c"));
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
		
		options.addOption(Option.builder("c").longOpt("num-of-threads")
				.desc("Set number of threads to use")
				.hasArg()
				.argName("number of threads to load files ")
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
		String header = "CLI for Message Counter program\n\n";
		String footer = "End of the help\n";
		formatter.printHelp("ChatCounter", header, options, footer, true);
	}
}
