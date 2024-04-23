package dev.beaver.beaverhomebudgetspring.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private UUID id;
    private String name;
    private BigDecimal balance;
    private UUID ownerId;

}
