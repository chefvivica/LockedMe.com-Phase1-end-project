package com.bussiness.data;

public class Files {
	private String fileName;
	private String filePath;
	public Files(String fileName, String filePath) {
		super();
		this.fileName = fileName;
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Override
	public String toString() {
		return "Files [fileName=" + fileName + ", filePath=" + filePath + "]";
	}
	
	
	
}
