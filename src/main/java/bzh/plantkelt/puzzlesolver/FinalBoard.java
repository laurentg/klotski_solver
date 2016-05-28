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
package bzh.plantkelt.puzzlesolver;

import java.io.PrintStream;
import java.util.List;

public class FinalBoard implements Board {

	public List<Board> computeMoves() {
		throw new UnsupportedOperationException("Not implemented");
	}

	public Long getKey() {
		throw new UnsupportedOperationException("Not implemented");
	}

	public boolean isWinning() {
		throw new UnsupportedOperationException("Not implemented");
	}

	public void print(PrintStream ps) {
		ps.println("Final board.");
	}

	public String toString() {
		return "END";
	}

}
