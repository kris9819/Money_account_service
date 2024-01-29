package com.money_account_service.services


import com.money_account_service.entities.LedgerEntity
import com.money_account_service.mappers.AvroMapper
import com.money_account_service.services.kafka.LedgerMessagePublisher
import org.apache.kafka.clients.admin.Admin
import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.containers.Network
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.spock.Testcontainers
import org.testcontainers.utility.DockerImageName
import spock.lang.Shared
import spock.lang.Specification

@Testcontainers
@SpringBootTest
class LedgerMessagePublisherKafkaTest extends Specification {

//    @Autowired
//    LedgerMessagePublisher ledgerMessagePublisher
//
//    @Autowired
//    private KafkaProperties kafkaProperties

//    private static final Network NETWORK = Network.newNetwork()

    @Shared
    static final KafkaContainer KAFKA_CONTAINER = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.5.2"))
//            .withNetwork(NETWORK)
//            .start()

    private static final GenericContainer<?> SCHEMA_REGISTRY = new GenericContainer<>(DockerImageName.parse("confluentinc/cp-schema-registry:7.5.2"))
//            .withNetwork(NETWORK)
            .withExposedPorts(8081)
            .withEnv("SCHEMA_REGISTRY_HOST_NAME", "schema-registry")
            .withEnv("SCHEMA_REGISTRY_LISTENERS", "http://0.0.0.0:8081")
//            .withEnv("SCHEMA_REGISTRY_KAFKASTORE_BOOTSTRAP_SERVERS",
//                    "PLAINTEXT://" + KAFKA_CONTAINER.getNetworkAliases().get(0) + ":9092")
            .waitingFor(Wait.forHttp("/subjects").forStatusCode(200))

    @DynamicPropertySource
    static void registerDynamicProperties(DynamicPropertyRegistry registry) {
        println("kafkaProducerConfig")
        registry.add("spring.kafka.producer.bootstrap-servers", KAFKA_CONTAINER::getBootstrapServers)
    }

    def setupSpec() {
        KAFKA_CONTAINER.start()
//        SCHEMA_REGISTRY.start()

        Properties properties = new Properties();
        properties.put(
                AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_CONTAINER.getBootstrapServers()
        )

        Admin admin = Admin.create(properties)
        NewTopic newTopic = new NewTopic("transfers", 1, (short)1)
        admin.createTopics(Collections.singleton(newTopic))
    }


    def "test"() {
        given:
        print("bootstrap: " + KAFKA_CONTAINER.getBootstrapServers())
        print("netowrk: " + KAFKA_CONTAINER.getNetworkAliases().get(0))

//        LedgerEntity ledgerEntity = LedgerEntity.builder()
//                .ledgerRecordId(1L)
//                .debitAccountId(12L)
//                .creditAccountId(13L)
//                .debitAmount(123L)
//                .debitCurrency("PLN")
//                .creditAmount(500L)
//                .creditCurrency("PLN")
//                .exchangeRate(1L)
//                .transferId(16L)
//                .build();

//        ledgerMessagePublisher.publish(AvroMapper.entityToAvro(ledgerEntity))

        expect:

        1==1

    }
}
