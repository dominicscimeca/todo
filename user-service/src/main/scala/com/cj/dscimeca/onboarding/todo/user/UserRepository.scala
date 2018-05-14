package com.cj.dscimeca.onboarding.todo.user

trait UserRepository{
  def get(id: Int): Option[User]
}
