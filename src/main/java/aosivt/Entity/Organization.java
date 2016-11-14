package aosivt.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oshchepkovayu on 18.10.16.
 */

@Entity

@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue
    private Long organization_id;

    private String name_organization;

    @OneToMany(mappedBy="organization_id",cascade= CascadeType.PERSIST)
    private List<PivotTableProtocol> pivot_tables = new ArrayList<PivotTableProtocol>();

    public Long getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Long organization_id) {
        this.organization_id = organization_id;
    }

    public String getName_organization() {
        return name_organization;
    }

    public void setName_organization(String name_organization) {
        this.name_organization = name_organization;
    }

    public List<PivotTableProtocol> getPivot_tables() {
        return pivot_tables;
    }

    public void setPivot_tables(List<PivotTableProtocol> pivot_tables) {
        this.pivot_tables = pivot_tables;
    }

    public String toString()
    {
        return getName_organization();
    }
}
