package com.example.keystoretest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyInfo
import android.security.keystore.KeyProperties
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayInputStream
import java.security.*
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val KEYSTORE_TYPE = "AndroidKeyStore"
        private const val RSA_KEY_ALIAS = "RSAbabey"
        private const val GOOGLE_ROOT_CERTIFICATE = "-----BEGIN CERTIFICATE-----\n" +
                "MIIFYDCCA0igAwIBAgIJAOj6GWMU0voYMA0GCSqGSIb3DQEBCwUAMBsxGTAXBgNV\n" +
                "BAUTEGY5MjAwOWU4NTNiNmIwNDUwHhcNMTYwNTI2MTYyODUyWhcNMjYwNTI0MTYy\n" +
                "ODUyWjAbMRkwFwYDVQQFExBmOTIwMDllODUzYjZiMDQ1MIICIjANBgkqhkiG9w0B\n" +
                "AQEFAAOCAg8AMIICCgKCAgEAr7bHgiuxpwHsK7Qui8xUFmOr75gvMsd/dTEDDJdS\n" +
                "Sxtf6An7xyqpRR90PL2abxM1dEqlXnf2tqw1Ne4Xwl5jlRfdnJLmN0pTy/4lj4/7\n" +
                "tv0Sk3iiKkypnEUtR6WfMgH0QZfKHM1+di+y9TFRtv6y//0rb+T+W8a9nsNL/ggj\n" +
                "nar86461qO0rOs2cXjp3kOG1FEJ5MVmFmBGtnrKpa73XpXyTqRxB/M0n1n/W9nGq\n" +
                "C4FSYa04T6N5RIZGBN2z2MT5IKGbFlbC8UrW0DxW7AYImQQcHtGl/m00QLVWutHQ\n" +
                "oVJYnFPlXTcHYvASLu+RhhsbDmxMgJJ0mcDpvsC4PjvB+TxywElgS70vE0XmLD+O\n" +
                "JtvsBslHZvPBKCOdT0MS+tgSOIfga+z1Z1g7+DVagf7quvmag8jfPioyKvxnK/Eg\n" +
                "sTUVi2ghzq8wm27ud/mIM7AY2qEORR8Go3TVB4HzWQgpZrt3i5MIlCaY504LzSRi\n" +
                "igHCzAPlHws+W0rB5N+er5/2pJKnfBSDiCiFAVtCLOZ7gLiMm0jhO2B6tUXHI/+M\n" +
                "RPjy02i59lINMRRev56GKtcd9qO/0kUJWdZTdA2XoS82ixPvZtXQpUpuL12ab+9E\n" +
                "aDK8Z4RHJYYfCT3Q5vNAXaiWQ+8PTWm2QgBR/bkwSWc+NpUFgNPN9PvQi8WEg5Um\n" +
                "AGMCAwEAAaOBpjCBozAdBgNVHQ4EFgQUNmHhAHyIBQlRi0RsR/8aTMnqTxIwHwYD\n" +
                "VR0jBBgwFoAUNmHhAHyIBQlRi0RsR/8aTMnqTxIwDwYDVR0TAQH/BAUwAwEB/zAO\n" +
                "BgNVHQ8BAf8EBAMCAYYwQAYDVR0fBDkwNzA1oDOgMYYvaHR0cHM6Ly9hbmRyb2lk\n" +
                "Lmdvb2dsZWFwaXMuY29tL2F0dGVzdGF0aW9uL2NybC8wDQYJKoZIhvcNAQELBQAD\n" +
                "ggIBACDIw41L3KlXG0aMiS//cqrG+EShHUGo8HNsw30W1kJtjn6UBwRM6jnmiwfB\n" +
                "Pb8VA91chb2vssAtX2zbTvqBJ9+LBPGCdw/E53Rbf86qhxKaiAHOjpvAy5Y3m00m\n" +
                "qC0w/Zwvju1twb4vhLaJ5NkUJYsUS7rmJKHHBnETLi8GFqiEsqTWpG/6ibYCv7rY\n" +
                "DBJDcR9W62BW9jfIoBQcxUCUJouMPH25lLNcDc1ssqvC2v7iUgI9LeoM1sNovqPm\n" +
                "QUiG9rHli1vXxzCyaMTjwftkJLkf6724DFhuKug2jITV0QkXvaJWF4nUaHOTNA4u\n" +
                "JU9WDvZLI1j83A+/xnAJUucIv/zGJ1AMH2boHqF8CY16LpsYgBt6tKxxWH00XcyD\n" +
                "CdW2KlBCeqbQPcsFmWyWugxdcekhYsAWyoSf818NUsZdBWBaR/OukXrNLfkQ79Iy\n" +
                "ZohZbvabO/X+MVT3rriAoKc8oE2Uws6DF+60PV7/WIPjNvXySdqspImSN78mflxD\n" +
                "qwLqRBYkA3I75qppLGG9rp7UCdRjxMl8ZDBld+7yvHVgt1cVzJx9xnyGCC23Uaic\n" +
                "MDSXYrB4I4WHXPGjxhZuCuPBLTdOLU8YRvMYdEvYebWHMpvwGCF6bAx3JBpIeOQ1\n" +
                "wDB5y0USicV3YgYGmi+NZfhA4URSh77Yd6uuJOJENRaNVTzk\n" +
                "-----END CERTIFICATE-----"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        generateAndParseKey()
    }

    private fun generateAndParseKey() {
        val builder = StringBuilder()

        val store = KeyStore.getInstance(KEYSTORE_TYPE).apply { this.load(null) }
        val keyPairGenerator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA)
        val keyFactory = KeyFactory.getInstance(KeyProperties.KEY_ALGORITHM_RSA)
        val rsaSpec = KeyGenParameterSpec.Builder(RSA_KEY_ALIAS, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT or KeyProperties.PURPOSE_SIGN or KeyProperties.PURPOSE_VERIFY)
            .setDigests(KeyProperties.DIGEST_SHA256)
            .setSignaturePaddings(KeyProperties.SIGNATURE_PADDING_RSA_PSS)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_OAEP)
            .setAttestationChallenge("yeet".toByteArray(Charsets.UTF_8))
            .setKeySize(2048)
            .build()

        keyPairGenerator.initialize(rsaSpec)
        keyPairGenerator.genKeyPair()


        val chain = store.getCertificateChain(RSA_KEY_ALIAS)
        // verify certificate chain
        for (i in chain.indices) {
            builder.appendln(chain[i].toString())
            try {
                if (i == chain.size - 1) {
                    chain[i].verify(chain[i].publicKey)
                } else {
                    chain[i].verify(chain[i + 1].publicKey)
                }
            } catch (e: NoSuchAlgorithmException) {
            } catch (e: InvalidKeyException) {
            } catch (e: NoSuchProviderException) {
            } catch (e: SignatureException) {
            } catch (e: CertificateException) {
            }
        }

        val secureRoot = CertificateFactory.getInstance("X.509")
            .generateCertificate(ByteArrayInputStream(GOOGLE_ROOT_CERTIFICATE.toByteArray(Charsets.UTF_8))) as X509Certificate

        val rootList = listOf(chain.last() as X509Certificate, secureRoot)

        builder.appendln()
        for (item in rootList) {
            builder.appendln(item.basicConstraints)
            builder.appendln(item.issuerDN.name)
            builder.appendln(item.issuerUniqueID)
            builder.appendln(item.issuerX500Principal.name)
            builder.appendln(item.serialNumber)
            builder.appendln(item.sigAlgName)
            builder.appendln(item.sigAlgOID)
            builder.appendln(item.subjectDN.name)
            builder.appendln(item.subjectUniqueID)
            builder.appendln(item.version)
            builder.appendln(String(item.tbsCertificate, Charsets.UTF_8))
        }
        builder.appendln()

        if (Arrays.equals((chain.last() as X509Certificate).tbsCertificate, secureRoot.tbsCertificate)) {
            builder.appendln("Final cert is from Google")
        }

        // check that key is inside secure hardware
        val privEntry = store.getEntry(RSA_KEY_ALIAS, null) as KeyStore.PrivateKeyEntry
        val keyInfo = keyFactory.getKeySpec(privEntry.privateKey, KeyInfo::class.java)
        builder.appendln("isInsideSecureHardware: " + keyInfo.isInsideSecureHardware.toString())
        builder.appendln("isInvalidatedByBiometricEnrollment: " + keyInfo.isInvalidatedByBiometricEnrollment.toString())
        builder.appendln("isUserAuthenticationRequired: " + keyInfo.isUserAuthenticationRequired.toString())
        builder.appendln("isUserAuthenticationRequirementEnforcedBySecureHardware: " + keyInfo.isUserAuthenticationRequirementEnforcedBySecureHardware.toString())
        builder.appendln("isUserAuthenticationValidWhileOnBody: " + keyInfo.isUserAuthenticationValidWhileOnBody.toString())
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            builder.appendln("isTrustedUserPresenceRequired: " + keyInfo.isTrustedUserPresenceRequired.toString())
            builder.appendln("isUserConfirmationRequired: " + keyInfo.isUserConfirmationRequired.toString())
        }

        base_view.text = builder.toString()
    }
}
