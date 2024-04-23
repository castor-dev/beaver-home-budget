package dev.beaver.beaverhomebudgetspring.service

import dev.beaver.beaverhomebudgetspring.dao.AccountDAO
import dev.beaver.beaverhomebudgetspring.dao.UserDAO
import dev.beaver.beaverhomebudgetspring.dto.AccountDTO
import dev.beaver.beaverhomebudgetspring.repository.AccountRepository
import dev.beaver.beaverhomebudgetspring.repository.UserRepository
import dev.beaver.beaverhomebudgetspring.service.impl.AccountServiceImpl
import org.modelmapper.ModelMapper
import spock.lang.Specification

class AccountServiceTest extends Specification {


    def accountRepository = Mock(AccountRepository)
    def userRepository = Mock(UserRepository)
    def modelMapper = new ModelMapper()

    def accountService = new AccountServiceImpl(accountRepository, userRepository, modelMapper)


    def "create account test"() {
        given:
        def ownerUserId = UUID.fromString('61ba7eda-9e80-489e-b464-7bb371a92210')
        def accountName = 'bank'
        def accountBalance = BigDecimal.valueOf(500.0)
        def accountDTO = new AccountDTO().with {it ->
            name = accountName
            balance = accountBalance
            ownerId = ownerUserId
            it
        }
        def userDao = new UserDAO().with {it ->
            name = 'owner_name'
            id = ownerUserId
            it
        }
        def accountDAO = new AccountDAO().with { it ->
            name = accountName
            balance = accountBalance
            owner = userDao
            it
        }

        when:
        def account = accountService.createAccount(accountDTO)

        then:
        1 * userRepository.findById(_) >> Optional.of(userDao)
        1 * accountRepository.save(_) >> accountDAO
        account.getOwnerId() == userDao.id
    }
}
