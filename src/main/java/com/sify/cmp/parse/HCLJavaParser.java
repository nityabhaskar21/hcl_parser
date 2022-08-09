package com.sify.cmp.parse;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bertramlabs.plugins.hcl4j.HCLParser;
import com.bertramlabs.plugins.hcl4j.HCLParserException;

public class HCLJavaParser {

	public static void main(String[] args) {

		// String path =
		// Thread.currentThread().getContextClassLoader().getResource("testVariables.txt").toString();
		File file = new File(
				"C:\\Users\\016817\\Documents\\workspace3\\com.sify.cmp.parse.zip_expanded\\com.sify.cmp.parse\\src\\main\\resources\\testVariables.txt");
		HCLJavaParser parser = new HCLJavaParser();
		try {
			// String str = FileUtils.readFileToString(file,"UTF-8");
			// var variables = parser.parse(str);
			// System.out.println("SKSK VARIABLES :"+variables);
			var variables = parser.parseVariables();
			System.out.println("VARIABLES :\n" + variables);
			var providers = parser.parseProviders();
			System.out.println("PROVIDERS :\n" + providers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	Map<String, Map<String, String>> parse(String input) {
		System.out.println("STR :" + input);
		HashMap<String, Map<String, String>> variables = new HashMap<>();
		Matcher variableMatcher = Pattern.compile("variable \"(\\S+?)\"\\s*\\{\\s*((?:.|\\n)+?)\\s*}").matcher(input);
		Pattern bodyElementPattern = Pattern.compile("(\\S+)\\s*=\\s*\"(.+)\"");
		while (variableMatcher.find()) {
			HashMap<String, String> bodyElements = new HashMap<>();
			System.out.println("SKSK group(2) :" + variableMatcher.group(2));
			Matcher bodyElementMatcher = bodyElementPattern.matcher(variableMatcher.group(2));
			// System.out.println("SKSK Root :"+ variableMatcher.group(1));
			while (bodyElementMatcher.find()) {
				bodyElements.put(bodyElementMatcher.group(1), bodyElementMatcher.group(2));
			}
			variables.put(variableMatcher.group(1), bodyElements);
		}
		System.out.println("SKSK variables :" + variables);
		return variables;
	}

	public Map parseVariables() {

		File terraformFile = new File(
				"E:\\variables.tf");
		File terraformFile1 = new File(
				"C:\\Users\\016817\\Documents\\workspace3\\com.sify.cmp.parse.zip_expanded\\com.sify.cmp.parse\\src\\main\\resources\\variablesN1.tf");
		Map results = null;
		try {
			results = new HCLParser().parse(terraformFile, "UTF-8");
		} catch (HCLParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return results;
	}

	public Map parseProviders() {

		File terraformFile = new File(
				"C:\\Users\\016817\\Documents\\workspace3\\com.sify.cmp.parse.zip_expanded\\com.sify.cmp.parse\\src\\main\\resources\\providers.tf");
		Map results = null;
		try {
			results = new HCLParser().parse(terraformFile, "UTF-8");
		} catch (HCLParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return results;
	}

}
