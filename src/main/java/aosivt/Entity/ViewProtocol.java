package aosivt.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oshchepkovayu on 18.10.16.
 */
@Entity
@Table(name = "view_protocols")
//@javax.persistence.SequenceGenerator(name="SEQ_VIEW_PROTOCOL", sequenceName="view_protocol_id_seq")
public class ViewProtocol {



    private static final long serialVersionUID = -7049957706738879274L;

    @Id
//    @GeneratedValue(strategy=GenerationType.SEQUENCE)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name="view_protocol_id_seq", sequenceName="view_protocol_id_seq", allocationSize=1)
    @GeneratedValue
    private Long view_protocol_id;

    private String view_protocol;

    @OneToMany(mappedBy="view_protocol_id",cascade= CascadeType.PERSIST)
    private List<PivotTableProtocol> pivot_tables = new ArrayList<PivotTableProtocol>();

    public Long getView_protocol_id() {
        return view_protocol_id;
    }

    public void setView_protocol_id(Long view_protocol_id) {
        this.view_protocol_id = view_protocol_id;
    }

    public String getView_protocol() {
        return view_protocol;
    }

    public void setView_protocol(String view_protocol) {
        this.view_protocol = view_protocol;
    }

    public List<PivotTableProtocol> getPivot_tables() {
        return pivot_tables;
    }

    public void setPivot_tables(List<PivotTableProtocol> pivot_tables) {
        this.pivot_tables = pivot_tables;
    }

    public String toString()
    {
        return getView_protocol();
    }
}
