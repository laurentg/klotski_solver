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

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Graph<T> {

	private Map<T, GraphNode<T>> nodes;

	public Graph() {
		nodes = new HashMap<T, GraphNode<T>>();
	}

	public Set<T> getLinks(T node) {
		GraphNode<T> gnode = nodes.get(node);
		if (gnode == null)
			throw new IllegalArgumentException("Node not in graph");
		Set<GraphNode<T>> links = gnode.getLinks();
		Set<T> ret = new HashSet<T>();
		for (GraphNode<T> gn : links)
			ret.add(gn.getNode());
		return ret;
	}

	public boolean containsNode(T node) {
		return nodes.containsKey(node);
	}

	public T put(T node) {
		if (containsNode(node)) {
			return nodes.get(node).getNode();
		} else {
			nodes.put(node, new GraphNode<T>(node));
			return node;
		}
	}

	public void addLink(T from, T to) {
		GraphNode<T> gnfrom = nodes.get(from);
		GraphNode<T> gnto = nodes.get(to);
		if (gnfrom == null || gnto == null)
			throw new IllegalArgumentException("Node not in graph");
		gnfrom.addLink(gnto);
	}

	public int size() {
		return nodes.size();
	}

	public void check() {
		for (Map.Entry<T, GraphNode<T>> e : nodes.entrySet()) {
			GraphNode<T> gnode = e.getValue();
			for (GraphNode<T> gnode2 : gnode.getLinks()) {
				if (!gnode2.isLinked(gnode))
					throw new AssertionError("Link not symetric");
			}
		}
	}

	private void fillDistance(T node) {
		for (Map.Entry<T, GraphNode<T>> e : nodes.entrySet())
			e.getValue().setDistance(-1);

		GraphNode<T> gnode = nodes.get(node);
		gnode.setDistance(0);
		Queue<GraphNode<T>> processList = new LinkedList<GraphNode<T>>();
		processList.add(gnode);

		while (processList.size() > 0) {
			GraphNode<T> gn = processList.remove();
			int distance = gn.getDistance();
			for (GraphNode<T> gn2 : gn.getLinks()) {
				if (gn2.getDistance() == -1) {
					gn2.setDistance(distance + 1);
					processList.add(gn2);
				}
			}
		}
	}

	public List<T> getPath(T from, T to) {
		List<T> ret = new LinkedList<T>();
		fillDistance(to);
		GraphNode<T> gn = nodes.get(from);
		while (true) {
			ret.add(gn.getNode());
			int min = Integer.MAX_VALUE;
			GraphNode<T> nextgn = null;
			for (GraphNode<T> gn2 : gn.getLinks()) {
				if (gn2.getDistance() < min) {
					min = gn2.getDistance();
					nextgn = gn2;
				}
			}
			if (min != gn.getDistance() - 1)
				throw new AssertionError("distance delta not 1");
			if (nextgn == null)
				throw new AssertionError("next node null");
			gn = nextgn;
			if (gn.getDistance() == 0)
				break;
		}
		return ret;
	}
}
