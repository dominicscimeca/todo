package com.cj.dscimeca.onboarding.todo.auth

trait CredentialsRepository{
  def get(username: String): Option[Credentials]
}
