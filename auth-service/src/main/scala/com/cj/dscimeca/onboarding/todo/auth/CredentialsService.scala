package com.cj.dscimeca.onboarding.todo.auth

class CredentialsService(credentialsRepository: CredentialsRepository) {

  def isCredentialsValid(username: String, password: String): Boolean = {
    credentialsRepository.get(username) match  {
      case Some(credentials) => doCredentialsMatch(credentials, username, password)
      case None => false
    }
  }

  private def doCredentialsMatch(credentials: Credentials, username: String, password: String): Boolean ={
    credentials.username == username && credentials.password == password
  }
}
