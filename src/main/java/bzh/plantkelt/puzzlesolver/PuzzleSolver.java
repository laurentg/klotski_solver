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

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import bzh.plantkelt.puzzlesolver.graph.Graph;

public class PuzzleSolver {

	private Graph<Board> graph;

	public PuzzleSolver() {
		graph = new Graph<Board>();
	}

	public void solve(Board initialPosition) {
		System.out.println("Solving board:");
		initialPosition.print(System.out);
		Queue<Board> processList = new LinkedList<Board>();
		processList.add(initialPosition);
		Board finalPosition = new FinalBoard();
		graph.put(initialPosition);
		graph.put(finalPosition);

		while (processList.size() > 0) {
			Board kb = processList.remove();
			if (kb.isWinning()) {
				graph.addLink(finalPosition, kb);
				graph.addLink(kb, finalPosition);
			}

			List<Board> moves = kb.computeMoves();
			for (Board nextkb : moves) {
				if (!graph.containsNode(nextkb))
					processList.add(nextkb);
				nextkb = graph.put(nextkb);
				graph.addLink(kb, nextkb);
			}
		}
		System.out.println("Found " + graph.size() + " boards.");
		System.out.println("Found " + graph.getLinks(finalPosition).size()
				+ " winning boards.");
		List<Board> path = graph.getPath(initialPosition, finalPosition);
		System.out.println("Solution in " + path.size() + " steps:");
		int step = 0;
		for (Board b : path) {
			System.out.println("Step " + step + ":");
			b.print(System.out);
			step++;
		}
	}

}
