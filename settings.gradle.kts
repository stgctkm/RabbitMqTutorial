rootProject.name = "RabbitMqTutorial"

include(
    "tutorial:1_HelloWorld:producer", "tutorial:1_HelloWorld:consumer",
    "tutorial:2_WorkQueues:producer", "tutorial:2_WorkQueues:consumer",
    "tutorial:3_PubSub:producer", "tutorial:3_PubSub:consumer",
    "tutorial:4_Routing:producer", "tutorial:4_Routing:consumer",
    "tutorial:5_Topic:producer", "tutorial:5_Topic:consumer",
    "tutorial:6_RPC:client", "tutorial:6_RPC:server",
    "SpringAMQP:1_HelloWorld:producer", "SpringAMQP:1_HelloWorld:consumer",
    "SpringAMQP:2_WorkQueues:producer", "SpringAMQP:2_WorkQueues:consumer",
)