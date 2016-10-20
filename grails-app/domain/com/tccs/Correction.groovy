package com.tccs

import com.tccs.type.ReasonType
import com.tccs.type.EntryType
import com.tccs.type.StatusType

class Correction {

	Date dateTimeCorrection
    ReasonType reason
    EntryType entryRequired
    String comment
    StatusType status
    User user

    static hasMany = [proofs: Proof]

    static mapping = {
        autoTimestamp true
    }
    static constraints = {
        dateTimeCorrection blank: false, nullable: false
        reason nullable: false
        entryRequired nullable: false
        comment maxSize: 1000, blank: true, nullable: true
        user nullable: false
        proofs nullable: true
    }
}
