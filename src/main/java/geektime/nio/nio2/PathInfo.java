package geektime.nio.nio2;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class PathInfo {
	public static void main(String[] args) {
		FileSystem fsDefault = FileSystems.getDefault();
		Path path = fsDefault.getPath("a", "b", "c", "d");
		System.out.println(path);
		System.out.printf("File name: %s%n", path.getFileName());
		for (int i = 0; i < path.getNameCount(); i++)
			System.out.println(path.getName(i));
		System.out.printf("Parent: %s%n", path.getParent());
		System.out.printf("Root: %s%n", path.getRoot());
		System.out.printf("SubPath [0, 2): %s%n", path.subpath(0, 2));
		System.out.println();
		
		Path normalPath = fsDefault.getPath("a", "./b/c/d/../../e");
		System.out.printf("Normaled file name: %s%n", normalPath.normalize());
		
		Path relativizedPath  = normalPath.normalize().relativize(path.getParent());
		System.out.printf("Relativized file name: %s%n", relativizedPath);
	}
}