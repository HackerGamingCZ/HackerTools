package cz.HackerGamingCZ.HackerTools.config;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.managers.LoggerManager;

import java.io.*;
import java.nio.charset.Charset;

public class SimpleConfigManager {

    private HackerTools plugin;
    private LoggerManager loggerManager = HackerTools.getPlugin().getLoggerManager();

    /*
     * Manage custom configurations and files
     */
    public SimpleConfigManager(HackerTools plugin) {
        this.plugin = plugin;
    }

    /*
     * Get new configuration with header
     *
     * @param filePath - Path to file
     *
     * @return - New SimpleConfig
     */
    public SimpleConfig getNewConfig(String filePath, String[] header) {

        File file = this.getConfigFile(filePath);

        if (!file.exists()) {
            this.prepareFile(filePath);

            if (header != null && header.length != 0) {
                this.setHeader(file, header);
            }
        }

        return new SimpleConfig(file, this.getCommentsNum(file), plugin);

    }

    /*
     * Get new configuration
     *
     * @param filePath - Path to file
     *
     * @return - New SimpleConfig
     */
    public SimpleConfig getNewConfig(String filePath) {
        return this.getNewConfig(filePath, null);
    }

    /*
     * Get configuration file from string
     *
     * @param file - File path
     *
     * @return - New file object
     */
    public File getConfigFile(String file) {

        if (file == null || file.isEmpty()) {
            return null;
        }

        File configFile;

        if (file.contains("/")) {

            if (file.startsWith("/")) {
                configFile = new File(plugin.getDataFolder()
                        + file.replace("/", File.separator));
            } else {
                configFile = new File(plugin.getDataFolder() + File.separator
                        + file.replace("/", File.separator));
            }

        } else {
            configFile = new File(plugin.getDataFolder(), file);
        }

        return configFile;

    }

    /*
     * Create new file for config and copy resource into it
     *
     * @param file - Path to file
     *
     * @param resource - Resource to copy
     */
    public void prepareFile(String filePath, String resource) {

        File file = this.getConfigFile(filePath);

        if (file == null) {
            return;
        }

        if (file.exists()) {
            return;
        }

        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            if (resource != null) {
                if (!resource.isEmpty()) {
                    this.copyResource(plugin.getResource(resource), file);
                }
            }

        } catch (IOException e) {
            loggerManager.logException("Chyba při příprave config souboru.", e);
        }

    }

    /*
     * Create new file for config without resource
     *
     * @param file - File to create
     */
    public void prepareFile(String filePath) {
        this.prepareFile(filePath, null);
    }

    /*
     * Adds header block to config
     *
     * @param file - Config file
     *
     * @param header - Header lines
     */
    public void setHeader(File file, String... header) {

        if (!file.exists()) {
            return;
        }

        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            String currentLine;
            StringBuilder config = new StringBuilder("");
            reader = new BufferedReader(new FileReader(file));

            while ((currentLine = reader.readLine()) != null) {
                config.append(currentLine).append("\n");
            }

            reader.close();
            config.append("# +----------------------------------------------------+ #\n");

            for (String line : header) {

                if (line.length() > 50) {
                    continue;
                }

                int lenght = (50 - line.length()) / 2;
                StringBuilder finalLine = new StringBuilder(line);

                for (int i = 0; i < lenght; i++) {
                    finalLine.append(" ");
                    finalLine.reverse();
                    finalLine.append(" ");
                    finalLine.reverse();
                }

                if (line.length() % 2 != 0) {
                    finalLine.append(" ");
                }

                config.append("# < ").append(finalLine.toString()).append(" > #\n");

            }

            config.append("# +----------------------------------------------------+ #");

            writer = new BufferedWriter(new FileWriter(file));
            writer.write(this.prepareConfigString(config.toString()));
            writer.flush();
            writer.close();

        } catch (IOException ex) {
            loggerManager.logException("Chyba při nastavování hlavičky config souboru.", ex);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    loggerManager.logException("Chyba při uzavírání čtečky u nastavování hlavičky config souboru.", ex);
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                    loggerManager.logException("Chyba při uzavírání zápisu u nastavování hlavičky config souboru.", ex);
                }
            }
        }

    }

    /*
     * Read file and make comments SnakeYAML friendly
     *
     * @param filePath - Path to file
     *
     * @return - File as Input Stream
     */
    public InputStream getConfigContent(File file) {

        if (!file.exists()) {
            return null;
        }

        BufferedReader reader = null;
        try {
            int commentNum = 0;

            String addLine;
            String currentLine;
            String pluginName = this.getPluginName();

            StringBuilder whole = new StringBuilder("");
            reader = new BufferedReader(new FileReader(file));

            while ((currentLine = reader.readLine()) != null) {

                if (currentLine.startsWith("#")) {
                    addLine = currentLine.replaceFirst("#", pluginName
                            + "_COMMENT_" + commentNum + ":");
                    whole.append(addLine).append("\n");
                    commentNum++;

                } else {
                    whole.append(currentLine).append("\n");
                }

            }

            String config = whole.toString();
            return new ByteArrayInputStream(config.getBytes(Charset.forName("UTF-8")));
        } catch (IOException ex) {
            loggerManager.logException("Configuration error", ex);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    loggerManager.logException("Configuration close error", ex);
                }
            }
        }
        return null;
    }

    /*
     * Get comments from file
     *
     * @param file - File
     *
     * @return - Comments number
     */
    private int getCommentsNum(File file) {

        if (!file.exists()) {
            return 0;
        }

        BufferedReader reader = null;
        try {
            int comments = 0;
            String currentLine;

            reader = new BufferedReader(new FileReader(file));

            while ((currentLine = reader.readLine()) != null) {

                if (currentLine.startsWith("#")) {
                    comments++;
                }

            }

            reader.close();
            return comments;
        } catch (IOException ex) {
            loggerManager.logException("Configuration error", ex);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    loggerManager.logException("Configuration close error", ex);
                }
            }
        }
        return 0;
    }

    /*
     * Get config content from file
     *
     * @param filePath - Path to file
     *
     * @return - readied file
     */
    public InputStream getConfigContent(String filePath) {
        return this.getConfigContent(this.getConfigFile(filePath));
    }

    private String prepareConfigString(String configString) {

        int lastLine = 0;
        int headerLine = 0;

        String[] lines = configString.split("\n");
        StringBuilder config = new StringBuilder("");

        for (String line : lines) {

            if (line.startsWith(this.getPluginName() + "_COMMENT")) {
                String comment = "#"
                        + line.trim().substring(line.indexOf(":") + 1);

                if (comment.startsWith("# +-")) {

                    /*
                     * If header line = 0 then it is header start, if it's equal
                     * to 1 it's the end of header
                     */
                    if (headerLine == 0) {
                        config.append(comment + "\n");

                        lastLine = 0;
                        headerLine = 1;

                    } else if (headerLine == 1) {
                        config.append(comment + "\n\n");

                        lastLine = 0;
                        headerLine = 0;

                    }

                } else {

                    /*
                     * Last line = 0 - Comment Last line = 1 - Normal path
                     */
                    String normalComment;

                    if (comment.startsWith("# ' ")) {
                        normalComment = comment.substring(0,
                                comment.length() - 1)
                                .replaceFirst("# ' ", "# ");
                    } else {
                        normalComment = comment;
                    }

                    if (lastLine == 0) {
                        config.append(normalComment + "\n");
                    } else if (lastLine == 1) {
                        config.append("\n" + normalComment + "\n");
                    }

                    lastLine = 0;

                }

            } else {
                config.append(line + "\n");
                lastLine = 1;
            }

        }

        return config.toString();

    }

    /*
     * Saves configuration to file
     *
     * @param configString - Config string
     *
     * @param file - Config file
     */
    public void saveConfig(String configString, File file) {
        String configuration = this.prepareConfigString(configString);

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(configuration);
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            loggerManager.logException("Configuration error while saving config file.", ex);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                    loggerManager.logException("Configuration error while closing writer on saving config file.", ex);
                }
            }
        }

    }

    public String getPluginName() {
        return plugin.getPdf().getName();
    }

    /*
     * Copy resource from Input Stream to file
     *
     * @param resource - Resource from .jar
     *
     * @param file - File to write
     */
    private void copyResource(InputStream resource, File file) {

        OutputStream out = null;
        try {
            out = new FileOutputStream(file);

            int lenght;
            byte[] buf = new byte[1024];

            while ((lenght = resource.read(buf)) > 0) {
                out.write(buf, 0, lenght);
            }

            out.close();
            resource.close();

        } catch (Exception ex) {
            loggerManager.logException("Configuration error while copying resource.", ex);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    loggerManager.logException("Configuration error while closing stream on copying resource.", ex);
                }
            }
        }
    }
}
