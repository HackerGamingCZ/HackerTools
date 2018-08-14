package cz.HackerGamingCZ.HackerTools.updater;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Updater {

    private File updaterFolder;

    public Updater() {
        updaterFolder = new File(HackerTools.getPlugin().getDataFolder(), "updater");
        if (!updaterFolder.exists()) {
            if (updaterFolder.mkdir()) {
                HackerTools.getPlugin().getLoggerManager().log("Folder updater created!");
            } else {
                HackerTools.getPlugin().getLoggerManager().warn("Folder updater could not be created!");
            }
        }
    }

    public boolean update(UpdaterPlugin type) {
        HackerTools.getPlugin().getLoggerManager().log("Trying to upload new version of " + type.getSource() + "...");
        File destinationFolder = new File(HackerTools.getPlugin().getServer().getWorldContainer(), "plugins");
        File sourceFile = new File(updaterFolder, type.getSource());
        if (sourceFile == null || !sourceFile.exists()) {
            return false;
        }
        File usingFile = new File(destinationFolder, type.getSource());
        if (usingFile == null || !usingFile.exists()) {
            return false;
        }
        String usingVersion = Hash.SHA256.checksumToHex(usingFile);
        String sourceVersion = Hash.SHA256.checksumToHex(sourceFile);
        if (usingVersion.equals(sourceVersion)) {
            return false;
        }
        try {
            Files.deleteIfExists(usingFile.toPath());
        } catch (IOException e) {
            HackerTools.getPlugin().getLoggerManager().log("Error while trying to upload new version of " + type.getSource() + "(" + e.getMessage() + ")");
            return false;
        }
        try {
            FileUtils.copyFileToDirectory(sourceFile, destinationFolder);
        } catch (IOException e) {
            HackerTools.getPlugin().getLoggerManager().log("Error while trying to upload new version of " + type.getSource() + "(" + e.getMessage() + ")");
            return false;
        }
        return true;
    }

}
