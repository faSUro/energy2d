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

  In case the time option is used (time must be a float or an int, representing the duration of the simulation in seconds), Energy2D will send a message like "The simulation ran for  *X* seconds" when the simulation will be done, where *X* is the argument of the run command.

- **runsteps {steps}**

  Makes the simulation run for the given number of steps (it must be an int). Returns a message like "Simulation ran *X* steps."  when the simulation will be done, where *X* is the argument of the runsteps command.

- **stop**

  Stops the simulation, returning the message "Simulation stopped.".

- **reset**

  Resets the simulation, returning the message "Simulation reset.".

- **get {element}.{property}**

  Returns the property value of the selected element. 
  The element is indicated by its UID, with the possibility to use a keyword that refers to all the elements of a given type.
  The property must be one possessed by the indicated element.
  
  E.g.: 
  
    "get THERMOMETER100.temperature" returns the current temperature of THERMOMETER100.
  
    "get allthermometers.temperature_history" returns the temperature history (time + temperature) of all thermometers.
  
- **set [{element}.]{property} {new_value}**

  Allows to modify the value of a property.
  
  If the command uses the format "set {property} {new_value}", it refers to global properties of the simulation, such as the steplength.
  The command also permits the format "set {element}.{property} {new_value}", in order to change the property value of a specific element (same rules as get command).
  Of course, the new_value argument must be consistent with the indicated property.
  
  Anyhow, the command will return a message to confirm the operation result.
  
  E.g.: 
  
    "set steplength 50" sets the steplength to 50.
  
    "set HEATER20.power 200" sets the power density of HEATER20 to 200.
  
## Supported elements (with keywords) and properties
  
- Element: Thermometer. Keyword: allthermometers. Properties: temperature, temperature_history.
- Element: Power source. Keyword: /. Properties:  power.
- ...

## Global properties

- steplength
- ...
  
  
  
  
  
  
  
  
