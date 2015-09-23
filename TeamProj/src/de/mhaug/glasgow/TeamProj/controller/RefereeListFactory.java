package de.mhaug.glasgow.TeamProj.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.mhaug.glasgow.TeamProj.model.Referee;

public class RefereeListFactory {
	private static final File inputFile = new File("./RefereesIn.txt");
	private static final int maxInitialReferees = 12;

	public List<Referee> readInputFile() {
		assert inputFile.canRead();

		ArrayList<Referee> result = new ArrayList<>(maxInitialReferees);

		Scanner inputScanner;
		try {
			inputScanner = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(new JFrame("Error"), "The file " + inputFile.getAbsolutePath()
					+ " could not be found", "Input file not found", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return new ArrayList<Referee>();
		}

		while (inputScanner.hasNextLine()) {
			String line = inputScanner.nextLine();
			// Skip empty lines
			if (line.matches("\\s*"))
				continue;
			result.add(createRefereeFromLine(line));
		}

		inputScanner.close();
		return result;
	}

	private Referee createRefereeFromLine(String inputLine) {
		// The + causes several spaces to be parsed as one separator
		// e.g. "Dave  Gray" is parsed to {"Dave", "Gray"}
		String[] splittedLine = inputLine.split(" +");
		assert splittedLine.length == 7 : "line of length " + splittedLine.length;

		for (int i = 0; i < splittedLine.length; i++) {
			splittedLine[i] = splittedLine[i].trim();
		}

		Referee result = new Referee(splittedLine);
		return result;
	}
}
