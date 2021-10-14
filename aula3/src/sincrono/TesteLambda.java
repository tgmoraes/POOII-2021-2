package sincrono;


public class TesteLambda {
	public static void main(String[] args) {

		// OUTRO EXEMPLO:
		// FORMA ANTIGA
		Thread t;
		t = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("ola");
			}
		});
		t.run();
		// com lambdas
		Thread t2 = new Thread(() -> System.out.println("ola"));

		t2.run();
	}
}
