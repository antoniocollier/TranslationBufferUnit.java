

import java.util.*;

public class TranslationBufferUnit {

    // Constants
    private static final int TLB_SIZE = 4; // Size of the TLB
    private static final int PAGE_TABLE_SIZE = 16; // Total number of pages

    // Data structures
    private Map<Integer, Integer> pageTable; // Virtual-to-Physical mapping
    private LinkedHashMap<Integer, Integer> tlb; // TLB with LRU

    // Metrics
    private int tlbHits = 0;
    private int tlbMisses = 0;
    private int pageFaults = 0;

    public TranslationBufferUnit() {
        // Initialize page table with example mappings
        pageTable = new HashMap<>();
        for (int i = 0; i < PAGE_TABLE_SIZE; i++) {
            pageTable.put(i, i + 100); // Virtual page i -> physical page i+100
        }

        // Initialize TLB with LRU policy
        tlb = new LinkedHashMap<>(TLB_SIZE, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > TLB_SIZE;
            }
        };
    }

    public Integer translateAddress(int virtualAddress) {
        int virtualPage = virtualAddress / 10; // Page size = 10
        int offset = virtualAddress % 10;

        // Check TLB
        if (tlb.containsKey(virtualPage)) {
            tlbHits++;
            System.out.println("TLB Hit: Virtual Page " + virtualPage);
            return tlb.get(virtualPage) * 10 + offset;
        }

        // TLB Miss: Check Page Table
        tlbMisses++;
        if (pageTable.containsKey(virtualPage)) {
            System.out.println("TLB Miss, Page Table Hit: Virtual Page " + virtualPage);
            int physicalPage = pageTable.get(virtualPage);
            tlb.put(virtualPage, physicalPage); // Update TLB
            return physicalPage * 10 + offset;
        }

        // Page Fault
        pageFaults++;
        System.out.println("Page Fault: Virtual Page " + virtualPage);
        return null;
    }

    public void printTLB() {
        System.out.println("Current TLB:");
        for (Map.Entry<Integer, Integer> entry : tlb.entrySet()) {
            System.out.println("Virtual Page " + entry.getKey() + " -> Physical Page " + entry.getValue());
        }
    }

    public void printStatistics() {
        System.out.println("\n--- Simulation Statistics ---");
        System.out.println("TLB Hits: " + tlbHits);
        System.out.println("TLB Misses: " + tlbMisses);
        System.out.println("Page Faults: " + pageFaults);
        double hitRatio = (double) tlbHits / (tlbHits + tlbMisses) * 100;
        System.out.printf("TLB Hit Ratio: %.2f%%\n", hitRatio);
    }

    public static void main(String[] args) {
        TranslationBufferUnit tbu = new TranslationBufferUnit();

        // Simulated Virtual Addresses
        int[] virtualAddresses = {12, 22, 12, 32, 42, 52, 62, 12, 72, 82, 92, 102, 32, 42};

        System.out.println("--- Starting Address Translation Simulation ---\n");
        for (int address : virtualAddresses) {
            System.out.println("Translating Virtual Address: " + address);
            Integer physicalAddress = tbu.translateAddress(address);
            if (physicalAddress != null) {
                System.out.println("Physical Address: " + physicalAddress);
            } else {
                System.out.println("Address could not be translated due to Page Fault.");
            }
            tbu.printTLB();
            System.out.println();
        }

        // Print Final Statistics
        tbu.printStatistics();
    }
}
