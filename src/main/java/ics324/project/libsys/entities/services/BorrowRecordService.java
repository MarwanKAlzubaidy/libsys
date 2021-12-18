package ics324.project.libsys.entities.services;

import ics324.project.libsys.entities.BorrowRecord;
import ics324.project.libsys.entities.Fine;
import ics324.project.libsys.entities.copy.Copy;
import ics324.project.libsys.entities.fine_status;
import ics324.project.libsys.entities.user.Customer;
import ics324.project.libsys.enums.Availability;
import ics324.project.libsys.enums.Status;
import ics324.project.libsys.repo.BorrowRecordRepository;
import ics324.project.libsys.repo.CopyRepository;
import ics324.project.libsys.repo.CustomerRepository;
import ics324.project.libsys.repo.FineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class BorrowRecordService {
    final BorrowRecordRepository borrowRecordRepository;
    final CopyRepository copyRepository;
    final CustomerRepository customerRepository;
    final FineRepository fineRepository;

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

    public List<BorrowRecord> getALlrecordsByCustomer(Customer customer) {
        return  borrowRecordRepository.findByCustomer(customer);
    }

    //this methode will check at 11:30pm which book return date = today and status = not returned
    //it will change the status to OverDue
    @Scheduled(cron = "0 0 23 * * *")
    public void updateOverDue()
    {
        List<BorrowRecord> records=borrowRecordRepository.findByReturnDateAndStatus(LocalDate.now(),Status.NOT_DUE);
        List<Fine> fines=new ArrayList<>();
        records.forEach(borrowRecord -> borrowRecord.setStatus(Status.OVERDUE));
        records.forEach(borrowRecord -> fines.add(new Fine(borrowRecord.getCustomer(),100,"book return OverDue", fine_status.NOT_PAID)));
            fineRepository.saveAll(fines);
            borrowRecordRepository.saveAll(records);


    }
}
