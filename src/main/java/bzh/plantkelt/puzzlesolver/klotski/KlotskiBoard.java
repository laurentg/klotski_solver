/*
	Klotski Puzzle Solver.
    Copyright (C) 2007,2016 Laurent GRÉGOIRE (laurent.gregoire@gmail.com)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package bzh.plantkelt.puzzlesolver.klotski;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import bzh.plantkelt.puzzlesolver.Board;

public class KlotskiBoard implements Board {

	private static final int XSIZE = 4;
	private static final int YSIZE = 5;

	private static final int KEY[] = { 0, 1, 2, 3, 3, 4, 4 };

	public static final int EMPTY = 0;
	public static final int SMALL_SQUARE = 1;
	public static final int LARGE_SQUARE = 2;
	public static final int HRECT_1 = 3;
	public static final int HRECT_2 = 4;
	public static final int VRECT_1 = 5;
	public static final int VRECT_2 = 6;

	private int[][] e;

	private List<KlotskiBoard> links = null;

	private Long myKey;

	public KlotskiBoard() {
		e = new int[XSIZE][];
		for (int x = 0; x < XSIZE; x++)
			e[x] = new int[YSIZE];
	}

	public static KlotskiBoard dixHuitCoups() {
		KlotskiBoard kb = new KlotskiBoard();
		kb.e = new int[][] {
				{ SMALL_SQUARE, SMALL_SQUARE, SMALL_SQUARE, SMALL_SQUARE,
						SMALL_SQUARE },
				{ LARGE_SQUARE, LARGE_SQUARE, SMALL_SQUARE, SMALL_SQUARE, EMPTY },
				{ LARGE_SQUARE, LARGE_SQUARE, SMALL_SQUARE, SMALL_SQUARE, EMPTY },
				{ SMALL_SQUARE, SMALL_SQUARE, SMALL_SQUARE, SMALL_SQUARE,
						SMALL_SQUARE }, };
		return kb;
	}

	public static KlotskiBoard daisy() {
		KlotskiBoard kb = new KlotskiBoard();
		kb.e = new int[][] {
				{ VRECT_1, VRECT_2, SMALL_SQUARE, SMALL_SQUARE, SMALL_SQUARE },
				{ LARGE_SQUARE, LARGE_SQUARE, SMALL_SQUARE, SMALL_SQUARE, EMPTY },
				{ LARGE_SQUARE, LARGE_SQUARE, SMALL_SQUARE, SMALL_SQUARE, EMPTY },
				{ VRECT_1, VRECT_2, SMALL_SQUARE, SMALL_SQUARE, SMALL_SQUARE }, };
		return kb;
	}

	public static KlotskiBoard violette() {
		KlotskiBoard kb = new KlotskiBoard();
		kb.e = new int[][] {
				{ VRECT_1, VRECT_2, VRECT_1, VRECT_2, SMALL_SQUARE },
				{ LARGE_SQUARE, LARGE_SQUARE, SMALL_SQUARE, SMALL_SQUARE, EMPTY },
				{ LARGE_SQUARE, LARGE_SQUARE, SMALL_SQUARE, SMALL_SQUARE, EMPTY },
				{ VRECT_1, VRECT_2, SMALL_SQUARE, SMALL_SQUARE, SMALL_SQUARE }, };
		return kb;
	}

	public static KlotskiBoard poppy() {
		KlotskiBoard kb = new KlotskiBoard();
		kb.e = new int[][] {
				{ VRECT_1, VRECT_2, SMALL_SQUARE, SMALL_SQUARE, SMALL_SQUARE },
				{ LARGE_SQUARE, LARGE_SQUARE, HRECT_1, SMALL_SQUARE, EMPTY },
				{ LARGE_SQUARE, LARGE_SQUARE, HRECT_2, SMALL_SQUARE, EMPTY },
				{ VRECT_1, VRECT_2, SMALL_SQUARE, SMALL_SQUARE, SMALL_SQUARE }, };
		return kb;
	}

	public static KlotskiBoard pansy() {
		KlotskiBoard kb = new KlotskiBoard();
		kb.e = new int[][] {
				{ VRECT_1, VRECT_2, VRECT_1, VRECT_2, SMALL_SQUARE },
				{ LARGE_SQUARE, LARGE_SQUARE, SMALL_SQUARE, SMALL_SQUARE, EMPTY },
				{ LARGE_SQUARE, LARGE_SQUARE, SMALL_SQUARE, SMALL_SQUARE, EMPTY },
				{ VRECT_1, VRECT_2, VRECT_1, VRECT_2, SMALL_SQUARE }, };
		return kb;
	}

	public static KlotskiBoard perceNeige() {
		KlotskiBoard kb = new KlotskiBoard();
		kb.e = new int[][] {
				{ VRECT_1, VRECT_2, VRECT_1, VRECT_2, SMALL_SQUARE },
				{ LARGE_SQUARE, LARGE_SQUARE, HRECT_1, SMALL_SQUARE, EMPTY },
				{ LARGE_SQUARE, LARGE_SQUARE, HRECT_2, SMALL_SQUARE, EMPTY },
				{ VRECT_1, VRECT_2, SMALL_SQUARE, SMALL_SQUARE, SMALL_SQUARE }, };
		return kb;
	}

	public static KlotskiBoard aneRouge() {
		KlotskiBoard kb = new KlotskiBoard();
		kb.e = new int[][] {
				{ VRECT_1, VRECT_2, VRECT_1, VRECT_2, SMALL_SQUARE },
				{ LARGE_SQUARE, LARGE_SQUARE, HRECT_1, SMALL_SQUARE, EMPTY },
				{ LARGE_SQUARE, LARGE_SQUARE, HRECT_2, SMALL_SQUARE, EMPTY },
				{ VRECT_1, VRECT_2, VRECT_1, VRECT_2, SMALL_SQUARE }, };
		return kb;
	}

	public static KlotskiBoard course() {
		KlotskiBoard kb = new KlotskiBoard();
		kb.e = new int[][] {
				{ VRECT_1, VRECT_2, SMALL_SQUARE, SMALL_SQUARE, EMPTY },
				{ LARGE_SQUARE, LARGE_SQUARE, HRECT_1, HRECT_1, HRECT_1 },
				{ LARGE_SQUARE, LARGE_SQUARE, HRECT_2, HRECT_2, HRECT_2 },
				{ VRECT_1, VRECT_2, SMALL_SQUARE, SMALL_SQUARE, EMPTY } };
		return kb;
	}

	public static KlotskiBoard embuscade() {
		KlotskiBoard kb = new KlotskiBoard();
		kb.e = new int[][] {
				{ SMALL_SQUARE, VRECT_1, VRECT_2, SMALL_SQUARE, EMPTY },
				{ LARGE_SQUARE, LARGE_SQUARE, HRECT_1, HRECT_1, HRECT_1 },
				{ LARGE_SQUARE, LARGE_SQUARE, HRECT_2, HRECT_2, HRECT_2 },
				{ SMALL_SQUARE, VRECT_1, VRECT_2, SMALL_SQUARE, EMPTY }, };
		return kb;
	}

	private KlotskiBoard getMove(int xsource, int ysource, int xsz, int ysz,
			int xdelta, int ydelta) {
		KlotskiBoard kb = new KlotskiBoard();
		for (int x = 0; x < XSIZE; x++)
			for (int y = 0; y < YSIZE; y++)
				kb.e[x][y] = e[x][y];
		for (int x = 0; x < xsz; x++)
			for (int y = 0; y < ysz; y++)
				kb.e[xsource + x][ysource + y] = EMPTY;
		for (int x = 0; x < xsz; x++)
			for (int y = 0; y < ysz; y++)
				kb.e[xsource + x + xdelta][ysource + y + ydelta] = e[xsource
						+ x][ysource + y];
		return kb;
	}

	private void addSmallSquareMoves(int x, int y, List<Board> list) {
		if (x > 0 && e[x - 1][y] == SMALL_SQUARE)
			list.add(getMove(x - 1, y, 1, 1, 1, 0));
		if (x < XSIZE - 1 && e[x + 1][y] == SMALL_SQUARE)
			list.add(getMove(x + 1, y, 1, 1, -1, 0));
		if (y > 0 && e[x][y - 1] == SMALL_SQUARE)
			list.add(getMove(x, y - 1, 1, 1, 0, 1));
		if (y < YSIZE - 1 && e[x][y + 1] == SMALL_SQUARE)
			list.add(getMove(x, y + 1, 1, 1, 0, -1));
	}

	private void addRectMovesA(int x, int y, List<Board> list) {
		if (x > 1 && e[x - 1][y] == HRECT_2)
			list.add(getMove(x - 2, y, 2, 1, 1, 0));
		if (x < XSIZE - 2 && e[x + 1][y] == HRECT_1)
			list.add(getMove(x + 1, y, 2, 1, -1, 0));
		if (y > 1 && e[x][y - 1] == VRECT_2)
			list.add(getMove(x, y - 2, 1, 2, 0, 1));
		if (y < YSIZE - 2 && e[x][y + 1] == VRECT_1)
			list.add(getMove(x, y + 1, 1, 2, 0, -1));
	}

	private void addRectMovesB(int x1, int y1, int x2, int y2, List<Board> list) {
		if (y1 == y2 && x1 + 1 == x2) {
			if (y1 > 0 && e[x1][y1 - 1] == HRECT_1)
				list.add(getMove(x1, y1 - 1, 2, 1, 0, 1));
			if (y1 < YSIZE - 1 && e[x1][y1 + 1] == HRECT_1)
				list.add(getMove(x1, y1 + 1, 2, 1, 0, -1));
			if (y1 > 1 && e[x1][y1 - 1] == LARGE_SQUARE
					&& e[x2][y1 - 1] == LARGE_SQUARE)
				list.add(getMove(x1, y1 - 2, 2, 2, 0, 1));
			if (y1 < YSIZE - 2 && e[x1][y1 + 1] == LARGE_SQUARE
					&& e[x2][y1 + 1] == LARGE_SQUARE)
				list.add(getMove(x1, y1 + 1, 2, 2, 0, -1));
		} else if (x1 == x2 && y1 + 1 == y2) {
			if (x1 > 0 && e[x1 - 1][y1] == VRECT_1)
				list.add(getMove(x1 - 1, y1, 1, 2, 1, 0));
			if (x1 < XSIZE - 1 && e[x1 + 1][y1] == VRECT_1)
				list.add(getMove(x1 + 1, y1, 1, 2, -1, 0));
			if (x1 > 1 && e[x1 - 1][y1] == LARGE_SQUARE
					&& e[x1 - 1][y2] == LARGE_SQUARE)
				list.add(getMove(x1 - 2, y1, 2, 2, 1, 0));
			if (x1 < XSIZE - 2 && e[x1 + 1][y1] == LARGE_SQUARE
					&& e[x1 + 1][y2] == LARGE_SQUARE)
				list.add(getMove(x1 + 1, y1, 2, 2, -1, 0));
		}
	}

	public List<Board> computeMoves() {
		List<Board> ret = new ArrayList<Board>();
		/* Find position of the two empty cells */
		boolean first = true;
		int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
		for (int x = 0; x < XSIZE; x++)
			for (int y = 0; y < YSIZE; y++) {
				if (e[x][y] == EMPTY)
					if (first) {
						x1 = x;
						y1 = y;
						first = false;
					} else {
						x2 = x;
						y2 = y;
					}
			}
		addSmallSquareMoves(x1, y1, ret);
		addSmallSquareMoves(x2, y2, ret);
		addRectMovesA(x1, y1, ret);
		addRectMovesA(x2, y2, ret);
		addRectMovesB(x1, y1, x2, y2, ret);
		return ret;
	}

	public void print(PrintStream ps) {
		ps.println("BOARD No " + getKey());
		for (int y = 0; y < YSIZE; y++) {
			ps.print("  ");
			for (int x = 0; x < XSIZE; x++) {
				switch (e[x][y]) {
				case EMPTY:
					ps.print("  ");
					break;
				case SMALL_SQUARE:
					ps.print("[]");
					break;
				case LARGE_SQUARE:
					ps.print("@@");
					break;
				case VRECT_1:
				case VRECT_2:
					ps.print("||");
					break;
				case HRECT_1:
				case HRECT_2:
					ps.print("==");
					break;
				}
			}
			ps.println();
		}
	}

	public boolean isWinning() {
		return (e[1][YSIZE - 2] == LARGE_SQUARE
				&& e[2][YSIZE - 2] == LARGE_SQUARE
				&& e[1][YSIZE - 1] == LARGE_SQUARE && e[2][YSIZE - 1] == LARGE_SQUARE);
	}

	public Long getKey() {
		if (myKey == null) {
			long key = 0L;
			for (int x = 0; x < XSIZE; x++)
				for (int y = 0; y < YSIZE; y++) {
					key += KEY[e[x][y]];
					key *= 5;
				}
			myKey = new Long(key);
		}
		return myKey;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Board))
			return false;
		return (((Board) o).getKey().equals(this.getKey()));
	}

	public int hashCode() {
		return getKey().intValue();
	}

	public void setLinks(List<KlotskiBoard> links) {
		this.links = links;
	}

	public List<KlotskiBoard> getLinks() {
		return links;
	}

	public String toString() {
		return getKey().toString();
	}
}