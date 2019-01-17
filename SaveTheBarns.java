/*This program is to calculate how many people of what party and gender contributed cash to the cause of saving the barns.
 * Robert Hannah 01/16/2019
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.*;
import java.util.*;

public class SaveTheBarns {
	
	//variables
	static String iString, iName, iAddress, iCity, iState, iZip;
	static String oTotal, oCounterM, oCounterMD, oCounterMR, oCounterMI, oCounterF, oCounterFD, oCounterFR, oCounterFI, oCounterD, 
			oCounterR, oCounterI, oTotalP;
	static String iCharString, iCharString2;
	static char iParty, iGender;
	static int cCounter,  cCounterF, cCounterM, cZip, cCounterR = 0, cCounterI = 0, cCounterMD = 0, cCounterFD = 0, 
			cCounterMR = 0, cCounterFR = 0, cCounterMI = 0, cCounterFI = 0, cCounterD = 0;
	static double iContribute, cTotal = 0, cTotalM = 0, cTotalMD = 0, cTotalMR = 0, cTotalMI = 0, cTotalD = 0, cTotalR = 0, cTotalI = 0,
			cTotalF = 0, cTotalFD = 0, cTotalFR = 0, cTotalFI = 0;
	static boolean endPrgm;
	static Scanner myScanner;
	static PrintWriter pw;
	static String record;
	
	public static void main(String[] args) {
		
		init();
		
		while(!endPrgm) {
		
		calcs();
		
		input();
		
		}
		Output();
		System.out.print(" Program Ended, Have a Good Day");
		pw.close();
		System.exit(1);
	}
	public static void init() {
		// Set up input Scanner
		try {
		myScanner = new Scanner(new File("Contributions3.dat"));
		myScanner.useDelimiter(System.getProperty("line.separator"));
		}
	
		catch (FileNotFoundException e) {
			System.out.print("No File Found");
			System.exit(1);	
		}	
		try {
			pw = new PrintWriter(new File ("Error.prt"));
			}
			catch (Exception e) {
			}
		input();
	}
	public static void input() {
		//Check and fill any records
		if (myScanner.hasNext()) {
			record = myScanner.next();
			iName = record.substring(0,24);	// positions 1-25
			iAddress = record.substring(24,49); // positions 26-51
			iCity = record.substring(49,64); // positions 52-65
			iState = record.substring(64, 66); // potitions 66-67
			try {
			iZip = record.substring(66, 71); // positions68-72
			cZip = Integer.parseInt(iZip); //Check for numerical data if not send to error
			}
			catch (Exception e) {
				pw.format("%n %1s", " ");
				pw.format(record);
				pw.print(" Error In Party ");
				input();
			}
			try {
				iCharString = record.substring(71); // position 73
				iParty = iCharString.charAt(0);
			}
			catch (Exception e) {
				pw.format("%n %1s", " ");
				pw.print(record);
				pw.print(" Error In Party ");
				input();
			}
			try {
				iCharString2 = record.substring(72); // position 74
				iGender = iCharString2.charAt(0);
			}
			catch (Exception e) {
				pw.format("%n %1s", " ");
				pw.print(record);
				pw.print(" Error In Gender ");
				input();
			}
			try {
				iString = record.substring(73, 80); // positions 75-80, for iContribute 
				iContribute = Double.parseDouble(iString); // positions 76-80 from iString
			}
			catch (Exception e) {
				pw.format("%n %1s", " ");
				pw.print(record);
				pw.print(" Error In Contribution Amount ");
				input();
			}
		}	
		else {
			//End the loop if there arn't any records by changing "End" to true
			endPrgm = true;
		}
	}
	
	public static void calcs() {
		//checking for empty names
		if (iName.trim().isEmpty()) {
			pw.format("%n %1s", " ");
			pw.print(record);
			pw.print(" Error In Name");
			input();
		}
		//checking for empty address
		if (iAddress.trim().isEmpty()) {
			pw.format("%n %1s", " ");
			pw.print(record);
			pw.print(" Error In Address");
			input();
		}
		//checking for empty city
		if (iCity.trim().isEmpty()) {
			pw.format("%n %1s", " ");
			pw.print(record);
			pw.print(" Error In City");
			input();
		}
		//checking for empty state
		if (iState.trim().isEmpty()) {
			pw.format("%n %1s", " ");
			pw.print(record);
			pw.print(" Error In State");
			input();
		}
		//checking if party is empty
		if (iCharString.trim().isEmpty()) {
			pw.format("%n %1s", " ");
			pw.print(record);
			pw.print(" Error In Party");
			input();
		}
		//checking if gender is empty
		if (iCharString2.trim().isEmpty()) {
			pw.format("%n %1s", " ");
			pw.print(record);
			pw.print(" Error In Gender");
			input();
		}
		//checking for bellow min or above maximum
		if (iContribute == 0 || iContribute == 10000.00) {
			pw.format("%n %1s", " ");
			pw.print(record);
			pw.print(" Error In Contribution Amount");
			input();
		}
		
		//check gender for male, after check party
		if (iGender == 'M') {
			cCounterM += 1;
			cTotalM += iContribute;
			switch (iParty) {
			case 'D':
				cCounterMD += 1;
				cCounterD += 1;
				cTotalD += iContribute;
				cTotalMD += iContribute;
				break;
			case 'R':
				cCounterMR += 1;
				cCounterR += 1;
				cTotalR += iContribute;
				cTotalMR += iContribute;
				break;
			case 'I':
				cCounterMI += 1;
				cCounterI += 1;
				cTotalI += iContribute;
				cTotalMI += iContribute;
				break;
			}
		}
		//check gender for female, after check party
		else {
			cCounterF += 1;
			cTotalF += iContribute;
			switch (iParty) {
			case 'D':
				cCounterFD += 1;
				cCounterD += 1;
				cTotalD += iContribute;
				cTotalFD += iContribute;
				break;
			case 'R':
				cCounterFR += 1;
				cCounterR += 1;
				cTotalR += iContribute;
				cTotalFR += iContribute;
				break;
			case 'I':
				cCounterFI += 1;
				cCounterI += 1;
				cTotalI += iContribute;
				cTotalFI += iContribute;
				break;
			}
		}
	}
	
	public static void Output() {
		cTotal += cTotalR + cTotalD + cTotalI;
		int cTotalP = cCounterM + cCounterF;
		String oTotalD, oTotalR, oTotalI, oTotalFI, oTotalMI, oTotalFR, oTotalMR, oTotalFD, oTotalMD, oTotalF, oTotalM;
		DecimalFormat dfTotal = new DecimalFormat("$##,###.00");
		//Format the Counters, and totals
		oTotal = dfTotal.format(cTotal);
		oCounterM = Integer.toString(cCounterM);
		oCounterMD = Integer.toString(cCounterMD);
		oCounterMR = Integer.toString(cCounterMR);
		oCounterMI = Integer.toString(cCounterMI);
		oCounterF = Integer.toString(cCounterF);
		oCounterFD = Integer.toString(cCounterFD);
		oCounterFR = Integer.toString(cCounterFR);
		oCounterFI = Integer.toString(cCounterFI);
		oCounterD = Integer.toString(cCounterD);
		oCounterR = Integer.toString(cCounterR);
		oCounterI = Integer.toString(cCounterI);
		oTotalP = Integer.toString(cTotalP);
		oTotalD = dfTotal.format(cTotalD);
		oTotalR = dfTotal.format(cTotalR);
		oTotalI = dfTotal.format(cTotalI);
		oTotalM = dfTotal.format(cTotalM);
		oTotalF = dfTotal.format(cTotalF);
		oTotalMD = dfTotal.format(cTotalMD);
		oTotalFD = dfTotal.format(cTotalFD);
		oTotalMR = dfTotal.format(cTotalMR);
		oTotalFR = dfTotal.format(cTotalFR);
		oTotalMI = dfTotal.format(cTotalMI);
		oTotalFI = dfTotal.format(cTotalFI);
		//Output the data to the console
		System.out.format("%n %-25s %-5s %10s", "Men: ", oCounterM, oTotalM);
		System.out.format("%n %-25s %-5s %10s", "Female: ", oCounterF, oTotalF);
		System.out.format("%n %-25s %-5s %10s", "Democrats: ", oCounterD, oTotalD);
		System.out.format("%n %-25s %-5s %10s", "Republicans: ", oCounterR, oTotalR);
		System.out.format("%n %-25s %-5s %10s", "Independent: ", oCounterI, oTotalI);
		System.out.format("%n %-25s %-5s %10s", "Male Democrat: ", oCounterMD, oTotalMD);
		System.out.format("%n %-25s %-5s %10s", "Female Democrat: ", oCounterFD, oTotalFD);
		System.out.format("%n %-25s %-5s %10s", "Male Republican: ", oCounterMR, oTotalMR);
		System.out.format("%n %-25s %-5s %10s", "Female Republican: ", oCounterFR, oTotalFR);
		System.out.format("%n %-25s %-5s %10s", "Male Independent: ", oCounterMI, oTotalMI);
		System.out.format("%n %-25s %-5s %10s", "Female Independent: ", oCounterFI, oTotalFI);
		System.out.format("%n %-25s %-5s %10s", "Total: ", oTotalP, oTotal);
	}
}