package AST;
import java.util.*;

public class Environment<V> {

	private Map<String, V> map;
	private Environment<V> e;

	public Environment(Environment<V> e) {
		map = new HashMap<String, V>();
		this.e = e;
	}

	// — push level
	Environment<V> beginScope() {
		Environment<V> env = new Environment<V>(this);
		return env;
	};

	// - pop top level
	Environment<V> endScope() {
		return e;
	};

	void assoc(String id, V val) {
		map.put(id, val);
	};

	V find(String id) {
		V var = this.map.get(id);
		if (var == null)
			var = this.e.find(id);
		return var;
		
		
	};

}
