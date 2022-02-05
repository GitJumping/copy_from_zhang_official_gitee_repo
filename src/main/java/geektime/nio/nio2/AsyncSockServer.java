package geektime.nio.nio2;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class AsyncSockServer {
	public static void main(String[] args) {
		try (AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open()) {
			
			server.bind(new InetSocketAddress("127.0.0.1", 8234));
			Future<AsynchronousSocketChannel> acceptCon = server.accept();
			
			AsynchronousSocketChannel client = acceptCon.get(10, TimeUnit.SECONDS);
			if ((client != null) && (client.isOpen())) {
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				Future<Integer> readval = client.read(buffer);
				System.out.println("Received from client: " + new String(buffer.array()).trim());
				readval.get();
				
				buffer.flip();
				String str = "I'm fine. Thank you!";
				Future<Integer> writeVal = client.write(ByteBuffer.wrap(str.getBytes()));
				System.out.println("Writing back to client: " + str);
				writeVal.get();
				buffer.clear();
			}
			Thread.sleep(5000);
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}