package com.bussiness.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.framework.exception.UnableToCreateNewFileException;
import com.framework.generic.FileGeneratorInterface;

public class LockedMeMain implements FileGeneratorInterface{
	private static final String DEFAULTPATH = "C:\\Users\\vivic\\my_applications\\my-java-projects\\lockedmecom\\default";
	private static final String TEMPPATH = "C:\\Users\\vivic\\my_applications\\my-java-projects\\lockedmecom\\default\\temp";
	public static final String ASKCHOICE = "Please enter your choice.";
	public static final String ERRORMESSAGE = "Invalid selector, Please enter again.";

	public static void main(String[] args) {
		LockedMeMain lm = new LockedMeMain();
		lm.printHeader();
		
		lm.run();
	}
	@Override
	//start program
	public void run() {
		Scanner mainScanner = new Scanner(System.in);
		int op = 0;
		displayMainMenu();
		do {
			try {
				op = mainScanner.nextInt();
				switch (op) {
				case 1:
					showFileListByAscOrder();
					break;
				case 2:
					fileMenu();
					break;
				case 3:
					System.out.println("Thank you for visiting LockedMe.com. We hope to see you soon!");
					System.exit(0);
					break;

				default:
					System.out.println(ERRORMESSAGE);
					break;
				}

			} catch (InputMismatchException e) {
				String bad_input = mainScanner.next();
				System.out.println("Please enter a number");
				continue;
			}
		} while (op != 3);
	}

	// -----------------------------------------------------------------------------------------------------------------------
	// helper method for printing instructions
	@Override
	public void printHeader() {
		System.out.println(
				"*************************************************************************************************\n");
		System.out.println(
				"*                                     Welcome to LockedMe.com                                   *");
		System.out.println(
				"*                                      Develper name: Vivica                                    *");
		System.out.println(
				"*                                 Contact email: vivica.ma@hcl.com                              *\n");
		System.out.println(
				"*************************************************************************************************");

	}

	@Override
	public void displayMainMenu() {
		System.out.println(
				"+-------------------------------------------------------------------------------------------------+");
		System.out.println(ASKCHOICE);
		String[] mainOptions = { "1. Retrieve all files", "2. Manipulate files", "3. Exit" };
		System.out.println(
				"+-------------------------------------------------------------------------------------------------+");

		for (String i : mainOptions) {
			System.out.println("\n" + i);
		}
	}
	
	@Override
	public void displayFileManipulationMenu() {
		System.out.println(
				"+-------------------------------------------------------------------------------------------------+");
		System.out.println(ASKCHOICE);
		System.out.println(
				"+-------------------------------------------------------------------------------------------------+");
		String[] fileManipulationOptions = { "1. Add a new file", "2. Delete a file", "3. Search a file",
				"4. Main menu" };
		for (String i : fileManipulationOptions) {
			System.out.println("\n" + i);
		}
	}
	
	@Override
	public void displayAddNewFileMenu() {
		System.out.println(
				"+-------------------------------------------------------------------------------------------------+");
		System.out.println("\n" + ASKCHOICE);
		System.out.println(
				"***************************************************************************************************");
		System.out.println("Default path is: " + TEMPPATH);
		System.out.println(
				"***************************************************************************************************");
		String[] addNewFile = { "1. Stay default directory and continue", "2. Change directory",
				"3. Make a new directory", "4. Previous ", "5. Main menu" };
		for (String i : addNewFile) {
			System.out.println("\n" + i);
		}
	}
	// -----------------------------------------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------------------------------------
	// helper method for run method
	
	@Override
	public void fileMenu() {
		Scanner fileManipulationScanner = new Scanner(System.in);
		int op = 0;
		displayFileManipulationMenu();

		do {
			try {
				op = fileManipulationScanner.nextInt();
				switch (op) {
				case 1:
					createFile();
					break;

				case 2:
					deleteFile();
					break;
				case 3:
					searchFile();
					break;

				case 4:
					run();
					break;

				default:
					System.out.println(ERRORMESSAGE);
					break;
				}
			} catch (InputMismatchException e) {
				String bad_input = fileManipulationScanner.next();
				System.out.println("Please enter a number");
				continue;
			}
		} while (op != 4);
	}
	
	@Override
	public void showDirectories(String dir) {
		System.out.println("************************************************************************************");
		System.out.println("All directories.");
		System.out.println("************************************************************************************");
		String[] pathnames;
		File f = new File(dir);
		pathnames = f.list();
		for (String pathname : pathnames) {
			System.out.println(pathname);
		}
		System.out.println("************************************************************************************");
		System.out.println("Please confirm your folder by entering the folder name from above list.");
		System.out.println("************************************************************************************");
	}
	
	@Override
	public void showFileListByAscOrder() {
		Scanner showFileScanner = new Scanner(System.in);
		showDirectories(DEFAULTPATH);
		String[] pathnames;
		File f = new File(DEFAULTPATH);
		pathnames = f.list();
		String pathValue = showFileScanner.nextLine();

		boolean flag = Arrays.asList(pathnames).contains(pathValue);
		if (flag) {
			System.out.println("************************************************************************************");
			System.out.println("Here are all your files:");
			System.out.println("************************************************************************************");
			File folder = new File(DEFAULTPATH + "\\" + pathValue);
			File[] listOfFiles = folder.listFiles();
			if (listOfFiles.length == 0) {
				System.out.println(
						"============================================================================================");
				System.out.println("\n\tNO FILE YET. You can add a new file by following the instructions.");
				System.out.println(
						"============================================================================================");
			} else {
				for (File file : listOfFiles) {
					if (file.isFile()) {
						System.out.println(file.getName());
					}
				}
			}
			displayMainMenu();
		} else {
			System.out.println(ERRORMESSAGE);
			displayMainMenu();
		}
	}

	// -----------------------------------------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------------------------------------
	// helper method for fileMenu method
	
	@Override
	public void createFile() {
		Scanner createFileScanner = new Scanner(System.in);
		displayAddNewFileMenu();
		int op = 0;

		do {
			try {
				op = createFileScanner.nextInt();
				switch (op) {
				case 1:
					try {
						addFile();
						//handle Custom Exception
					} catch (UnableToCreateNewFileException e) {
						System.out.println("File already exists.");
					}
					break;
				case 2:
					changeDirectory();
					break;
				case 3:
					createNewDirectory();
					break;
				case 4:
					fileMenu(); // back to file menu
					break;

				case 5:
					run();// back to main menu
					break;

				default:
					System.out.println(ERRORMESSAGE);
					break;
				}
			} catch (InputMismatchException e) {
				String bad_input = createFileScanner.next();
				System.out.println("Please enter a number");
				continue;
			}
		} while (op != 4 || op != 5);
	}
	
	@Override
	public boolean caseSensitive(String path, String name) {
		boolean flag;
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		List n = new ArrayList<String>();

		for (File file : listOfFiles) {
			if (file.isFile()) {
				n.add(file.getName());
			}
		}

		flag = n.contains(name + ".txt");
		return flag;
	}
	
	@Override
	public void deleteFile() {
		Scanner sc = new Scanner(System.in);
		System.out.println("please enter directory name");
		String folder = sc.next();
		if (!new File(DEFAULTPATH + "\\" + folder).exists()) {
			System.out.println("Folder name is wrong, please try again.");
			displayFileManipulationMenu();
		} else {
			System.out.println("please enter file name");
			String name = sc.next();
			if (caseSensitive(DEFAULTPATH + "\\" + folder, name)) {
				try {
					File f = new File(DEFAULTPATH + "\\" + folder + "\\" + name + ".txt");
					if (f.delete()) {
						System.out.println(f.getName() + " deleted");
						displayFileManipulationMenu();
					} else {
						System.out.println("File not found.");
					}
				} catch (Exception e) {
					System.out.println(ERRORMESSAGE);
				}
			} else {
				System.out.println("File not found, case sensitive, please try again.");
				displayFileManipulationMenu();
			}
		}
	}
	
	@Override
	public void searchFile() {
		Scanner sc = new Scanner(System.in);
		System.out.println("please enter directory name where you want to search.");
		String folder = sc.next();
		if (!new File(DEFAULTPATH + "\\" + folder).exists()) {
			System.out.println("Folder name is wrong, please try again.");
			displayFileManipulationMenu();
		} else {
			System.out.println("please enter the file name which you want to search.");
			String name = sc.next();
			if (caseSensitive(DEFAULTPATH + "\\" + folder, name)) {
				try {
					File f = new File(DEFAULTPATH + "\\" + folder + "\\" + name + ".txt");
					if (f.exists()) {
						System.out.println("Found it! " + f.getAbsolutePath());
						displayFileManipulationMenu();
					} else {
						System.out.println("File not found.");
					}
				} catch (Exception e) {
					System.out.println(ERRORMESSAGE);
				}
			} else {
				System.out.println("File not found, case sensitive, please try again.");
				displayFileManipulationMenu();
			}
		}
	}
	// -----------------------------------------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------------------------------------
	// helper method for createFile method
	
	@Override
	
	// throw Custom Exception
	public void addFile() throws UnableToCreateNewFileException {
		System.out.println(
				"***************************************************************************************************");
		System.out.println("Please enter your file name.");
		System.out.println(
				"***************************************************************************************************");
		Scanner nameScanner = new Scanner(System.in);
		String name = nameScanner.next();
		File file = new File(TEMPPATH + "\\" + name + ".txt");
		try {
			if (file.createNewFile()) {
				System.out.println(
						"***************************************************************************************************");
				System.out.println(name + " File created.");
				System.out.println(
						"***************************************************************************************************");
			} else {
				throw new UnableToCreateNewFileException("File already exists.");
				

			}
		} catch (IOException e) {
			System.out.println(ERRORMESSAGE);
		} finally {
			displayAddNewFileMenu();
		}

	}
	
	@Override
	public void changeDirectory() {
		showDirectories(DEFAULTPATH);
		Scanner sc = new Scanner(System.in);
		String path = sc.next();
		System.out.println(
				"***************************************************************************************************");
		System.out.println("Please enter your file name.");
		System.out.println(
				"***************************************************************************************************");
		String name = sc.next();
		File file = new File(DEFAULTPATH + "\\" + path + "\\" + name + ".txt");
		try {
			if (file.createNewFile()) {
				System.out.println(
						"***************************************************************************************************");
				System.out.println(name + " File created.");
				System.out.println(
						"***************************************************************************************************");

			} else {
				System.out.println(
						"***************************************************************************************************");
				System.out.println("File already exists.");
				System.out.println(
						"***************************************************************************************************");
			}
		} catch (IOException e) {
			System.out.println(ERRORMESSAGE);

		} finally {
			displayAddNewFileMenu();
		}
	}
	
	@Override
	public void createNewDirectory() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the name of the desired a directory: ");
		String path = DEFAULTPATH + "\\" + sc.next();
		File file = new File(path);
		boolean bool = file.mkdir();
		if (bool) {
			System.out.println(
					"***************************************************************************************************");
			System.out.println("New directory created successfully!");
			System.out.println(
					"***************************************************************************************************");
			displayAddNewFileMenu();
		} else {
			System.out.println(
					"***************************************************************************************************");
			System.out.println("Sorry couldn’t create specified directory.");
			System.out.println(
					"***************************************************************************************************");
			displayAddNewFileMenu();
		}

	}

}
