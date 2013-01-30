package com.nhncorp.myapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {
	private File file;

	public CSVLoader(String fileName) {
		file = new File(fileName);
	}

	public List<Object[]> loadAll() {
		List<Object[]> objectList = new ArrayList<Object[]>();
		try {
			BufferedReader br = readerInitialize();
			String line = null;
			while ((line = br.readLine()) != null) {
				objectList.add(commaSeperatedStringsToObjects(line));
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return objectList;
	}

	private BufferedReader readerInitialize() {
		BufferedReader br =null;
		try {
			br = new BufferedReader(new FileReader(this.file));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		return br;
	}

	public Object[] commaSeperatedStringsToObjects(String line) {
		String[] splitedStrings = line.split(",");
		Object[] obj =new Object[splitedStrings.length];
		for(int i =0; i <  splitedStrings.length;i++){
			obj[i] =  toIntegerIfPossible(splitedStrings[i]);
		}
		return obj;	
	}
	
	private Object toIntegerIfPossible(String str){
		Object obj =null;
		try {
			obj =  Integer.parseInt(str);
		} catch (NumberFormatException e) {
			obj = str;
		}
		return obj;
		
	}

}
