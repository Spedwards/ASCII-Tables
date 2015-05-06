package me.spedwards.table;

import java.util.*;

import org.apache.commons.lang3.StringEscapeUtils;

import org.json.*;

import me.spedwards.table.errors.*;

/**
 * Table object.
 * @author	Liam Edwards
 * @version	1.2.0
 */
public class Table {
	
	private ArrayList<String> header;
	private ArrayList<ArrayList<String>> rows;
	
	/**
	 * Initialize this Table with header row.
	 * @param	header	ArrayList<String> of cells to be used as the table header.
	 */
	public Table(ArrayList<String> header) {
		this.header = header;
		this.rows = new ArrayList<ArrayList<String>>();
	}
	
	/**
	 * Adds a row to this Table.
	 * @param	row						ArrayList<String> of cells to be added.
	 * @throws	TooFewCellsException	If a row has fewer cells than the header.
	 * @throws	TooManyCellsException	If a row has more cells than the header.
	 */
	public void addRow(ArrayList<String> row) throws TooFewCellsException,TooManyCellsException {
		if (row.size() < this.header.size()) {
			throw new TooFewCellsException("");
		} else if (row.size() > this.header.size()) {
			throw new TooManyCellsException("");
		} else {
			this.rows.add(row);
		}
	}
	
	/**
	 * Create a row
	 * @param	cells	an indefinite amount of strings to be used as cells.
	 * @return	an ArrayList<String> of cells.
	 * @since	1.1.0
	 */
	public static ArrayList<String> newRow(String... cells) {
		ArrayList<String> out = new ArrayList<String>();
		for (String cell : cells) {
			out.add(cell);
		}
		return out;
	}
	
	/**
	 * Gets header row of this Table.
	 * @return this Table header row.
	 */
	public ArrayList<String> getHeader() {
		return this.header;
	}
	
	/**
	 * Gets rows of this Table.
	 * @return	table rows.
	 */
	public ArrayList<ArrayList<String>> getRows() {
		return this.rows;
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
		
		for (int i = 0; i < this.header.size(); i++) {
			for (int j = 0; j < this.header.get(i).length() + 4; j++) {
				line += "-";
			}
			line += "+";
		}
		for (int i = 0; i < this.header.size(); i++) {
			tmp += "|  " + this.header.get(i) + "  ";
		}
		tmp += "|";
		headers = line + "\n" + tmp + "\n" + line;
		
		tmp = "";
		
		for (ArrayList<String> row : this.rows) {
			for (String cell : row) {
				body += "|  " + cell + "  ";
			}
			body += "|\n";
		}
		
		table = headers + "\n" + body + line;
		
		return table;
	}
	
	private void adjust() {
		for (int i = 0; i < rows.size(); i++) {
			for (int j = 0; j < rows.get(i).size(); j++) {
				if (rows.get(i).get(j).length() < header.get(j).length()) {
					rows.get(i).set(j, adjustCell(rows.get(i).get(j),header.get(j).length()));
				} else if (rows.get(i).get(j).length() > header.get(j).length()) {
					header.set(j, adjustCell(header.get(j), rows.get(i).get(j).length()));
				}
				for (int l = 0; l < i; l++) {
					if (rows.get(l).get(j).length() > rows.get(i).get(j).length()) {
						rows.get(i).set(j, adjustCell(rows.get(i).get(j),rows.get(l).get(j).length()));
					} else if (rows.get(l).get(j).length() < rows.get(i).get(j).length()) {
						rows.get(l).set(j, adjustCell(rows.get(l).get(j),rows.get(i).get(j).length()));
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
	
	/**
	 * Converts table to JSON string.
	 * @param	table	Table object.
	 * @return	JSON string of table.
	 * @since	1.2.0
	 */
	public static String toJSON(Table table) {
		ArrayList<String> header = table.getHeader();
		ArrayList<ArrayList<String>> rows = table.getRows();
		
		int countRows = rows.size();
		int countColumns = header.size();
		
		String headerArray = "[";
		for (String cell : header) {
			headerArray += "\"";
			headerArray += StringEscapeUtils.escapeJava(cell);
			headerArray += "\",";
		}
		headerArray += "]";
		
		String rowsArray = "[";
		for (ArrayList<String> row : rows) {
			rowsArray += "[";
			for (String cell : row) {
				rowsArray += "\"";
				rowsArray += StringEscapeUtils.escapeJava(cell);
				rowsArray += "\",";
			}
			rowsArray += "],";
		}
		rowsArray += "]";
		
		String json = String.format("{\"header\": %s, \"cells\": %s}",
			headerArray,
			rowsArray
		);
		
		return json.replaceAll(",]", "]");
	}
	
	/**
	 * Converts JSON string to table.
	 * @param	json					JSON string.
	 * @return	Table object.
	 * @since	1.2.0
	 */
	public static Table fromJSON(String json) {
		JSONObject obj = new JSONObject(json);
		JSONArray headerJSON = obj.getJSONArray("header");
		JSONArray rowsJSON = obj.getJSONArray("cells");
		
		ArrayList<String> header = new ArrayList<String>();
		for (int i = 0; i < headerJSON.length(); i++) {
			header.add(StringEscapeUtils.unescapeJava(headerJSON.getString(i)));
		}
		
		ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < rowsJSON.length(); i++) {
			JSONArray a = rowsJSON.getJSONArray(i);
			ArrayList<String> tmp = new ArrayList<String>();
			for (int j = 0; j < a.length(); j++) {
				tmp.add(StringEscapeUtils.unescapeJava(a.getString(j)));
			}
			rows.add(tmp);
		}
		
		Table t = new Table(header);
		for (ArrayList<String> row : rows) {
			t.addRow(row);
		}
		return t;
	}

}