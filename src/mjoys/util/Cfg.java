package mjoys.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Cfg {
    private String root;
    private String cfgFilePath;
    private File defaultPropertyFile;
    private Properties defaultPropertyCfg;
    
    private final static Logger logger = new Logger().addPrinter(System.out);
    
    public Cfg(String cfgFilePathInRoot, String defaultPropertyFileName) {
        this.root = System.getProperty("user.dir");
        this.cfgFilePath = PathUtil.combine(root, cfgFilePathInRoot);
        this.defaultPropertyFile = new File(this.cfgFilePath, defaultPropertyFileName);
        if (!this.defaultPropertyFile.exists()) {
            logger.log("can't find cfg file");
            return;
        }
        
        this.defaultPropertyCfg = getProperties(this.defaultPropertyFile);
        if (this.defaultPropertyCfg == null) {
            logger.log("can't read properties file");
            return;
        }
    }
    
    public String getRoot() {
        return root;
    }

    public String getCfgFilePath() {
        return cfgFilePath;
    }

    public Properties getDefaultPropertyCfg() {
        return defaultPropertyCfg;
    }
    
    public final static Properties getProperties(File file) {
        InputStream in;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            Properties p = new Properties();   
            p.load(in);
            return p;
        } catch (Exception e) {
            e.printStackTrace();
            return new Properties();
        }   
    }
}
