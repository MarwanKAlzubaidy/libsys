package ics324.project.libsys.entities.services;

import ics324.project.libsys.entities.BorrowRecord;
import ics324.project.libsys.entities.copy.Copy;
import ics324.project.libsys.enums.Availability;
import ics324.project.libsys.repo.BorrowRecordRepository;
import ics324.project.libsys.repo.CopyRepository;
import ics324.project.libsys.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BorrowRecordService {
    final BorrowRecordRepository borrowRecordRepository;
    final CopyRepository copyRepository;
    final CustomerRepository customerRepository;


    public List<BorrowRecord> getALlrecords() {
        return borrowRecordRepository.findAll();
    }

    public void save(BorrowRecord borrowRecord) throws Exception {
        if (customerRepository.availableForBorrow().contains(borrowRecord.getCustomer())) {
            Copy copy = copyRepository.getById(borrowRecord.getCopy().getId());

        if (copy.getAvailability() == Availability.AVAILABLE) {
            borrowRecordRepository.save(borrowRecord);
            copy.setAvailability(Availability.NOT_AVAILABLE);
            copyRepository.save(copy);
        } else throw new Exception("Copy not available any more");}

                else throw new Exception("Customer Cant have more than 5 borrows at the same time");

    }
}
