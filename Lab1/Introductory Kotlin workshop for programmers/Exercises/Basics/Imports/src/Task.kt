import domain.external.Customer as ExternalCustomer
import domain.internal.Customer as InternalCustomer

open class CustomerMapper {

    fun mapToInternalCustomer(cust1 : ExternalCustomer) : InternalCustomer{
        return InternalCustomer(cust1.name, cust1.id)
    }

}

fun main(){}