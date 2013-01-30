package com.nhncorp.myapp;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;





public class CSVLoaderTest {
	private CSVLoader loader;
	@Before
	public void setUp(){
		loader =  new CSVLoader("src/test/java/TaxData.csv");
		
	}
	
	@Test
	public void testLoadAll(){
		List<Object[]> loadObject = loader.loadAll();
		List<Object[]> expectedList = Arrays.asList(new Object[][]{
				{0,2008,0},
				{1000,2008,80},
				{1200,2008,96},
				{1205,2008,96},
				{1206,2008,97},
				{4600,2008,674},
				{5000,2008,778}
		});
		
		assertEquals(expectedList.size(),loadObject.size());
		assertArrayEquals(expectedList.toArray(),loadObject.toArray());
	}
	
	@Test
	public void testCommaSeperateedStringsToObjects(){
		assertArrayEquals(new Object[]{0,2008,0},loader.commaSeperatedStringsToObjects("0,2008,0") );
	}
	
	 
}
