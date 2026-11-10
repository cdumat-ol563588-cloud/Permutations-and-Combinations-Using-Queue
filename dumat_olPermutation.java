import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class dumat_olPermutation {
    static Scanner input = new Scanner(System.in);
    static Queue<String> queue = new LinkedList<>();
    public static void main(String[] args) {
    	
    int choice;
        do {
            System.out.println("\n===== PERMUTATION AND COMBINATION =====");
            System.out.println("[1] Add Elements to Queue");
            System.out.println("[2] Display Queue");
            System.out.println("[3] Generate Permutations");
            System.out.println("[4] Generate Combinations");
            System.out.println("[5] Exit");
            System.out.print("Enter choice: ");

            choice = input.nextInt();

            switch (choice) {

                case 1:
                    addElements();
                    break;

                case 2:
                    displayQueue();
                    break;

                case 3:
                    generatePermutations();
                    break;

                case 4:
                    generateCombinations();
                    break;

                case 5:
                    System.out.println("Program terminated.");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }

    // TODO 1: Accept elements and add them to the Queue
    static void addElements() {
    	System.out.println("how many elements to add: ");
    	int count = input.nextInt();
    	input.nextLine();
    	
    	for(int i = 1; i <= count; i++) {
    		System.out.println("Enter element " + i + ": ");
    		String element = input.nextLine();
    		queue.add(element);
    	}
    	System.out.println("Element succesfully added");
    }

    // TODO 2: Display all elements in the Queue
    static void displayQueue() {
    	if(queue.isEmpty()) {
    		System.out.println("Queue is empty.");
    	} else {
    		System.out.println("Queue" + queue);
    	}
    }

    // TODO 3: Generate all possible permutations
    static void generatePermutations() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty. Please add some elements first.");
            return;
        }
        String[] pool = queue.toArray(new String[0]);
        System.out.println("Enter number of elements to arrange: ");
        int r = input.nextInt();

        if (r > pool.length || r <= 0) {
            System.out.println("Invalid number of elements to arrange.");
            return;
        }

        Queue<String> permQ = new LinkedList<>();
        for (String item : pool) {
            permQ.add(item);
        }
        int totalPermutation = 0;

        while (!permQ.isEmpty()) {
            String current = permQ.poll();

            if (current.length() == r) {
                System.out.println(current);
                totalPermutation++;
            } else {
                for (String item : pool) {
                    if (!current.contains(item)) {
                        permQ.add(current + item); // Fixed: Added missing queue insertion
                    }
                }
            }
        }
        // Fixed: Moved outside the while loop
        System.out.println("\nTotal Permutation: " + totalPermutation);
    }

    // TODO 4: Generate all possible combinations
    static void generateCombinations() {
        if (queue.isEmpty()) {
            System.out.println("Queue is Empty. Please add some elements");
            return;
        }
        String[] pool = queue.toArray(new String[0]);
        System.out.println("Enter the number of elements to select: ");
        int r = input.nextInt();
        System.out.println();

        if (r > pool.length || r <= 0) {
            System.out.println("Invalid number of elements to select.");
            return;
        }

        Queue<CombState> combQ = new LinkedList<>();
        for (int i = 0; i < pool.length; i++) {
            combQ.add(new CombState(pool[i], i)); // Fixed: Changed pool[1] to pool[i]
        }
        int totalCombination = 0;
        while (!combQ.isEmpty()) {
            CombState current = combQ.poll();

            if (current.path.length() == r) {
                System.out.println(current.path);
                totalCombination++;
            } else {
                for (int i = current.lastIdx + 1; i < pool.length; i++) {
                    combQ.add(new CombState(current.path + pool[i], i));
                }
            }
        }
        System.out.println("\nTotal Combination: " + totalCombination);
    }
static class CombState {
	String path;
	int lastIdx;
	
	CombState(String path, int lastIdx) {
		this.path = path;
		this.lastIdx = lastIdx;
		}
	}
}
