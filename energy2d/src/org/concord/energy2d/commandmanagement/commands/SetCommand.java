package org.concord.energy2d.commandmanagement.commands;

import org.concord.energy2d.commandmanagement.commands.exception.CommandFormatException;
import org.concord.energy2d.commandmanagement.commands.exception.InexistentElementException;
import org.concord.energy2d.commandmanagement.commands.exception.InexistentPropertyException;
import org.concord.energy2d.model.Model2D;
import org.concord.energy2d.model.Part;
import org.concord.energy2d.model.Thermometer;
import org.concord.energy2d.model.Thermostat;
import org.concord.energy2d.system.System2D;

public class SetCommand implements ICommand {

    private Model2D model;

    @Override
    public String execute(System2D s2d, String args) {
        model = s2d.getModel();
        try {
            String[] splitArgs = args.split(" ");
            if (splitArgs.length != 2)
                throw new CommandFormatException();

            if (splitArgs[0].equalsIgnoreCase("timestep")) {
                model.setTimeStep(Float.parseFloat(splitArgs[1]));
                return "New timestep set: " + splitArgs[1];
            }

            String[] split = splitArgs[0].split("\\.");
            if (split.length == 2) {
                String element = split[0];
                String property = split[1];
                if (element.startsWith("THERMOMETER")) {
                    Thermometer thermometer = model.getThermometer(element);
                    if (thermometer == null)
                        throw new InexistentElementException();
                    return setThermometerProperty(thermometer, property, splitArgs[1]);
                } else if (element.startsWith("HEATER")) {
                    Part heater = model.getPart(element);
                    if (heater == null)
                        throw new InexistentElementException();
                    return setHeaterProperty(heater, property, splitArgs[1]);
                } else
                    throw new InexistentElementException();
            }
            else
                throw new CommandFormatException();
        } catch (InexistentElementException e) {
            return "Element not found.";
        } catch (CommandFormatException e) {
            return "It was impossible to execute command \"set\". Use this format: set [{element}.]{property} {parameter}";
        }
    }

    private String setThermometerProperty(Thermometer thermometer, String property, String parameter) {
        try {
            if (property.equalsIgnoreCase("setpoint")) {
                String thermometerName = thermometer.getUid();
                String elementNumber = thermometerName.substring(11); //finds the thermometer's number (the last 2 characters)
                Part part = model.getPart("HEATER" + elementNumber); //uses the thermometer's number to find the connected heater
                Thermostat thermostat = model.getThermostat(thermometer, part); //finds the right thermostat
                thermostat.setSetPoint(Float.parseFloat(parameter));
                return thermometerName + " new setpoint: " + parameter;
            }
            else
                throw new InexistentPropertyException();
        } catch (InexistentPropertyException e) {
            return "The selected element doesn't have the indicated property.";
        } catch (NumberFormatException e) {
            return "Invalid argument.";
        }
    }

    private String setHeaterProperty(Part heater, String property, String parameter) {
        try {
            if (property.equalsIgnoreCase("power")) {
                heater.setPower(Float.parseFloat(parameter));
                return heater.getUid() + " new power density: " + parameter;
            }
            else
                throw new InexistentPropertyException();
        } catch (InexistentPropertyException e) {
            return "The selected element doesn't have the indicated property.";
        } catch (NumberFormatException e) {
            return "Invalid argument.";
        }
    }

}
