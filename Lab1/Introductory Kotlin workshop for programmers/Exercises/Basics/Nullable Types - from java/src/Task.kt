class MailWrapper(private val mailer:JavaMailer){

    fun receiveMail():List<Mail> {
        val mail : List<Mail>? = mailer.receiveMail()
        return mail.orEmpty()
    }

}