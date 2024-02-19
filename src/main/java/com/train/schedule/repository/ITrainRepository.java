package com.train.schedule.repository;

import com.train.schedule.model.Train;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITrainRepository extends JpaRepository<Train, Long> {
    Train findByTrainNumber(String trainNumber);

    boolean existsByTrainNumber(String trainNumber);

    @Query(nativeQuery = true, value = "SELECT * FROM train_schedule.train where stations like %:source%")
    List<Train> findStationBySource(String source);
   @Transactional
    void deleteByTrainNumber(String trainNumber);
}
