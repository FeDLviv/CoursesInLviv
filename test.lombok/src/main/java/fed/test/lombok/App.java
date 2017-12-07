package fed.test.lombok;

public class App {
	public static void main(String[] args) {
		Auto x = new Auto("Audi", 200, 1.45);
		x.setSpeed(220);
		System.out.println(x);
	}
}
