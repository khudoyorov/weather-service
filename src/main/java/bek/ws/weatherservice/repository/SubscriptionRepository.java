package bek.ws.weatherservice.repository;

import bek.ws.weatherservice.entity.SubscriptionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SubscriptionRepository extends ReactiveCrudRepository<SubscriptionEntity,Integer> {
}
