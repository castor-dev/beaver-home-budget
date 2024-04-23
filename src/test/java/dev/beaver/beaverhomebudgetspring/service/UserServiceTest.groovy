package dev.beaver.beaverhomebudgetspring.service

import dev.beaver.beaverhomebudgetspring.dao.UserDAO
import dev.beaver.beaverhomebudgetspring.repository.UserRepository
import dev.beaver.beaverhomebudgetspring.service.impl.UserServiceImpl
import org.modelmapper.ModelMapper
import spock.lang.Specification

class UserServiceTest extends Specification {

    def userRepository = Mock(UserRepository)
    def modelMapper = new ModelMapper()
    def userService = new UserServiceImpl(userRepository, modelMapper)

    def "create user test"() {
        given:
        def userName = "user_name"
        def newUserId = UUID.fromString('b34fcce3-95fc-457f-b92e-d0e61fb2b3f5')
        def newUser = new UserDAO().with { it ->
            name = userName
            id = newUserId
            it
        }

        when:
        userService.createUser(userName)

        then:
        1 * userRepository.save(_) >> newUser
    }

    def "update user test"() {
        given:
        def updatedUserName = 'updated_user_name'

        when:
        userService.updateUser(getId(), updatedUserName)

        then:
        1 * userRepository.findById(getId()) >> Optional.ofNullable(getUser())
        1 * userRepository.save(_) >> getUser()

    }

    def "delete user test"() {
        given:
        def userId = UUID.fromString('9324a2be-723d-4042-ae82-f66436c627fd')

        when:
        userService.deleteUser(userId)

        then:
        1 * userRepository.deleteById(userId)
    }

    def "get all users test"() {
        given:
        def user_1 = new UserDAO().with { it ->
            name = 'user_1'
            id = UUID.fromString('9324a2be-723d-4042-ae82-f66436c627fd')
            it
        }
        def user_2 = new UserDAO().with { it ->
            name = 'user_2'
            id = UUID.fromString('b34fcce3-95fc-457f-b92e-d0e61fb2b3f5')
            it
        }
        def userList = [user_1, user_2]

        when:
        userService.getAllUsers()

        then:
        1 * userRepository.findAll() >> userList
    }

    def getUser() {
        return new UserDAO().with { it ->
            name = 'userName'
            id = getId()
            it
        }
    }

    def getId() {
        return UUID.fromString('9324a2be-723d-4042-ae82-f66436c627fd')
    }
}
