package backend.picpay.repositories

import backend.picpay.models.Transfer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TransferRepository : JpaRepository<Transfer, Long>{


    @Query("SELECT * FROM transfer t WHERE t.sender = :id OR t.receiver = :id", nativeQuery = true)
    fun findTransfersByUserId(id : Long) : List<Transfer>

}