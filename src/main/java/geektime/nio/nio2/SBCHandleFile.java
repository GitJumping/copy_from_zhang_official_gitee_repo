package geektime.nio.nio2;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;
import static java.nio.file.StandardOpenOption.*;

public class SBCHandleFile {
	final static int RECLEN = 50;

	public static void main(String[] args) throws IOException {
		String tmpdir = System.getProperty("java.io.tmpdir");
		Path path = Paths.get(tmpdir + File.separatorChar + "sbcfile"); ///
		
		FileChannel fc;
		fc = FileChannel.open(path, CREATE, WRITE, SYNC).position(RECLEN * 2);
		ByteBuffer buffer = ByteBuffer.wrap("Geektime".getBytes());
		fc.write(buffer);
		fc.close();
		buffer.clear();
		
		SeekableByteChannel sbc;
		sbc = Files.newByteChannel(path, EnumSet.of(READ)).position(RECLEN * 2);
		sbc.read(buffer);
		sbc.close();
		System.out.println(new String(buffer.array()));
	}

}
