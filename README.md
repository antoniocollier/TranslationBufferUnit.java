# TranslationBufferUnit.java
Computer Architecture Project.
This is exactly how I was able to run the files on my computer.

README: Translation Buffer Unit Simulation
Project Overview
This Java program simulates a Translation Buffer Unit (TBU), demonstrating the principles of memory address translation and exploring optimizations for efficiency. It models:

Translation Lookaside Buffer (TLB): A small cache for frequently accessed virtual-to-physical address mappings.
Page Table: A data structure for translating virtual pages to physical memory addresses.
The simulation tracks TLB hits, misses, and page faults, and implements an LRU (Least Recently Used) replacement policy for the TLB.
----------------------------------------------------------------------------------
How to Run
Requirements:

Java Development Kit (JDK) installed on your system.
A terminal or command prompt to compile and run the program.
-----------------------------------------------------------------------------------
Steps:

Save the TranslationBufferUnit.java file in your desired directory.

Open a terminal or command prompt and navigate to the directory:

bash
Copy code
cd path_to_directory
Compile the program:

bash
Copy code
javac TranslationBufferUnit.java
This will generate a TranslationBufferUnit.class file in the same directory.
--------------------------------------------------------------------------------
Run the program:

bash
Copy code
java TranslationBufferUnit
Program Output
The program simulates the translation of virtual addresses and provides:

Detailed logs of TLB hits, TLB misses, and page faults.
Updates to the TLB state after each translation.
Summary statistics:
Number of TLB Hits.
Number of TLB Misses.
Number of Page Faults.
TLB Hit Ratio (percentage).
--------------------------------------------------------------------------------
Example Output
plaintext
Copy code
--- Starting Address Translation Simulation ---

Translating Virtual Address: 12
TLB Miss, Page Table Hit: Virtual Page 1
Physical Address: 1012
Current TLB:
Virtual Page 1 -> Physical Page 101

...

--- Simulation Statistics ---
TLB Hits: 1
TLB Misses: 13
Page Faults: 0
TLB Hit Ratio: 7.14%
Contact
If you encounter issues running the program or have questions, feel free to reach out.
