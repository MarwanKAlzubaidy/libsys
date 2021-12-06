package edu.kfupm.libsys.entities.copy;



import edu.kfupm.libsys.entities.copy.Copy;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;


import java.time.LocalDate;

@Entity
@Setter
@Getter
public class OwnedCopy extends Copy {


    private LocalDate purchase_date;

}
