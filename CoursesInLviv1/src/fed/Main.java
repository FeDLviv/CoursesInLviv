package fed;

public class Main {

	public static void main(String[] args) {
		User user = new User();
		user.name = "Oleg";
		user.age = 20;
		user.position = "Java Developer";

		User other = new User(user);
		System.out.println(other.equals(user));

		other.age = 12;
		System.out.println(other.equals(user));
	}

}
