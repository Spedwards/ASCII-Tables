package me.spedwards.table;

import java.util.*;

import me.spedwards.table.errors.*;

/**
 * Table object.
 * @author	Liam Edwards
 * @version	1.0.0
 */
public class Table {
	
	private Row header;
	private ArrayList<Row> rows = new ArrayList<Row>();
	
	/**
	 * Initialize this Table with header row.
	 * @param	header	row object to be used as the table header.
	 * @see		Row
	 */
	public Table(Row header) {
		this.header = header;
	}
	
	/**
	 * Adds a row to this Table.
	 * @param	row		row object to be added.
	 * @throws	TooFewCellsException	If a row has fewer cells than the header.
	 * @throws	TooManyCellsException	If a row has more cells than the header.
	 * @see		me.spedwards.table.Row
	 */
	public void addRow(Row row) throws TooFewCellsException,TooManyCellsException {
		if (row.getCells().size() < this.header.getCells().size()) {
			throw new TooFewCellsException("");
		} else if (row.getCells().size() > this.header.getCells().size()) {
			throw new TooManyCellsException("");
		} else {
			this.rows.add(row);
		}
	}
	
	/**
	 * Creates and converts this Table to a String.
	 * @return	this table as a String.
	 */
	public String create() {
		adjust();
		
		String table = "",
			tmp = "",
			headers = "",
			body = "",
			line = "+";
		
		for (int i = 0; i < header.getCells().size(); i++) {
			for (int j = 0; j < header.getCells().get(i).length() + 4; j++) {
				line += "-";
			}
			line += "+";
		}
		for (int i = 0; i < header.getCells().size(); i++) {
			tmp += "|  " + header.getCells().get(i) + "  ";
		}
		tmp += "|";
		headers = line + "\n" + tmp + "\n" + line;
		
		tmp = "";
		
		for (Row row : rows) {
			for (String cell : row.getCells()) {
				body += "|  " + cell + "  ";
			}
			body += "|\n";
		}
		
		table = headers + "\n" + body + line;
		
		return table;
	}
	
	private void adjust() {
		for (int i = 0; i < rows.size(); i++) {
			for (int j = 0; j < rows.get(i).getCells().size(); j++) {
				if (rows.get(i).getCells().get(j).length() < header.getCells().get(j).length()) {
					rows.get(i).getCells().set(j, adjustCell(rows.get(i).getCells().get(j),header.getCells().get(j).length()));
				} else if (rows.get(i).getCells().get(j).length() > header.getCells().get(j).length()) {
					header.getCells().set(j, adjustCell(header.getCells().get(j), rows.get(i).getCells().get(j).length()));
				}
				for (int l = 0; l < i; l++) {
					if (rows.get(l).getCells().get(j).length() > rows.get(i).getCells().get(j).length()) {
						rows.get(i).getCells().set(j, adjustCell(rows.get(i).getCells().get(j),rows.get(l).getCells().get(j).length()));
					} else if (rows.get(l).getCells().get(j).length() < rows.get(i).getCells().get(j).length()) {
						rows.get(l).getCells().set(j, adjustCell(rows.get(l).getCells().get(j),rows.get(i).getCells().get(j).length()));
					}
				}
			}
		}
	}
	
	private String adjustCell(String item, int length) {
		int k = 1;
		while (item.length() < length) {
			if (k % 2 == 0) {
				item = " " + item;
			} else {
				item += " ";
			}
			k++;
		}
		return item;
	}
	
	/**
	 * Converts this Table to a String.&nbsp;Same as<code>create()</code>.
	 * @return	this Table as a String.
	 * @see		#create()
	 */
	@Override
	public String toString() {
		return create();
	}

}