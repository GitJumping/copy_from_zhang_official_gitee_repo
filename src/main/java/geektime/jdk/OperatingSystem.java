package geektime.jdk;

import java.lang.management.*;

public class OperatingSystem {
  public static void main(String[] args) {
    OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
    System.out.println("System Architecture: " + os.getArch());
    System.out.println("Operating System: " + os.getName());
    System.out.println("Version: " + os.getVersion());
    
    MemoryMXBean m = ManagementFactory.getMemoryMXBean();
    System.out.println("Heap Memory: " + m.getHeapMemoryUsage());
  }
}
