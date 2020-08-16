import java.util.ArrayList;
import java.util.List;

class Directory extends Entry {
	private final List<Entry> entries = new ArrayList<>();

	public Directory(Directory parent, String name) {
		super(parent, name);
	}

	@Override
	public int getSize() {
		int size = 0;
		for (Entry entry : entries) {
//			if (entry instanceof com.laioffer.File) {
//				size += ((com.laioffer.File) entry).getSize();
//			} else {
//				size += ((com.laioffer.Directory) entry).getSize();
//			}
			size += entry.getSize();
		}
		return size;
	}

	public List<Entry> getEntries() {
		return entries;
	}

	public List<Entry> getRecursiveEntries() {
		List<Entry> res = new ArrayList<>();
		res.add(this);
		for (Entry e : this.entries) {
			if (e instanceof File) {
				res.add(e);
			} else {
				res.addAll(((Directory) e).getRecursiveEntries());
			}
		}
		return res;
	}

	public void addEntry(Entry entry) {
		entries.add(entry);
		entry.parent = this;
	}

	public void removeEntry(Entry entry) {
		entries.remove(entry);
	}
}
