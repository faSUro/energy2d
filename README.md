# How to run Energy2D from scratch (UNIX only)

## Prerequisites

- Having a clone of the Energy2D repository.

- Having Apache Ant installed (command: sudo apt-get install ant).

- Having the latest Java 8 release (jdk1.8.0_261) installed inside the directory "/usr/lib/jvm/". In case you have a 
  different release (it must be Java 8 anyway) or a different directory for the JDK, you will need to change the path
  at line 15 of the "energy2d/build.xml" file.
  
## Create and run energy2d.jar from command line

1. Get inside "energy2d" directory

2. Execute command: ant compile

3. Execute command: ant jar

4. Execute command: java -jar exe/energy2d.jar

**WARNING: do not try to move the jar or Energy2D won't start!**
