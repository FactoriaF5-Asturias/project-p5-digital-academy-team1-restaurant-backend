package dev.team1.bills;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BillsRepository extends JpaRepository<Bill, Long>{

}
