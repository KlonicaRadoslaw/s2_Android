class Person( val personalInfo: PersonalInfo? )

class PersonalInfo( val data: String? )

interface Mailer {
    fun sendMessage(content: String)
}

fun sendMessageToClient(
        person: Person?, mailer: Mailer
){
    //this is an example of how NOT to do it in Kotlin
	if(person == null) return
	if(person.personalInfo  == null) return
	if(person.personalInfo.data == null ) return
	val personalData = person.personalInfo.data

	mailer.sendMessage(personalData)}


