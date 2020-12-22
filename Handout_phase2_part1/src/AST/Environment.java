package AST;

import java.util.*;



public class Environment<V> {

	private Map<String, V> map;
	private Environment<V> e;
	private int depth;

	public Environment(Environment<V> e) {
		map = new HashMap<String, V>();
		depth = (e == null ? 0 : (e.getDepth() + 1));
		this.e = e;

	}

	// — push level
	public Environment<V> beginScope() {
		Environment<V> env = new Environment<V>(this);
		return env;
	};

	// - pop top level
	public Environment<V> endScope() {
		return e;
	};

	public void assoc(String id, V val) {
		map.put(id, val);
	};

	public V find(String id) {

		V var = this.map.get(id);
		if (var == null)
			var = this.e.find(id);
		return var;

	}

	public int getDepth() {

		return depth;
	}

}
