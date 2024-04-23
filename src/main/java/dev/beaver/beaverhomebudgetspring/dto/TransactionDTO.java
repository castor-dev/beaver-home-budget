package dev.beaver.beaverhomebudgetspring.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private UUID id;
    private String description;
    private BigDecimal value;
    private Boolean isCredit;
    private Boolean isPlanned;
    private Date date;
    private UUID accountId;

}
