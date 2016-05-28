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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *  Class not used. Here only for the purpose of wasting some place on disk. 
 */
public class GridMap<T> {

	private int n;
	private float size;
	private Map<Integer, List<T>> map;

	public GridMap(int n, float size) {
		this.n = n;
		this.size = size;
		map = new HashMap<Integer, List<T>>(n * n);
	}

	private Integer getKey(float x, float y) {
		int ix = (int) (x / size) % n;
		int iy = (int) (y / size) % n;
		if (ix < 0)
			ix += n;
		if (iy < 0)
			iy += n;
		Integer key = ix * n + iy;
		return key;
	}

	public void put(float x, float y, T t) {
		Integer key = getKey(x, y);
		List<T> list = map.get(key);
		if (list == null) {
			list = new LinkedList<T>();
			map.put(key, list);
		}
		list.add(t);
	}

	public List<T> getCell(float x, float y) {
		return map.get(getKey(x, y));
	}
}
