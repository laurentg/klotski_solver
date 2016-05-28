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
package bzh.plantkelt.puzzlesolver.graph;

import java.util.HashSet;
import java.util.Set;

public class GraphNode<T> {

	private Set<GraphNode<T>> links;
	private T node;
	private int distance;

	public GraphNode(T node) {
		this.node = node;
		links = new HashSet<GraphNode<T>>();
	}

	public T getNode() {
		return node;
	}

	public Set<GraphNode<T>> getLinks() {
		return links;
	}

	public void addLink(GraphNode<T> node) {
		links.add(node);
	}

	public boolean isLinked(GraphNode<T> node) {
		return links.contains(node);
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getDistance() {
		return distance;
	}

	public int hashCode() {
		return node.hashCode();
	}
}
