package ics324.project.libsys.entities.services;


import ics324.project.libsys.entities.Book;
import ics324.project.libsys.entities.copy.Copy;
import ics324.project.libsys.entities.copy.LoanedCopy;
import ics324.project.libsys.entities.copy.OwnedCopy;
import ics324.project.libsys.enums.Availability;
import ics324.project.libsys.repo.CopyRepository;
import ics324.project.libsys.repo.LoanedCopyRepository;
import ics324.project.libsys.repo.OwnedCopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CopyService {
    final CopyRepository copyRepository;
    final OwnedCopyRepository ownedCopyRepository;
    final LoanedCopyRepository loanedCopyRepository;


    public void saveOwendList(List<OwnedCopy> ownedCopyList) {
        ownedCopyRepository.saveAll(ownedCopyList);
    }

    public List<Copy> getAllbyBook(Book book,Availability availability) {
        return  copyRepository.findByBookAndAvailability( book, availability);

    }
    public List<OwnedCopy> getAllOwend(){
        return  ownedCopyRepository.findAll();

    }
    public List<LoanedCopy> getAllLoaned(){
        return  loanedCopyRepository.findAll();

    }
}
