import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileSystem {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {

			System.out.println("**************File Menu Operations***********");
			System.out.println("1.Createfs");
			System.out.println("2.Formatfs");
			System.out.println("3.Savefs");
			System.out.println("4.Openfs");
			System.out.println("5.List");
			System.out.println("6.Remove");
			System.out.println("7.Rename");
			System.out.println("8.Put External File");
			System.out.println("9.Get External File");
			System.out.println("10.Change username");
			System.out.println("11.Link/Un Link");
			System.out.println("*********************************************");
			System.out.println("chose an Option to Perform: Enter Integer from above menu.");

			Integer option = sc.nextInt();
			switch (option) {
			case 1:
				System.out.println("*****Performing Create FS");
				System.out.println("Enter the number of blocks.");
				Integer numbeOfBlocks = sc.nextInt();
				Disk.createFS(numbeOfBlocks);
				break;

			case 2:
				System.out.println("*****Performing Format FS");
				System.out.println("Enter the max number of Files.");
				Integer numebrOFfiles = sc.nextInt();
				System.out.println("Enter the max number of DBP entries.");
				Integer numberOfInodes = sc.nextInt();
				FileNameTable.formatFs(numebrOFfiles, numberOfInodes);
				break;

			case 3:
				System.out.println("*****Performing Save FS");
				System.out.println("Enter the file name to save to");
				String fileNmae = sc.next();
				Disk.saveFs(fileNmae);
				break;

			case 4:
				System.out.println("*****Performing Open FS");
				System.out.println("Enter the file name to open from");
				String fileName = sc.next();
				Disk.openFs(fileName);
				break;

			case 5:
				System.out.println("*****Performing Listing Operation");
				HashMap<String, Inode> fileNameToInodeMap = FileNameTable.getFileNameToInodeMap();
				printTheListing(fileNameToInodeMap);
				break;

			case 6:
				System.out.println("*****Performing Remove Option");
				System.out.println("Enter the file name to remove");
				String removeFile = sc.next();
				FileNameTable.removeFile(removeFile);
				break;

			case 7:
				System.out.println("*****Performing Rename Option");
				System.out.println("Enter the file Old Name");
				String oldName = sc.next();
				System.out.println("Enter the file New Name");
				String newName = sc.next();
				FileNameTable.renameFile(oldName, newName);
				break;

			case 8:
				System.out.println("*****Performing Put External FileName");
				System.out.println("Enter the extenral file Path");
				String externalFilePath = sc.next();
				FileNameTable.putExternalFile(externalFilePath);
				break;

			case 9:
				System.out.println("*****Performing Get External FileName");
				System.out.println("Enter the Get extenral file Path");
				String externalGetFilePath = sc.next();
				FileNameTable.putExternalFile(externalGetFilePath);
				break;

			case 10:
				System.out.println("*****Performing User Name Change");
				System.out.println("Enter the File Name");
				String userFileName = sc.next();
				System.out.println("Enter the new user name");
				String userName = sc.next();
				FileNameTable.changeTheUserName(userFileName, userName);
				break;

			case 11:

				break;

			}
		}

	}

	private static void printTheListing(HashMap<String, Inode> fileNameToInodeMap) {
		for (Map.Entry mapElement : fileNameToInodeMap.entrySet()) {
			String key = (String) mapElement.getKey();
			Inode inode = (Inode) mapElement.getValue();
			System.out.println("The Directory Contents are: ");
			System.out.println("File Name: " + key);
			System.out.println("File Size: " + inode.getFileSize());
			System.out.println("File Owner: " + inode.getUserName());
			System.out.println("Disk Block Number: " + inode.getBlockNumber());
		}
	}
}
