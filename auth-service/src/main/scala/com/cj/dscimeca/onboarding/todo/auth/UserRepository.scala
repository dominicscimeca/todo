package com.cj.dscimeca.onboarding.todo.auth

trait UserRepository{
  def get(id: Int): Option[User]
}
