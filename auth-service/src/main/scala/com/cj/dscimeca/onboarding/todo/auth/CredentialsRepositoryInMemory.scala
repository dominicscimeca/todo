package com.cj.dscimeca.onboarding.todo.auth

object CredentialsRepositoryInMemory extends CredentialsRepository {
  private val users = Map(
    "bsmith" -> Credentials("bsmith", "test1234"),
    "jdoe" -> Credentials("jdoe", "passwordsecure")
  )

  def get(username: String): Option[Credentials] = {
    users.get(username)
  }
}
