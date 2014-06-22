package configuration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

public enum Config {
  INSTANCE;

  private Configuration config;

  InputStream input;
  BufferedWriter output;

  public void populate() {

    File file = new File("config.yml");
    try {
      input = new FileInputStream(file.getAbsoluteFile());
      loadConfig();
    } catch (IOException e) {
      FileWriter fw;
      try {
        fw = new FileWriter(file.getAbsoluteFile());
        output = new BufferedWriter(fw);
        saveNewConfig();
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
  }

  private void saveNewConfig() throws IOException {
    config = new Configuration();
    config.setDatabase("chat");
    config.setPassword("");
    config.setUrl("");
    config.setUser("root");

    ArrayList<String> announcements = new ArrayList<String>();

    announcements.add("&7[&b*&7] Welcome to 1337Clan");
    announcements.add("&7[&b*&7] We are currently in the alpha stages of our new chat system");
    announcements.add("&7[&b*&7] Please report any issues to a staff member");
    announcements.add("&7[&b*&7] Thank you for playing!");
    announcements.add("&7[&b*&7] Don't forget to vote for us");

    config.setAnnouncables(announcements);
    config.setInterval(600000);

    Representer representer = new Representer();

    Tag antag = new Tag("!Config");
    representer.addClassTag(Configuration.class, antag);
    DumperOptions dumperOptions = new DumperOptions();
    dumperOptions.setPrettyFlow(true);
    dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

    Yaml yaml = new Yaml(representer, dumperOptions);
    output.write(yaml.dump(config));

    output.close();
  }

  public void loadConfig() throws IOException {
    Yaml yaml = new Yaml();
    config = yaml.loadAs(input, Configuration.class);
  }
  
  public Configuration getConfig(){
    return this.config;
  }

}
