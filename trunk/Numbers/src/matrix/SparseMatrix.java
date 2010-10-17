package matrix;

import java.util.TreeMap;
import java.util.Map.Entry;

public class SparseMatrix<T> implements Matrix<T>
{
	private final TreeMap<Integer, T>[] rows;
	private final TreeMap<Integer, T>[] columns;	
	

	public SparseMatrix(int n, int m) {
		rows = (TreeMap<Integer, T>[]) new TreeMap<?, ?>[n];
		columns = (TreeMap<Integer, T>[]) new TreeMap<?, ?>[m];
		for(int i = 0;i<n;i++)
			rows[i] = new TreeMap<Integer, T>();
		for(int j = 0;j<m;j++)
			columns[j] = new TreeMap<Integer, T>();
	}
	
	
	public void set(int row, int column, T value) {
		rows[row].put(column, value);
		columns[column].put(row, value);
	}
	
	public void remove(int row, int column) {
		rows[row].remove(column);
		columns[column].remove(row);
	}
	
	public TreeMap<Integer,T> getColumn(int column) { 
		return columns[column];
	}

	public TreeMap<Integer,T> getRow(int row) { 
		return rows[row];
	}
	
	public int columns() {
		return columns.length;
	}

	public int rows() {
		return rows.length;
	}
	
	private static <T> void swap(int a, int b, TreeMap<Integer, T>[] fixed, TreeMap<Integer, T>[] changing) {
		TreeMap<Integer, T> aMap = changing[a];
		TreeMap<Integer, T> bMap = changing[b];
		
		for (Integer i : aMap.keySet())
			fixed[i].remove(a);
		for (Integer i : bMap.keySet())
			fixed[i].remove(b);

		for (Entry<Integer, T> e : aMap.entrySet())
			fixed[e.getKey()].put(b, e.getValue());
		for (Entry<Integer, T> e : bMap.entrySet())
			fixed[e.getKey()].put(a, e.getValue());
		
		changing[b] = aMap;
		changing[a] = bMap;		
	}
	
	public void swapRows(int row1, int row2) {
		swap(row1, row2, columns, rows);
	}
	
	public void swapColumns(int column1, int column2) {
		swap(column1, column2, rows, columns);
	}
	
	
	public void recheck() {
		for(int i = 0;i<rows.length;i++)
			for (Entry<Integer, T> e : rows[i].entrySet())
				if (e.getValue()!=columns[e.getKey()].get(i))
					throw new RuntimeException();

		for(int i = 0;i<columns.length;i++)
			for (Entry<Integer, T> e : columns[i].entrySet())
				if (e.getValue()!=rows[e.getKey()].get(i))
					throw new RuntimeException();
	}
	
	
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		recheck();
		for(int i = 0;i<rows.length;i++) {
			for(int j = 0;j<columns.length;j++)
				sb.append(rows[i].get(j)+"\t");
			sb.append("\n");
		}
		return sb.toString();
	}

}


