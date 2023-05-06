package bek.ws.weatherservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "subscriptions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionEntity {
    @Id
    private Integer id;
    private Integer userId;
    private Integer weatherId;
}
