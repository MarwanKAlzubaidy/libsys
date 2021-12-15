package ics324.project.libsys.repo;

import ics324.project.libsys.entities.BorrowRecord;
import ics324.project.libsys.entities.user.Customer;
import ics324.project.libsys.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    @Query(value = "select br from BorrowRecord br where br.customer=?1 and not br.status=?2")
    List<BorrowRecord> BRforReturn(Customer customer, Status status);
}