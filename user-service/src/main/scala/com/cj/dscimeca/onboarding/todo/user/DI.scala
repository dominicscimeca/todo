package com.cj.dscimeca.onboarding.todo.user

object DI{
  var UserRepository:UserRepository = UserRepositoryInMemory

  private val restHandlers: List[RestHandler] = List(new UserRestHandler(UserRepository))

  val RestHandlerManager: RestHandlerManager = new RestHandlerManager(restHandlers)

}
