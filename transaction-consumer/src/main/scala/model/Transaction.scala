package model

case class Transaction(transaction_id: String, account_number: String,
                       transaction_reference: String, transaction_datetime: String, amount: Double) extends Serializable
