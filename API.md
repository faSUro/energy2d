# API Presentation

It's possible to remotely connect to Energy2D and control the simulation from a client. All you need to do is connect to Energy2D via UDP (address: localhost*, port: 8888) and send text messages. Below there's a list of supported commands and a brief guide to make them work properly.

## Before you start...

All of the valid commands that work on single elements of the simulation need to uniquely identify those elements. In order to accomplish this result, the UID field is exploited.
Because of the mechanism that translate commands into action, you need to respect a couple of rules:
1. The UID can't contain spaces;
2. The UID must start with a prefix: the element's type. 

For example: a valid UID for a thermometer could be "THERMOMETER100", while "THERMO100" or "THERMOMETER 100" wouldn't be recognised.

Here is a list of all supported type-prefix couples:
- thermometer -> THERMOMETER
- power source -> HEATER
- ...

## Supported commands

- **run**

	run command description
- **runsteps**

  runsteps command description
