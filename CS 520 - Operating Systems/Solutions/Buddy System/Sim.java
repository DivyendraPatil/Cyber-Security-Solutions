package memorymanagement;

public class Sim {
	
	public static long K = 1024;
	public static long M = 1024 * 1024;

	public static void main(String[] args) {
		BuddySystem bSystem = new BuddySystem(2*M); //2 Megabytes
		//  Process are initialized with respective sizes given in the question
		Process processA = new Process("A", 20*K);
		Process processB = new Process("B", 35*K);
		Process processC = new Process("C", 90*K);
		Process processD = new Process("D", 40*K);
		Process processE = new Process("E", 240*K);
		// Order of process being inserted (True for +) in memory and removed out as per Buddy System (False for -)
		bSystem.action(processA, true);
		bSystem.action(processB, true);
		bSystem.action(processC, true);
		bSystem.action(processD, true);
		bSystem.action(processE, true);
		bSystem.action(processD, false);
		bSystem.action(processA, false);
		bSystem.action(processC, false);
		bSystem.action(processB, false);
		bSystem.action(processE, false);
	}
}
