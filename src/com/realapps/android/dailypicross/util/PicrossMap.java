package com.realapps.android.dailypicross.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PicrossMap {
	public static final int TYPE_NOT  = -1;
	public static final int TYPE_NONE = 0;
	public static final int TYPE_MARKER = 1;
	public static final int TYPE_PENCIL = 2;
	
	public static PicrossMap getInstance(String path) throws IOException {
		return new PicrossMap(new File(path));
	}
	
	private int length = 0;
	private boolean[][] mAnswer = null; 
	private int[][] mInput = null;
	private PicrossMap(File file) throws IOException {
		FileReader input = new FileReader(file);
		int len = 0;
		String result = ""; char[] buf = new char[1024];
		while((len = input.read(buf)) > 0) {
			result += new String(buf, 0, len);
		}
		
		String[] lines = result.split("\n");
		length = lines.length;
		mInput = new int[length][length];
		mAnswer = new boolean[length][length];
		for(int y=0; y<lines.length; y++) {
			String line = lines[y].trim();
			
			if(line.length() == length) {
				for(int x=0; x<length; x++) {
					char c = line.charAt(x);
					mInput[y][x] = TYPE_NONE;
					if(c == '-') 
						mAnswer[y][x] = false;
					else if(c == '*')
						mAnswer[y][x] = true;
				}
			} else {
				throw new ArrayIndexOutOfBoundsException("line "+y+" has illegal ("+line.length()+", "+length+")\n"+line);
			}
		}
	}
	
	public boolean[][] getAnswer() {
		return mAnswer;
	}
	
	public boolean check(int type, int x, int y) {
		mInput[y][x] = type;
		
		for(y=0; y<length; y++) {
			for(x=0; x<length; x++) {
				if(mAnswer[y][x]) {
					if(mInput[y][x] != TYPE_MARKER)
						return false;
				} else {
					if(mInput[y][x] != TYPE_NONE && mInput[y][x] != TYPE_NOT)
						return false;
				}
			}
		}
		return true;
	}
	public int getInput(int x, int y) {
		return mInput[y][x];
	}
	
	public int getSize() {
		return length;
	}
	
	public int[] getColHint(int pos) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		
		if(pos < 0 || pos >= length) {
			throw new IllegalArgumentException("argument is "+pos+" (max is "+length+")");
		}
		
		// 세로배열 구하기
		boolean[] set = new boolean[length];
		for(int i=0; i<length; i++)
			set[i] = mAnswer[i][pos];
		
		// 힌트 구하기
		int cnt = 0;
		for(boolean x: set) {
			if(x == true) {
				cnt++;
			} else {
				if(cnt > 0) {
					arr.add(cnt);
					cnt = 0;
				}
			}
		}
		
		if(cnt > 0) {
			arr.add(cnt);
			cnt = 0;
		}
		
		// 배열로 변환
		int[] result = new int[arr.size()];
		for(int i=0; i<arr.size(); i++) {
			result[i] = arr.get(i);
		}
		
		return result;
	}
	
	public int[] getRowHint(int pos) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		
		if(pos < 0 || pos >= length) {
			throw new IllegalArgumentException("argument is "+pos+" (max is "+length+")");
		}
		
		// 세로배열 구하기
		boolean[] set = mAnswer[pos];
		
		// 힌트 구하기
		int cnt = 0;
		for(boolean x: set) {
			if(x == true) {
				cnt++;
			} else {
				if(cnt > 0) {
					arr.add(cnt);
					cnt = 0;
				}
			}
		}
		
		if(cnt > 0) {
			arr.add(cnt);
			cnt = 0;
		}
		
		// 배열로 변환
		int[] result = new int[arr.size()];
		for(int i=0; i<arr.size(); i++) {
			result[i] = arr.get(i);
		}
		
		return result;
	}
}
