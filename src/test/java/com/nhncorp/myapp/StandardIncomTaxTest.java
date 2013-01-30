package com.nhncorp.myapp;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class StandardIncomTaxTest {
	
	 
	 

	@Parameters
	public static Collection data(){
		CSVLoader loader =  new CSVLoader("src/test/java/TaxData.csv");
		return  loader.loadAll();
	}
	private int income;
	private int taxAmount;
	private int year;
	
	
	public StandardIncomTaxTest(int income,int year,int taxAmount){
		this.income =income;
		this.year = year;
		this.taxAmount = taxAmount;
	}
	
	@Test
	public void testGetTaxAmount(){
		StandardTax incomTax =new StandardTax();
		assertEquals(this.taxAmount,incomTax.getTaxAmount(this.income));
	}
}
