package org.iona.currency.repository;

import org.iona.currency.models.Rate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by Bakhtiyor Ruziev on 21.06.15.
 * <p>
 * Rate repository
 */
public interface RateRepository extends MongoRepository<Rate, String> {


    @Query("{date:?0}")
    Rate findByDate(String date);

    @Query("{date: {$gte: ?0, $lte: ?1}}")
    List<Rate> findBetweenDate(String fromDate, String toDate);


}
