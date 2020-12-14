package com.framework.generic;

import com.framework.exception.UnableToCreateNewFileException;

public interface FileGeneratorInterface {
	public void printHeader();
	public void run();	
	public void displayMainMenu();
	public void displayFileManipulationMenu();
	public void displayAddNewFileMenu();
	public void fileMenu();
	public void showDirectories(String dir);
	public void showFileListByAscOrder();
	public void createFile();
	public boolean caseSensitive(String path, String name);
	public void deleteFile();
	public void searchFile();
	public void addFile() throws UnableToCreateNewFileException;
	public void changeDirectory();
	public void createNewDirectory();
	
	
}
