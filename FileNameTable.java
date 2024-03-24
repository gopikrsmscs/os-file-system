import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class FileNameTable {

	static String[] fileNames;
	static Inode[] inodes;

	static HashMap<String, Inode> fileNameToINodeMap;

	public static void formatFs(int filesLength, int numberOfInodes) {
		fileNameToINodeMap = new HashMap<String, Inode>();
		fileNames = new String[filesLength];
		inodes = new Inode[numberOfInodes];

	}

	public static String[] getFileNames() {
		return fileNames;
	}

	public static Inode[] getInodes() {

		return inodes;
	}

	public static HashMap<String, Inode> getFileNameToInodeMap() {

		return fileNameToINodeMap;
	}

	public static void removeFile(String removeFile) {
		Inode inode = fileNameToINodeMap.get(removeFile);
		// Free the block of the disk
		Disk.freeTheDiskBlock(inode.getBlockNumber());
		// Remove the file
		fileNameToINodeMap.remove(removeFile);

	}

	public static void renameFile(String oldName, String oldName2) {
		Inode inode = fileNameToINodeMap.get(oldName);
		// Remove the file
		fileNameToINodeMap.remove(oldName);
		fileNameToINodeMap.put(oldName2, inode);

	}

	public static void putExternalFile(String externalFilePath) {
		File file = new File(externalFilePath);

		String fileName = file.getName();
		System.out.println(fileName);
		try {
			FileInputStream fl = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fl.read(data);
			fl.close();
			int fileLength = data.length;
			Inode inode = new Inode();
			inode.setDate(new Date());
			inode.setUserName("SYSTEM");
			inode.setFileSize(fileLength);
			inode.setFileSize(fileLength);
			inode.setBlockNumber(fileNameToINodeMap.size() + 1);
			fileNameToINodeMap.put(fileName, inode);
			Disk.writeToDisk(data, fileNameToINodeMap.size() + 1);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void changeTheUserName(String userFileName, String userName) {
		Inode inode = fileNameToINodeMap.get(userFileName);
		System.out.println("Current User Name for the File: " + userFileName);
		inode.setUserName(userName);
		fileNameToINodeMap.put(userFileName, inode);

	}

}
