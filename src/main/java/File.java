class File extends Entry {
	private static final int DEFAULT_FILE_SIZE = 16;
	private byte[] content;
	private int size;

	public File(Directory parent, String name) {
		super(parent, name);
		content = new byte[DEFAULT_FILE_SIZE];
		size = DEFAULT_FILE_SIZE;
	}

	@Override
	public int getSize() {
		return size;
	}

	public byte[] getContent() {// Read
		return content;
	}

	public void setContent(byte[] newContent, int offset) { // Update
		content = newContent;
	}

}
