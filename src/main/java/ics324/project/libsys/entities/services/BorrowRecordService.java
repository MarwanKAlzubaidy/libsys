package ics324.project.libsys.entities.services;

import ics324.project.libsys.entities.BorrowRecord;
import ics324.project.libsys.entities.copy.Copy;
import ics324.project.libsys.entities.user.Customer;
import ics324.project.libsys.enums.Availability;
import ics324.project.libsys.enums.Status;
import ics324.project.libsys.repo.BorrowRecordRepository;
import ics324.project.libsys.repo.CopyRepository;
import ics324.project.libsys.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
            } else throw new Exception("Copy not available any more");
        } else throw new Exception("Customer Cant have more than 5 borrows at the same time");

    }

    public List<BorrowRecord> findByCustomerAndStatusNot(Customer customer, Status status) {
        List<BorrowRecord> borrowRecords=  borrowRecordRepository.BRforReturn(customer,status);
        borrowRecords.forEach(borrowRecord -> System.out.println(borrowRecord.getName()));
        return borrowRecords;
    }

    public void saveMultiReturns(Set<BorrowRecord> recordSet) {
        recordSet.forEach(borrowRecord -> saveReturn(borrowRecord)
                );
    }

    private void saveReturn(BorrowRecord borrowRecord) {
        Copy copy=borrowRecord.getCopy();
        copy.setAvailability(Availability.AVAILABLE);
        copyRepository.save(copy);
        borrowRecord.setStatus(Status.RETURNED);
        borrowRecordRepository.save(borrowRecord);
    }
}
