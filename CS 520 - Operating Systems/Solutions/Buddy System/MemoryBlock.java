package memorymanagement;

public class MemoryBlock {
	private long size;
	private Process process;
	
	private MemoryBlock() {	}
	
	public MemoryBlock(long size) {
		this();
		this.size = size;
	}
	// putting a process into the memory block
	public boolean load(Process process) {
		if (this.process == null) {
			this.process = process;
			return true;
		}
		return false;
	}
	// removing process from memory block
	public boolean unload(Process process) {
		if (this.process != null && this.process.equals(process)) {
			this.process = null;
			return true;
		}
		return false;
	}
	
	public Long getSize() {
		return size;
	}
	
	public Long getUsedSpace() {
		return process.getSize();
	}
	// to know how much space is left in the memory block after the process has occupied the block
	public Long getFreeSpace() {
		return size - (process == null ? 0 : process.getSize());
	}
	// checking if the block can be divided into two equal sizes no process gets broken into two block
	public boolean canBeSubdivided() {
		return getFreeSpace() >= size/2;
	}
	// dividing the current block into two new blocks
	public MemoryBlock getEmptyNewBlock() {
		return new MemoryBlock(size /= 2);
	}
	//printing the output
	@Override
	public String toString() {
		String toString = "[";
		if (process == null) {
			toString += "Empty";
		} else {
			toString += process.toString();
		}
		return toString + "::" + size + "]";
	}
	// to check if two memory blocks are of equal size and empty
	@Override
	public boolean equals(Object obj) {
		return obj instanceof MemoryBlock && size == ((MemoryBlock) obj).size && process == ((MemoryBlock) obj).process;
	}
// If the two blocks are equal by the above method then they merge.
	public void merge(MemoryBlock mBlock) {
		size += mBlock.getSize();
	}
}
