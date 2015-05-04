package me.spedwards.table;

import java.util.*;

/**
 * Row object.
 * @author	Liam Edwards
 * @version	1.0.0
 */
public class Row {

	private ArrayList<String> cells = new ArrayList<String>();
	
	/**
	 * Initialize this Row with cells.
	 * @param	cells	Variable-Length Argument of Strings.
	 * @see		#addCell(String cell)
	 */
	public Row(String... cells) {
		for (String cell : cells) {
			addCell(cell);
		}
	}
	
	/**
	 * Add a new cell to this Row.
	 * @param	cell	String of text to put in cell.
	 */
	public void addCell(String cell) {
		this.cells.add(cell);
	}
	
	/**
	 * Gets the cells of this Row.
	 * @return 	the ArrayList<String> of cells.
	 */
	public ArrayList<String> getCells() {
		return this.cells;
	}

}