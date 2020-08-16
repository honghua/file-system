import java.util.ArrayList;
import java.util.List;

class FileSystem {
	public static void main(String[] args) {
		System.out.println("hello world");
	}
	private static final String ROOT_PATH_NAME = "/";
	private static final String SPLITTER = "/";
	private Directory root;

	public FileSystem() {
		root = new Directory(null, ROOT_PATH_NAME);
	}

	// 1.create
	// mkdir
	// touch xx.txt
	public boolean createFile(String dirPath, String name) {
		Entry entry = resolve(dirPath);
		if (!(entry instanceof Directory)) {
			throw new IllegalArgumentException();
		}
		Directory parent = (Directory) entry;
		File file = new File(parent, name);
		parent.getEntries().add(file);
		return true;
	}

	public boolean mkdir(String dirPath, String name) {
		Entry entry = resolve(dirPath);
		if (!(entry instanceof Directory)) {
			throw new IllegalArgumentException();
		}
		Directory parent = (Directory) entry;
		Directory dir = new Directory(parent, name);
		parent.getEntries().add(dir);
		return true;
	}

	// 2. read -- Directory vs. File
	public List<Entry> ls(String dirPath) { // Directory
		Entry entry = resolve(dirPath);
		if (! (entry instanceof Directory)) {
			throw new IllegalArgumentException();
		}
		return ((Directory) entry).getEntries();
	}

	public List<Entry> lsR(String dirPath) {
		Entry entry = resolve(dirPath);
		if (! (entry instanceof Directory)) {
			throw new IllegalArgumentException();
		}

		return ((Directory) entry).getRecursiveEntries();
	}


	public byte[] open(String filePath) {
		Entry entry = resolve(filePath);
		if (!(entry instanceof File)) {
			throw new IllegalArgumentException("provide file path");
		}
		File file = (File) entry;
		return file.getContent();
	}

	// 3. update

	public boolean mvTo(String path, String dirPath) {
		if (path.equals(ROOT_PATH_NAME)) return false;

		Entry entry = resolve(path);
		delete(path);

		Entry parent = resolve(dirPath);
		if (! (parent instanceof Directory)) {
			throw new IllegalArgumentException();
		}
		Directory dir = (Directory) parent;
		dir.addEntry(entry);
		return true;
	}

	public boolean append(String filename, byte[] newContent) {

//		com.laioffer.File file = ;
//		file.setContent(newContent);
		return true;
	}

	// 4. delete
	public boolean delete(String path) {
		if (path.equals(ROOT_PATH_NAME)) {
			throw new IllegalArgumentException("You do NOT have privilege to delete root dir!");
		}

		Entry entry = resolve(path);
		if (entry == null) return false;

		entry.delete();
		return true;
	}


	private Entry resolve(@org.jetbrains.annotations.NotNull String path) {
		// throw Exception
		assert path.startsWith(ROOT_PATH_NAME) : "Path start with '/' ";
		if (path.equals(ROOT_PATH_NAME)) return root;

//		path -> string[]
//		“/Users/meng/leetcode/2sum.txt”
//		[Users, meng, leetcode, 2sum]
		String[] array = path.substring(1).split(SPLITTER);

		Entry entry = root;
		for (String s : array) {
			boolean found = false;
			if (entry != null && entry instanceof Directory) {
				for (Entry e : ((Directory) entry).getEntries()) {
					if (e.getName().equals(s)) {
						entry = e;
						found = true;
					}
				}
			}
			if (!found) {
				throw new IllegalArgumentException("Invalid Path");
			}
		}
		return entry;
	}
}
