package com.monsanto.arch.awsutil.kms.model

import com.amazonaws.services.kms.model.{GenerateDataKeyResult, GenerateDataKeyWithoutPlaintextResult}
import com.monsanto.arch.awsutil.kms.toBytes

/** Contains information about a data key generated by AWS KMS.
  *
  * @param keyId the unique key identifier of the master key used to encrypt the key
  * @param ciphertext the data key, as encrypted by the master key
  * @param plaintext the data key, optional
  */
case class DataKey(keyId: String, ciphertext: Array[Byte], plaintext: Option[Array[Byte]])

object DataKey {
  /** Generates a new `DataKey` instance from a `GenerateDataKeyResult`, which includes the plaintext. */
  def apply(result: GenerateDataKeyResult): DataKey = DataKey(result.getKeyId, toBytes(result.getCiphertextBlob), Some(toBytes(result.getPlaintext)))

  /** Generates a new `DataKey` instance from a `GenerateDataKeyWithoutPlaintextResult`. */
  def apply(result: GenerateDataKeyWithoutPlaintextResult): DataKey = DataKey(result.getKeyId, toBytes(result.getCiphertextBlob), None)
}