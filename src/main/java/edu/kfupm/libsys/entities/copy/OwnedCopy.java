package edu.kfupm.libsys.entities.copy;



import edu.kfupm.libsys.entities.copy.Copy;

import javax.persistence.Entity;

import java.sql.Date;
@Entity
public class OwnedCopy extends Copy {


    private Date purchase_date;

}
