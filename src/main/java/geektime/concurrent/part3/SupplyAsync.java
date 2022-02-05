package geektime.concurrent.part3;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SupplyAsync {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> getDataById(10))
				.thenApply(data -> sendData(data));

		cf.get();
	}

	private static String getDataById(int id) {
		System.out.println("getDataById: " + Thread.currentThread().getName());
		return "Data:" + id;
	}

	private static String sendData(String data) {
		System.out.println("sendData: " + Thread.currentThread().getName());
		System.out.println(data);
		return data;
	}
}
