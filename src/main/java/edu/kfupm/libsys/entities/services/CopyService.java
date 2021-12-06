package edu.kfupm.libsys.entities.services;


import edu.kfupm.libsys.entities.copy.OwnedCopy;
import edu.kfupm.libsys.repo.CopyRepository;
import edu.kfupm.libsys.repo.LoanedCopyRepository;
import edu.kfupm.libsys.repo.OwnedCopyRepository;
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
}
