package ics324.project.libsys.entities.copy;

import ics324.project.libsys.entities.Library;
import ics324.project.libsys.enums.returnStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter


public class LoanedCopy extends Copy{
    private returnStatus returnToLibraryStatus;
    @JoinColumn(name = "library_id", nullable = false)
    @ManyToOne(optional = false)
    private Library library;
    
}
