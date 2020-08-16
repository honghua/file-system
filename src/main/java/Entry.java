import java.time.LocalDateTime;

abstract class Entry {
	String name;
	private final LocalDateTime createdTime;
	LocalDateTime updatedTime;
	Directory parent; // path
	
	public Entry(Directory parent, String name) {
		this.parent = parent;
		this.name = name;
		createdTime = LocalDateTime.now();
		updatedTime = LocalDateTime.now();
	}

	public abstract int getSize();

	public void delete() {// delete self
		if (this.parent == null) {
			return;
		}


		this.parent.removeEntry(this);
		this.parent = null;
	}

	public void changName(String newName){
		this.name = newName;
	}

	public String getName() {
		return name;
	}

	public LocalDateTime getCreateTime() {
		return createdTime;
	}

	public LocalDateTime getUpdatedTime() {
		return updatedTime;
	}

	public String getPath() {
		if (parent == null) {
			return "";
		} else {
			return parent.getPath() + "/" + name;
		}
//		StringBuilder sb = new StringBuilder();
//		Entry entry = this;
//		while (entry != null) {
//			sb.append(entry.name);
//			sb.append("/");
//			entry = entry.parent;
//		}
//		return sb.toString();
	}
}


