/*
 * @(#)CursorParse.java $version 2012. 12. 13.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nhncorp.myapp;

import java.io.FileInputStream;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

/**
 * @author NHN I&S
 */
public class MyStreamFilter implements javax.xml.stream.StreamFilter {
	public static void main(String[] args) throws Exception {
		String filename = "D:/DATA/test/BookCatalog.xml";

		XMLInputFactory xmlif = null;

		xmlif = XMLInputFactory.newInstance();
		xmlif.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, Boolean.TRUE);
		xmlif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
		xmlif.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.TRUE);
		xmlif.setProperty(XMLInputFactory.IS_COALESCING, Boolean.TRUE);

		System.out.println("FACTORY: " + xmlif);
		System.out.println("filename = " + filename);

		FileInputStream fis = new FileInputStream(filename);

		XMLStreamReader xmlr = xmlif.createFilteredReader(xmlif.createXMLStreamReader(fis), new MyStreamFilter());

		int eventType = xmlr.getEventType();
		printEventType(eventType);

		while (xmlr.hasNext()) {
			eventType = xmlr.next();
			printEventType(eventType);
			printName(xmlr, eventType);
			printText(xmlr);

			if (xmlr.isStartElement()) {
				printAttributes(xmlr);
			}
			printPIData(xmlr);
		}
	}

	public static final String getEventTypeString(int eventType) {
		switch (eventType) {
			case XMLEvent.START_ELEMENT:
				return "START_ELEMENT";

			case XMLEvent.END_ELEMENT:
				return "END_ELEMENT";

			case XMLEvent.PROCESSING_INSTRUCTION:
				return "PROCESSING_INSTRUCTION";

			case XMLEvent.CHARACTERS:
				return "CHARACTERS";

			case XMLEvent.COMMENT:
				return "COMMENT";

			case XMLEvent.START_DOCUMENT:
				return "START_DOCUMENT";

			case XMLEvent.END_DOCUMENT:
				return "END_DOCUMENT";

			case XMLEvent.ENTITY_REFERENCE:
				return "ENTITY_REFERENCE";

			case XMLEvent.ATTRIBUTE:
				return "ATTRIBUTE";

			case XMLEvent.DTD:
				return "DTD";

			case XMLEvent.CDATA:
				return "CDATA";
		}

		return "UNKNOWN_EVENT_TYPE";
	}

	private static void printEventType(int eventType) {
		System.out.print("EVENT TYPE(" + eventType + "):");
		System.out.println(getEventTypeString(eventType));
	}

	private static void printName(XMLStreamReader xmlr, int eventType) {
		if (xmlr.hasName()) {
			System.out.println("HAS NAME: " + xmlr.getLocalName());
		} else {
			System.out.println("HAS NO NAME");
		}
	}

	private static void printText(XMLStreamReader xmlr) {
		if (xmlr.hasText()) {
			System.out.println("HAS TEXT: " + xmlr.getText());
		} else {
			System.out.println("HAS NO TEXT");
		}
	}

	private static void printPIData(XMLStreamReader xmlr) {
		if (xmlr.getEventType() == XMLEvent.PROCESSING_INSTRUCTION) {
			System.out.println(" PI target = " + xmlr.getPITarget());
			System.out.println(" PI Data = " + xmlr.getPIData());
		}
	}

	private static void printAttributes(XMLStreamReader xmlr) {
		if (xmlr.getAttributeCount() > 0) {
			System.out.println("\nHAS ATTRIBUTES: ");

			int count = xmlr.getAttributeCount();

			for (int i = 0; i < count; i++) {
				QName name = xmlr.getAttributeName(i);
				String namespace = xmlr.getAttributeNamespace(i);
				String type = xmlr.getAttributeType(i);
				String prefix = xmlr.getAttributePrefix(i);
				String value = xmlr.getAttributeValue(i);

				System.out.println("ATTRIBUTE-PREFIX: " + prefix);
				System.out.println("ATTRIBUTE-NAMESP: " + namespace);
				System.out.println("ATTRIBUTE-NAME:   " + name.toString());
				System.out.println("ATTRIBUTE-VALUE:  " + value);
				System.out.println("ATTRIBUTE-TYPE:  " + type);
			}
		} else {
			System.out.println("HAS NO ATTRIBUTES");
		}
	}

	public boolean accept(XMLStreamReader reader) {
		if (!reader.isStartElement() && !reader.isEndElement()) {
			return false;
		} else {
			return true;
		}
	}
}