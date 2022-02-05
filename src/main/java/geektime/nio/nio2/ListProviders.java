package geektime.nio.nio2;

import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileSystemProvider;
import java.util.List;

public class ListProviders {
	public static void main(String[] args) throws Exception {
		List<FileSystemProvider> providers = FileSystemProvider.installedProviders();
		for (FileSystemProvider provider : providers) {
			System.out.println(provider);
		}
		
		FileSystem fsDefault = FileSystems.getDefault();

		for (FileStore fs : fsDefault.getFileStores()) {
			System.out.println("FileStore name: " + fs.name() + " Total space: " + fs.getTotalSpace());
		}
		
        Path path = Paths.get("/");  // 需要修改为可用的路径
		FileStore fs = Files.getFileStore(path);
		System.out.println("\nFileStore Name: " + fs.name());
		long totalBytes = fs.getTotalSpace();
		long unallocBytes = fs.getUnallocatedSpace();
		long usableBytes = fs.getUsableSpace();
		long sizeInGB = 1024 * 1024 * 1024;
		System.out.println("Total Space : " + totalBytes / sizeInGB + "GB");
		System.out.println("Unallocated Space : " + unallocBytes / sizeInGB + "GB");
		System.out.println("Useable Space : " + usableBytes / sizeInGB + "GB");
	}
}
