Several projects created to aid the TM-Jug AOP presentation. The projects cover different setup & weaving scenarios.

## btw-demo
A project showing off AOP's Build-Time weaving model. This scenario is mostly for learnign purposes as it requires you to build the source coude using the **ajc** compiler instead of **javac** (makes it harder to addopt into already existing projects). In order for this project to compile you must load it into an Eclipse instance having installed the AspectJ tooling support:

- http://eclipse.org/aspectj/
- http://www.eclipse.org/ajdt/

## ltw-demo
A project showing off AOP's Load-Time weaving model. Models the structure of a real production project having a legacy module which is improved through AOP. The addoption of AOP in this case does not alter in any way the current build process. The only constraint is that at runtime, the **aspectjweaver** JVMTI (Java Virtual Machine Tool Interface) agent has to be used by the JVM.