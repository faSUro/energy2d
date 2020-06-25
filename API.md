# API Presentation

It's possible to remotely connect to Energy2D and control the simulation from a detached client. All you need to do is connect via UDP (address: localhost*, port: 8888) and send text messages. Below there's a list of supported commands and a brief guide to make them work properly.

## Before you start...

All valid commands that work on single elements of the simulation need to uniquely identify those elements. In order to accomplish this result, the UID field is exploited.
Because of the mechanism that translate commands into action, you need to respect a couple of rules:
1. The UID can't contain spaces;
2. The UID of all elements must start with a prefix corresponding to the element's type. 

E.g.: a valid UID for a thermometer could be "THERMOMETER100", while "THERMO100" or "THERMOMETER 100" wouldn't be recognised.

### List of all supported type-prefix couples:
- thermometer -> THERMOMETER
- power source -> HEATER
- ...

## Supported commands

- **load {file_content}**

It loads the given file into Energy2D. The argument has to be the content of a file with extension .e2d.

- **run [{time}]**

Simply starts the simulation and returns the message "Simulation running...". 

In case the time option is used (time must be a float or an int, representing the duration of the simulation in seconds), Energy2D will send a message like "The simulation ran for *X* seconds" when the simulation will be done, where *X* is the argument of the run command.

- **runsteps {steps}**

Makes the simulation run for the given number of steps (it must be an int). Returns a message like "Simulation running *X* steps.", where *X* is the argument of the runsteps command.

- **stop**

Stops the simulation, returning the message "Simulation stopped.".

- **reset**

Resets the simulation, returning the message "Simulation reset.".
