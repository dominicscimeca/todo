package com.cj.dscimeca.onboarding.todo.user

object UserRepositoryInMemory extends UserRepository {
  private val users = Map(
    1 -> User(1, "Bob", "Smith"),
    2 -> User(2, "John", "Doe")
  )

  def get(id: Int): Option[User] = {
    users.get(id)
  }
}
