package com.tccs

import grails.transaction.Transactional

@Transactional
class ProofService {

	Proof createFile(Correction correction, String fileName, byte[] fileData) {
		Proof proof =  new Proof(fileName: fileName, fileData: fileData)
		correction.addToProofs(proof)
		
		return proof
	}
}
