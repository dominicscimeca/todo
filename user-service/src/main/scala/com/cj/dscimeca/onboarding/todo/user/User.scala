package com.cj.dscimeca.onboarding.todo.user

case class User (id: Integer, firstName: String, lastName: String){

    def getFullName: String = "%s %s".format(firstName, lastName)

    def toJSONString: String = s"""{id:$id,"firstname":"$firstName","lastname":"$lastName","fullname":"$getFullName"}"""

}
