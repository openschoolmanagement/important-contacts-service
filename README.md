# important-contacts-service
Spring Boot Service for Management of Important Contacts for School and Classes

VCAP services:
'''
{
    "rabbitmq": {
        "hostname": "localhost",
        "password": "pass",
        "port": "5672",
        "uri": "amqp://rmq:pass@localhost:5672",
        "username": "rmq"
    },
    "database": {
      "uri": "jdbc:mysql://localhost/OSM_CONTACTS",
      "username": "dbu_contacts",
      "password": "school123"
    },
    "redis": {
        "hostname": "localhost",
        "password": "pass1",
        "port": "6379"
    },
    "influxdb": {
        "hostname": "localhost",
        "port": "8086",
        "user": "admin"
        "password": "pass",
    },
    "mongodb": {
        "hostname": "localhost",
        "port": "27017",
        "user": "admin"
        "password": "pass",
    }
}

'''
