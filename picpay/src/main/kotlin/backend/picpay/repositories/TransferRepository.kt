package backend.picpay.repositories

import backend.picpay.models.Transfer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransferRepository : JpaRepository<Transfer, Long> {

}