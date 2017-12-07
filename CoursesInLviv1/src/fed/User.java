package fed;

public class User {

	// додав би гетери та сетери, але в ТЗ не було :)
	String name;
	int age;
	String position;

	public User() {

	}

	public User(User x) {
		this.name = x.name;
		this.age = x.age;
		this.position = x.position;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		} else {
			User temp = (User) obj;
			if (this.name.equals(temp.name) && this.age == temp.age && this.position.equals(temp.position)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public String toString() {
		return name + " " + age + " " + position;
	}

	public int hashCode() {
		return this.toString().hashCode();
	}
}
