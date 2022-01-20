package client;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class Client {

	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;

	public static void main(String[] args) {
		new Client().setUpNetwork();
	}

	public void setUpNetwork() {
		try {
			socket = new Socket("localhost", 5000);
			inputStream = new DataInputStream(socket.getInputStream());
			outputStream = new DataOutputStream(socket.getOutputStream());
			System.out.println("networking established");
			sendArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendArray() throws IOException {
		int[] array = new int[1000000];
		var random = new Random();
		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(1000);
			outputStream.writeInt(array[i]);
		}

		readResult();
	}

	private void readResult() throws IOException {
		if (inputStream.available() > 0) {
			for (int i = 0; i < 10; i++) {
				System.out.println(inputStream.readInt());
			}
		}
	}

}