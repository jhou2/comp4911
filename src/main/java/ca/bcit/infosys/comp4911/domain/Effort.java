package ca.bcit.infosys.comp4911.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;

import java.io.Serializable;

@Entity
public class Effort implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EFFORT_ID", updatable = false, nullable = false)
    @Min(value = 0, message="ID can not be smaller than 0.") 
    private Integer id = null;
    
	@Version
    @Column(name = "version")
    private int version = 0;

    @Enumerated(EnumType.STRING)
    private PLevel pLevel;

    /** Tenths of hours */
    @Column
    @Min(value = 0, message="PersonDays can not be smaller than 0.")
    private int personDays;

    public Effort(PLevel pLevel, int personDays) {
		super();
		this.pLevel = pLevel;
		this.personDays = personDays;
	}
    
    /**
     * Construct an Effort, default personDays to 0
     * @param pLevel
     */
    public Effort(PLevel pLevel) {
		super();
		this.pLevel = pLevel;
		this.personDays = 0;
	}
    
    public Effort(){
    	
    }
    
    public int getVersion() {
        return this.version;
    }

    public void setVersion(final int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        if (id != null) {
            return id.equals(((Effort) that).id);
        }
        return super.equals(that);
    }

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        }
        return super.hashCode();
    }

    public PLevel getpLevel() {
        return this.pLevel;
    }

    public void setpLevel(final PLevel pLevel) {
        this.pLevel = pLevel;
    }

    public int getPersonDays() {
        return this.personDays;
    }

    public void setPersonDays(final int personDays) {
        this.personDays = personDays;
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Effort{" +
                "id=" + id +
                ", version=" + version +
                ", pLevel=" + pLevel +
                ", personDays=" + personDays +
                '}';
    }
}