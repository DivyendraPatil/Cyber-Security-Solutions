package memorymanagement;

import java.util.ArrayList;

public class BuddySystem {
	
	private long memory_size;
	// dividing whole memory into buddy system blocks
	private ArrayList<MemoryBlock> memory = new ArrayList<>(); 
	
	
	
	private BuddySystem() {}
	// Allocating memory to the entire system
	public BuddySystem(long memory_size) {
		this();
		this.memory_size = memory_size;
	}

	private void load(Process process) {
		// calculating space required in the buddy system
		long needSpace = (long) Math.pow(2, Math.ceil(Math.log10(process.getSize())/Math.log10(2)));
		//divding the memory in buddy system into blocks
		if (memory.size() == 0) {
			memory.add(new MemoryBlock(needSpace));
			for (long s = needSpace; s < memory_size; s*=2) {
				memory.add(new MemoryBlock(s));
			}
		}
		// process to be loaded into memory block 
		MemoryBlock toBeLoadedInto = null;
		int index = 0; // to identify the position of the memory block in array list
		for (MemoryBlock mBlock : memory) { // to check where the process can fit
			index++;
			if (mBlock.getFreeSpace() >= needSpace && mBlock.canBeSubdivided()) {
				if (mBlock.load(process)) {
					break;
				}
				toBeLoadedInto = mBlock.getEmptyNewBlock();
				toBeLoadedInto.load(process);
				break;
			}
		}
		if(toBeLoadedInto != null) { // loading process into the block decided by above loop
			memory.add(index, toBeLoadedInto);
		}
	}
	// removing the process from the block it has occupied
	private void unload(Process process) {
		for (MemoryBlock mBlock : memory) {  // to find and remove the process from the block it resides in
			if (mBlock.unload(process)){
				break;
			}
		}
		// Merging two equal empty blocks
		while(memory.size() > 1) {
			if (memory.get(0).equals(memory.get(1))) {
				memory.get(1).merge(memory.get(0));
				memory.remove(0);
			} else {
				break;
			}
		}
	}
	// this method is used to interact witht he buddy system
	public void action(Process process, boolean toAddInMemory) {
		if (toAddInMemory) {
			load(process);
		} else {
			unload(process);
		}
		System.out.println(memory);
	}
}