package dev.beaver.beaverhomebudgetspring.service

import dev.beaver.beaverhomebudgetspring.dao.AccountDAO
import dev.beaver.beaverhomebudgetspring.dao.TransactionDAO
import dev.beaver.beaverhomebudgetspring.dto.TransactionDTO
import dev.beaver.beaverhomebudgetspring.repository.AccountRepository
import dev.beaver.beaverhomebudgetspring.repository.TransactionRepository
import dev.beaver.beaverhomebudgetspring.service.impl.TransactionServiceImpl
import org.modelmapper.ModelMapper
import spock.lang.Specification

import java.text.SimpleDateFormat

class TransactionServiceTest extends Specification {

    def accountRepository = Mock(AccountRepository);
    def transactionRepository = Mock(TransactionRepository)
    def modelMapper = new ModelMapper()

    def transactionService = new TransactionServiceImpl(accountRepository, transactionRepository, modelMapper)


    def "Create Transaction test"() {
        given:
        def accountId = UUID.fromString('2f7bab0b-7bf0-4b8f-abf3-9c55b4894343')
        def transactionId  = UUID.fromString('c39c7800-530f-4375-8ba6-c0b76dde958a')
        def transactionAccountDAO = new AccountDAO().with { it ->
            id = accountId
            it
        }
        def description = "description"
        def value = BigDecimal.valueOf(80)
        def isCredit = true
        def isPlanned = false
        def date = new Date()

        def transactionDto = new TransactionDTO().with { it ->
            it.description = description
            it.value = value
            it.isCredit = isCredit
            it.isPlanned = isPlanned
            it.date = date
            it
        }

        def transactionDAO = new TransactionDAO().with { it ->
            it.id = transactionId
            it.description = description
            it.value = value
            it.isCredit = isCredit
            it.isPlanned = isPlanned
            it.date = date
            account = transactionAccountDAO
            it
        }



        when:
        def transaction = transactionService.createTransaction(transactionDto)

        then:
        1 * accountRepository.findById(_) >> Optional.of(transactionAccountDAO)
        1 * transactionRepository.save(_) >> transactionDAO
        transaction.accountId.equals(accountId)


    }
}
