package com.sify.cmp.parse;

import com.sonalake.utah.Parser;
import com.sonalake.utah.config.Config;
import com.sonalake.utah.config.ConfigLoader;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TestParser {
	
	public static void main(String[] args) {
		String configFile = "testConfig.xml";
		String sampleFile = "testFile.txt";
		TestParser tp = new TestParser();
		/*
		 * System.out.println("INSIDE THE TESTPARSER.......Sample Block"); TestParser tp
		 * = new TestParser(); try { tp.testFileProcessing(configFile, sampleFile); }
		 * catch(Exception e) { System.out.println("SKSK EXCEPTION: "+e.getMessage()); }
		 * //System.out.println("INSIDE THE TESTPARSER COMPLETE.......");
		 */		
		configFile = "testVariables.xml";
		sampleFile = "testVariables.txt";
		System.out.println("INSIDE THE TESTPARSER.......Variables Block");
		
		try {
			tp.testFileProcessing(configFile, sampleFile);
		} catch(Exception e)
		{
			System.out.println("SKSK EXCEPTION: "+e.getMessage());
		}
		System.out.println("INSIDE THE TESTPARSER COMPLETE.......");
		
		
		configFile = "providersConfig.xml";
		sampleFile = "providers.tf";
		//sampleFile = "testProviders.txt";
		System.out.println("INSIDE THE TESTPARSER.......Provider Block");
		
		try {
			tp.testFileProcessing(configFile, sampleFile);
		} catch(Exception e)
		{
			System.out.println("SKSK EXCEPTION: "+e.getMessage());
		}
		System.out.println("INSIDE THE TESTPARSER COMPLETE.......");
	}

	private void testFileProcessing(String configResource, String fileResource) throws IOException {
		// load the config
		URL configURL = Thread.currentThread().getContextClassLoader().getResource(configResource);
		System.out.println("configURL :"+configURL);
		Config config = new ConfigLoader().loadConfig(configURL);

		// load a real file
		List<Map<String, String>> observedValues = new ArrayList<>();
		try (Reader in = new InputStreamReader(
				Thread.currentThread().getContextClassLoader().getResourceAsStream(fileResource))) {
			Parser parser = Parser.parse(config, in);
			while (true) {
				Map<String, String> record = parser.next();
				if (null == record) {
					break;
				} else {
					observedValues.add(record);
					System.out.println("SKSK RECORD :"+record);
				}
			}
			
			System.out.println("SKSK OBSERVED-VALUES :"+observedValues);
		}

	}

}
