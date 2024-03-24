import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Disk {

	public static int blockSize = 256;
	static List<Block> dataBlocks = new ArrayList<>();
	public static int diskSize;
	public static byte data[];
	public static byte blockNumberr = 0;

	public static void createFS(int noOfBlocks) {
		diskSize = (noOfBlocks > 0) ? noOfBlocks : 1;
		data = new byte[diskSize * blockSize];
		try {
			try {
				File myObj = new File("DISK");
				if (myObj.createNewFile()) {
					System.out.println("File created: " + myObj.getName());
				} else {
					System.out.println("File already exists.");
				}
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
			FileInputStream ifstream = new FileInputStream("DISK");
			int readableSize = (ifstream.available() < data.length) ? ifstream.available() : data.length;
			System.out.println("Redable size: " + readableSize);
			ifstream.read(data, 0, readableSize);
			ifstream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void saveFs(String fileName) {
		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {
			fis = new FileInputStream("DISK");
			fos = new FileOutputStream(fileName);

			byte[] buffer = new byte[256];
			int noOfBytes = 0;
			while ((noOfBytes = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, noOfBytes);
			}
			fis.close();
			fos.close();
		} catch (Exception e) {
			System.out.println("File not found" + e);
		}
	}

	public static void openFs(String fileName) {
		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream("DISK");
			fis = new FileInputStream(fileName );
			byte[] buffer = new byte[256];
			int noOfBytes = 0;
			while ((noOfBytes = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, noOfBytes);
			}
			fis.close();
			fos.close();
		} catch (Exception e) {
			System.out.println("File not found" + e);
		}
	}

	public static void freeTheDiskBlock(int blockNumber) {
		if(dataBlocks.size()>=blockNumber) {
			dataBlocks.remove(blockNumber);
		}
	}

	public static void writeToDisk(byte[] data2, int blockNumber) {
		Block block = new Block();
		block.length = data2.length;
		block.data = data2;
		dataBlocks.add(block);
		try (FileOutputStream fos = new FileOutputStream("DISK")) {
			for (int i = 0; i < dataBlocks.size(); i++) {
				fos.write(dataBlocks.get(i).data);
			}

			System.out.println("Successfully written data to the file");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
