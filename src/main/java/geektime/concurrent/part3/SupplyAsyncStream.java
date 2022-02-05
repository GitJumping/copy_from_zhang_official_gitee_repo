package geektime.concurrent.part3;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SupplyAsyncStream {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(10, 20, 30);

		long count = list.stream().map(n -> CompletableFuture.supplyAsync(() -> getDataById(n)))
				.map(cf -> cf.thenApply(data -> sendData(data))).map(t -> t.join()).count();

		System.out.println("Number of elements:" + count);
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