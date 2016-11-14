package aosivt.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by oshchepkovayu on 09.11.16.
 */
@Entity
@Table(name = "configuration_property")
public class ConfigurationProperty {

    @Id
    @GeneratedValue
    private Long conf_id;

    private String pass;
    private String dir_report;

    public Long getConf_id() {
        return conf_id;
    }

    public void setConf_id(Long conf_id) {
        this.conf_id = conf_id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDir_report() {
        return dir_report;
    }

    public void setDir_report(String dir_report) {
        this.dir_report = dir_report;
    }
}
