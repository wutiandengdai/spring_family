# Mental_Calc
## What You Need to Know
	- Spring Boot
	- Spring Data
	- Lombock and JUnit
	- RabbitMQ
## System Components
	- multiplication
	  	user, challenge generation, challenge attempt event
	- gamification
	  	score, badge and leaderboard
## Run the System
	1. Install and start rabbitmq (with management plugin)
		> docker run -d --hostname my-rabbit --name rabbit_mq -p 4369:4369 -p 5671-5672:5671-5672 -p 15672:15672 -p 15691-15692:15691-15692 -p 25672:25672 rabbitmq:3-management
	2. start mulitification and gamification instance 1
		> ./mvnw spring-boot:run
	3. Start the second instance of both services
		> ./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=8090

