package Core;

public class CoreFunctions {
	public static void main(String args[]) {

	}

	public static void timedprint(String message, long miliseconds, boolean continuestatement)
			throws InterruptedException {

		char[] chars = message.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			System.out.print(chars[i]);
			Thread.sleep(miliseconds);
		}

		if (continuestatement == true) {
			@SuppressWarnings("unused")
			char cancontinu = IO.inputChar("");

		} else {
			System.out.println("");

		}
		return;
	}

}
