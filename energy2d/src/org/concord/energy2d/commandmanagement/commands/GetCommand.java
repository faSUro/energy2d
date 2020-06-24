package org.concord.energy2d.commandmanagement.commands;

import org.concord.energy2d.commandmanagement.commands.exception.CommandFormatException;
import org.concord.energy2d.commandmanagement.commands.exception.InexistentElementException;
import org.concord.energy2d.commandmanagement.commands.exception.InexistentPropertyException;
import org.concord.energy2d.model.Model2D;
import org.concord.energy2d.model.Thermometer;
import org.concord.energy2d.model.TimedData;
import org.concord.energy2d.system.System2D;

import java.util.List;

public class GetCommand implements ICommand {

    @Override
    public String execute(System2D s2d, String args) {
        Model2D model = s2d.getModel();
        try {
            String[] split = args.split("\\.");
            if (split.length != 2)
                throw new CommandFormatException();
            String element = split[0];
            String property = split[1];

            if (element.startsWith("THERMOMETER")) {
                Thermometer thermometer = model.getThermometer(element);
                if (thermometer == null)
                    throw new InexistentElementException();
                return getTemperature(thermometer, property);
            }
            else if (element.equalsIgnoreCase("allthermometers")) {
                List<Thermometer> thermometerList = model.getThermometers();
                return getAllThermometersTemperature(thermometerList, property);
            }
            else
                throw new InexistentElementException();
        } catch (CommandFormatException e) {
            return "It was impossible to execute command \"get\". Use this format: get {element}.{property}";
        } catch (InexistentElementException e) {
            return "Element not found.";
        }
    }

    private String getAllThermometersTemperature(List<Thermometer> thermometerList, String property) {
        StringBuilder dataList = new StringBuilder();
        try {
            if (property.equalsIgnoreCase("temperature")) {//returns the current temperature of all thermometer
                for (Thermometer t : thermometerList) {
                    dataList.append(t.getUid());
                    dataList.append(" ");
                    dataList.append(t.getCurrentData());
                    dataList.append("   ");
                }
            }
            else if (property.equalsIgnoreCase("temperature_history")) { //returns the complete temperature history of all thermometer
                for (Thermometer t : thermometerList) {
                    dataList.append(t.getUid());
                    dataList.append(" ");
                    for (TimedData td : t.getData()) {
                        dataList.append(td.getTime());
                        dataList.append("/");
                        dataList.append(td.getValue());
                        dataList.append(" ");
                    }
                    dataList.append("   ");
                }

            }
            else
                throw new InexistentPropertyException();
            return dataList.toString();
        } catch (InexistentPropertyException e) {
            return "The selected element doesn't have the indicated property.";
        }
    }

    private String getTemperature(Thermometer thermometer, String property) {
        try {
            if (property.equalsIgnoreCase("temperature"))
                return String.valueOf(thermometer.getCurrentData()); //returns the current temperature of the thermometer only
            else if (property.equalsIgnoreCase("temperature_history")) {
                StringBuilder dataList = new StringBuilder(thermometer.getUid());
                dataList.append(" ");
                for (TimedData td : thermometer.getData()) {
                    dataList.append(td.getTime());
                    dataList.append("/");
                    dataList.append(td.getValue());
                    dataList.append(" ");
                }
                return dataList.toString(); //returns the complete temperature history of the thermometer
            }
            else
                throw new InexistentPropertyException();
        } catch (InexistentPropertyException e) {
            return "The selected element doesn't have the indicated property.";
        }
    }

}
