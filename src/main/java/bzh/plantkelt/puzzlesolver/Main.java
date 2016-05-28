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

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

import bzh.plantkelt.puzzlesolver.klotski.KlotskiBoard;

public class Main {

	private static List<String> listBoardNames(Class<?> aClass) {
		List<String> ret = new LinkedList<String>();
		for (Method m : aClass.getMethods()) {
			if (Modifier.isStatic(m.getModifiers())
					&& (Board.class.isAssignableFrom(m.getReturnType()))
					&& (m.getParameterTypes().length == 0)) {
				ret.add(m.getName());
			}
		}
		return ret;
	}

	private static Board getBoard(Class<?> aClass, String boardName) {
		try {
			Method m = aClass.getMethod(boardName, new Class[] {});
			return (Board) m.invoke(null, new Object[] {});
		} catch (Exception e) {
			return null;
		}
	}

	private static void usage() {
		StringBuffer sb = new StringBuffer("   java ");
		sb.append(Main.class.getName());
		sb.append(" [");
		List<String> boards = listBoardNames(KlotskiBoard.class);
		int i = 0;
		for (String board : boards) {
			sb.append(" -" + board + " ");
			i++;
			if (i < boards.size())
				sb.append("|");
		}
		sb.append("]");
		System.out.println("Usage:");
		System.out.println(sb.toString());
		System.exit(1);
	}

	public static void main(String args[]) {
		System.out
				.println("Klotski Solver - Copyright (C) 2007,2016 Laurent GRÉGOIRE");
		System.out
				.println("This program is GPLv3, and comes with ABSOLUTELY NO WARRANTY.");
		System.out
				.println("This is free software, and you are welcome to redistribute it");
		System.out
				.println("under certain conditions. For more details see LICENSE.txt.");

		if (args.length != 1)
			usage();
		PuzzleSolver ks = new PuzzleSolver();
		Board kb = getBoard(KlotskiBoard.class, args[0].substring(1));
		if (kb == null)
			usage();
		ks.solve(kb);
	}
}
