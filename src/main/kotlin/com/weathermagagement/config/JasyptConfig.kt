package com.weathermagagement.config

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties
import org.jasypt.encryption.StringEncryptor
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@EnableEncryptableProperties
@Configuration
class JasyptConfig(
    @Value("\${jasypt.encryptor.password}")
    private val encryptKey: String
) {

    @Primary
    @Bean(JasyptConfig.JASYPT_STRING_ENCRYPTOR)
    fun stringEncryptor(): StringEncryptor {
        val encryptor = StandardPBEStringEncryptor()
        val config = SimpleStringPBEConfig()
        config.password = encryptKey
        config.algorithm = "PBEWithMD5AndDES"
        config.setKeyObtentionIterations("1000")
        config.poolSize = 1
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator")
        config.stringOutputType = "base64"
        encryptor.setConfig(config)

        return encryptor
    }

    companion object {
        const val JASYPT_STRING_ENCRYPTOR: String = "jasyptStringEncryptor"
    }
}