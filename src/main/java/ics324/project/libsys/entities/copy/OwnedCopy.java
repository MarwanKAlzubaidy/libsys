package ics324.project.libsys.entities.copy;



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
