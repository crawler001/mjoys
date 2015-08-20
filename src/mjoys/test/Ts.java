package mjoys.test;

import java.io.Serializable;

public class Ts implements Serializable {
	private static final long serialVersionUID = 1L;
	public String name;
	public int age;
	
	@Override
	public boolean equals(Object value) {
		Ts t = (Ts) value;
		return this.name.equals(t.name) && this.age == t.age;
	}
}