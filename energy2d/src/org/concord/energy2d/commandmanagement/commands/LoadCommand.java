package org.concord.energy2d.commandmanagement.commands;

import org.concord.energy2d.system.System2D;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoadCommand implements ICommand {

    @Override
    public String execute(System2D s2d, String args) {
        try {
            File newFile = new File(s2d.TEMP_FILES_FOLDER + generateUniqueName()); //creates the file
            FileWriter fileWriter = new FileWriter(newFile);
            String[] split = args.split("\n");
            for (String s : split) { //populates the file
                fileWriter.write(s);
            }
            fileWriter.close();
            s2d.loadFile(newFile); //loads the new file into Energy2D
        } catch (Exception e) {
            return "There was a problem loading the file.";
        }
        return "File correctly loaded";
    }

    private String generateUniqueName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date()) + ".e2d";
    }

}
