package edu.kfupm.libsys.entities.copy;

import edu.kfupm.libsys.entities.Library;
import edu.kfupm.libsys.entities.Return_status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter


public class LoanedCopy extends Copy{
    private Return_status returnToLibraryStatus;
    @JoinColumn(name = "library_id", nullable = false)
    @ManyToOne(optional = false)
    private Library library;
    
}
